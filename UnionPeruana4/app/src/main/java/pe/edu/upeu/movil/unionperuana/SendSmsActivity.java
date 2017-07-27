package pe.edu.upeu.movil.unionperuana;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;


import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.Campaign;
import pe.edu.upeu.movil.unionperuana.bean.Sms;
import pe.edu.upeu.movil.unionperuana.service.UnionService;
import pe.edu.upeu.movil.unionperuana.util.Commons;

public class SendSmsActivity extends AppCompatActivity {

    String campaignId;
    TextView textUrl;
    ProgressBar progressBar;
    ListView lstCampaign;


    private static final int PROGRESS = 0x1;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    List<Sms> listSmsToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        Button btnSendMessage = (Button) findViewById(R.id.btnSendMessage);
        textUrl = (TextView) findViewById(R.id.textUrl);


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                progressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                sendSMS();
            }
        });


        UnionService unionService = new UnionService();
        List<Campaign> list = unionService.findCampaign(getPhoneNumber());

        lstCampaign = (ListView) findViewById(R.id.lstCampaign);
        ArrayAdapter<Campaign> adapterList = new ArrayAdapter<Campaign>(this, android.R.layout.simple_list_item_1, list);
        lstCampaign.setAdapter(adapterList);
        lstCampaign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);
                Campaign campaign = (Campaign) parent.getItemAtPosition(position);
                campaignId = campaign.getId() + "";
                Toast.makeText(getBaseContext(), "Campaña seleccionada : " + campaign.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @NonNull
    @org.jetbrains.annotations.Contract(pure = true)
    private String getPhoneNumber() {
        //TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //String phoneNumber = mTelephonyMgr.getDeviceId();
        //if (phoneNumber == null) {
        //    phoneNumber = "357722070285067";
        //}
        //return phoneNumber;
        return "357722070285067";
    }

    protected void sendSMS() {
        //new Thread(new Runnable() {
        //@Override
        //public void run() {
                /*try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                SmsManager manager = SmsManager.getDefault();
                UnionService smsService = new UnionService();

                String url = Commons.URL_STRING_SMS + "allmessage/" + getPhoneNumber() + "_" + campaignId;

                // PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


                listSmsToSend = smsService.findSms(url);
                int countMessageSent=0;
                for (Sms sms : listSmsToSend) {
                    try {
                        if (sms.getMessage().length() > 151) {
                            int lengthValidate = 150;
                            manager.sendTextMessage(sms.getNumPhone(), null, sms.getMessage().substring(0, lengthValidate), null, null);
                            manager.sendTextMessage(sms.getNumPhone(), null, sms.getMessage().substring(lengthValidate, sms.getMessage().length()),
                                    null, null);
                        } else {
                            manager.sendTextMessage(sms.getNumPhone(), null, sms.getMessage(), null, null);
                        }
                        url = Commons.URL_STRING_SMS + "updatesms/" + getPhoneNumber() + "_" + sms.getId();
                        smsService.updateSms(url);
                        countMessageSent++;
                    } catch (Exception ex) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "Your sms has failed...", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                if(countMessageSent>0){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            textUrl.setText(listSmsToSend.size() + " Mensajes fueron enviados.");
                        }
                    });
                }
        // }

        //}).start();

    }

}
