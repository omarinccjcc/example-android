package pe.edu.upeu.movil.unionperuana;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.Institution;
import pe.edu.upeu.movil.unionperuana.service.UnionService;

public class ShowChurchMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_church_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle reicieveParams = getIntent().getExtras();
        //Toast.makeText(this, reicieveParams.getString("baseTypeId")+" - "+reicieveParams.getString("cityId"), Toast.LENGTH_SHORT).show();
        UnionService unionService = new UnionService();

        List<Institution> list = unionService.findInstitutionByBaseTyIdCityIdChurch(
                reicieveParams.getString("baseTypeId"),
                reicieveParams.getString("cityId"),
                reicieveParams.getString("church"));

        Toast.makeText(this, list.size()+"", Toast.LENGTH_SHORT).show();
    }

}
