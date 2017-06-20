package pe.edu.upeu.movil.unionperuana;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Context;


import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.Campaign;
import pe.edu.upeu.movil.unionperuana.bean.Sms;
import pe.edu.upeu.movil.unionperuana.service.UnionService;

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
                //sendSMS();
            }
        });


        UnionService smsService = new UnionService();
        List<Campaign> list = smsService.findCampaign(getPhoneNumber());

        //listview
        lstCampaign = (ListView) findViewById(R.id.lstCampaign);
        ArrayAdapter<Campaign> adapterList = new ArrayAdapter<Campaign>(this, android.R.layout.simple_list_item_1, list);
        lstCampaign.setAdapter(adapterList);

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

}
