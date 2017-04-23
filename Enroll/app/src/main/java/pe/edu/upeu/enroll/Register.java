package pe.edu.upeu.enroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.upeu.enroll.bean.Person;

public class Register extends AppCompatActivity {

    EditText txtNames;
    EditText txtLastNames;
    EditText txtAddress;
    private Spinner spinnerSex;
    String sexo;
    private Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonSave = (Button)findViewById(R.id.buttonSave);
        txtNames = (EditText)findViewById(R.id.txtNames);
        txtLastNames = (EditText)findViewById(R.id.txtLastNames);
        txtAddress = (EditText)findViewById(R.id.txtAddress);

        spinnerSex = (Spinner)findViewById(R.id.spinnerSex);

        final String [] sexsString = {"Masculino","Femenino"};
        ArrayAdapter<String> sexs= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sexsString);
        spinnerSex.setAdapter(sexs);

        spinnerSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sexo=(String)parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(),"sexo selected: "+sexo,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spinnerSex.setOnItemSelectedListener(new AdapterView.setOnItemSelectedListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                sexo=(String)parent.getItemAtPosition(position);
//            }
//        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.listPerson.add(new Person(1,txtNames.toString(),
                        txtLastNames.toString(),txtAddress.toString(),sexo,"Activo"));
                goMain();
            }
        });
    }

    public void goMain(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
