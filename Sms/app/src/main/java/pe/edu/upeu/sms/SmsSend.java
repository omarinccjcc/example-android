package pe.edu.upeu.sms;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.edu.upeu.sms.service.SmsService;
import pe.edu.upeu.sms.util.Campaign;
import pe.edu.upeu.sms.util.Sms;
import pe.edu.upeu.sms.util.SmsCommon;

public class SmsSend extends AppCompatActivity {


    List<String> list;
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
        setContentView(R.layout.activity_sms_send);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        Button btnSendMessage = (Button) findViewById(R.id.btnSendMessage);
        textUrl = (TextView) findViewById(R.id.textUrl);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                progressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                // sendSMS();

            }
        });

        SmsService smsService = new SmsService();
        String url = SmsCommon.URL_STRING + "campaign/" + getPhoneNumber();
        List<Campaign> list = smsService.findCampaign(url);

        //listview
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

    private String getPhoneNumber() {
        TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = mTelephonyMgr.getDeviceId();
        if (phoneNumber == null) {
            phoneNumber = "357722070285067";
        }
        return phoneNumber;
    }

    /**
     * Send all message by sms of cell
     */

    // sendSMS()


}
