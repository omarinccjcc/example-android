package pe.edu.upeu.registro.registromvc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.registro.registromvc.bean.Person;
import pe.edu.upeu.registro.registromvc.dao.PersonDAO;
import pe.edu.upeu.registro.registromvc.frm.FrmPersonHelper;

public class Register extends AppCompatActivity {

    private FrmPersonHelper frmPersonHelper;

    private Person person;

    private List<String> listSex;
    private Spinner spinnerSex;
    private Switch switchMarried;
    private TextView textViewMarried;
    private int personId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frmPersonHelper = new FrmPersonHelper(Register.this);
        spinnerLoad();
        switchLoad();
        editLoad();


        Button btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                person = frmPersonHelper.getPersonOfFrm(spinnerSex,textViewMarried);
                PersonDAO personDao = new PersonDAO(Register.this);
                personDao.savePerson(person);
                personDao.close();
            }
        });
    }


    public void editLoad(){
        // para obtener valores enviados de la otra actividad
        Bundle parameters = getIntent().getExtras();
        personId = parameters.getInt("personId");
        if(personId!=0){
            PersonDAO personDao = new PersonDAO(Register.this);
            Person person = personDao.findPersonById(personId+"");
            frmPersonHelper.setPerson(person);

            for(int i = 0; i < spinnerSex.getCount(); i++){
                if(spinnerSex.getItemAtPosition(i).toString().equals(person.getSex())){
                    spinnerSex.setSelection(i);
                    break;
                }
            }

            if(person.getStatusMarried().equals("Casado")){
                switchMarried.setChecked(true);
                textViewMarried.setText("Casado");
            }
        }
    }

    public void spinnerLoad(){
        spinnerSex = (Spinner)findViewById(R.id.spinnerSex);
        listSex = new ArrayList<String>();
        listSex.add("-- Seleccione --");
        listSex.add("Masculino");
        listSex.add("Famenino");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listSex);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(dataAdapter);
    }

    public void switchLoad(){
        switchMarried = (Switch) findViewById(R.id.switchMarried);
        textViewMarried= (TextView) findViewById(R.id.switchMarried);
        switchMarried.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    textViewMarried.setText("Casado");
                } else {
                    textViewMarried.setText("Sotero");
                }
            }
        });
    }
}
