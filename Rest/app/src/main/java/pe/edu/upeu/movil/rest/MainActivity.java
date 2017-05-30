package pe.edu.upeu.movil.rest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import pe.edu.upeu.movil.rest.bean.Campaign;
import pe.edu.upeu.movil.rest.service.UnionService;

public class MainActivity extends AppCompatActivity {


    ListView listCampaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UnionService unionService = new UnionService();
        String url = "http://192.168.43.6:8080/Sms/service/campaign/357722070285067" ;
        List<Campaign> list = unionService.findCampaign(url);

        //listview
        listCampaign = (ListView) findViewById(R.id.listCampaign);
        ArrayAdapter<Campaign> adapterList = new ArrayAdapter<Campaign>(this, android.R.layout.simple_list_item_1, list);
        listCampaign.setAdapter(adapterList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
