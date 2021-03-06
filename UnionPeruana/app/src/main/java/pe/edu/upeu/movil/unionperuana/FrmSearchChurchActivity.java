package pe.edu.upeu.movil.unionperuana;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import pe.edu.upeu.movil.unionperuana.bean.BaseType;
import pe.edu.upeu.movil.unionperuana.bean.City;
import pe.edu.upeu.movil.unionperuana.service.UnionService;

/**
 * Created by omar on 21/05/17.
 */

public class FrmSearchChurchActivity extends AppCompatActivity {

    private Spinner spinnerType;
    private Spinner spinnerCity;
    private Button btnSearchChurch;
    private EditText txtNameChurch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_search_church);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // para el manejo de los rest
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        UnionService unionService = new UnionService();
        spinnerTypeLoad(unionService);
        spinnerCityLoad(unionService);
        btnSearchChurchLoad();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//habilita el boton en ActionBar

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void btnSearchChurchLoad(){
        btnSearchChurch = (Button)findViewById(R.id.btnSearchChurch);
        txtNameChurch = (EditText) findViewById(R.id.txtNameChurch);
        btnSearchChurch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FrmSearchChurchActivity.this, spinnerType.getSelectedItemId()+ " - "+txtNameChurch.getText(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(FrmSearchChurchActivity.this, NearToMeActivity.class);
                String typeCode = ( (BaseType)spinnerType.getSelectedItem() ).getID();
                i.putExtra("baseTypeId", typeCode );

                City city =(City)spinnerCity.getSelectedItem();
                i.putExtra("cityId", city.getId() );
                i.putExtra("latitud", city.getLatitud() );
                i.putExtra("longitud", city.getLongitud());

                i.putExtra("church", txtNameChurch.getText().toString());
                i.putExtra("typeSearch", "search");
                startActivity(i);
            }
        });
    }

    public void spinnerTypeLoad(UnionService unionService) {
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        List<BaseType> list = unionService.findTypeInstitutions();
        ArrayAdapter<BaseType> dataAdapter = new ArrayAdapter<BaseType>(FrmSearchChurchActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
        spinnerType.setAdapter(dataAdapter);
    }

    public void spinnerCityLoad(UnionService unionService) {
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        List<City> list = unionService.findCity();
        ArrayAdapter<City> dataAdapter = new ArrayAdapter<City>(FrmSearchChurchActivity.this, android.R.layout.simple_spinner_item, list);
        spinnerCity.setAdapter(dataAdapter);
    }
}
