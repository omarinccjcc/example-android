package pe.edu.upeu.registro;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import pe.edu.upeu.registro.bean.Person;

public class Register extends AppCompatActivity {

    private EditText txtName;
    private EditText txtLastNameF;
    private EditText txtLastNameM;
    private EditText txtAddress;
    private Spinner spinnerSex;
    private int personId;
    private List<String> listSex;

    private RadioButton radioActive, radioInactive;
    private RadioGroup radioGroupStatus;

    private Switch switchMarried;
    private TextView textViewMarried;

    private TextInputLayout tilName,tilSex;

    private RatingBar ratingBarScore;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadFiles();
        switchLoad();
        radioLoad();
        ratingBarLoad();
        spinnerLoad();
        editLoad();
        savePerson();
    }

    public void ratingBarLoad(){
        ratingBarScore = (RatingBar) findViewById(R.id.ratingBarScore);


        ratingBarScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                score=String.valueOf(rating);
            }
        });
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
    public void loadFiles(){
        txtName = (EditText) findViewById(R.id.txtNombre);
        txtLastNameF = (EditText) findViewById(R.id.txtLastNameF);
        txtLastNameM = (EditText) findViewById(R.id.txtLastNameM);
        txtAddress = (EditText) findViewById(R.id.txtAddress);

        tilName=(TextInputLayout) findViewById(R.id.tilName);
        tilSex=(TextInputLayout) findViewById(R.id.tilSex);


    }

    public void editLoad(){
        // para obtener valores enviados de la otra actividad
        Bundle parameters = getIntent().getExtras();
        personId = parameters.getInt("personId");
        if(personId!=0){
            Person person = getPersonById(personId);
            txtName.setText(person.getName());
            txtLastNameF.setText(person.getLastNameF());
            txtLastNameM.setText(person.getLastNameM());
            txtAddress.setText(person.getAddress());

            for(int i = 0; i < spinnerSex.getCount(); i++){
                if(spinnerSex.getItemAtPosition(i).toString().equals(person.getSex())){
                    spinnerSex.setSelection(i);
                    break;
                }
            }

            // for set value to radio
            if(person.getStatus().equals("Activo")){
                radioGroupStatus.check(R.id.radioActive);
            }else{
                radioGroupStatus.check(R.id.radioInactive);
            }

            if(person.getStatusMarried().equals("Casado")){
                switchMarried.setChecked(true);
                textViewMarried.setText("Casado");
            }

            ratingBarScore.setRating(Float.parseFloat(person.getScore()));

        }
    }

    public void savePerson(){
        Button btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                boolean a = isFileValid(txtName.getText().toString());
                boolean b = isNullSex();

                if (a || b) {
                    return;
                }

                if(personId==0){
                    newPerson();
                }else{
                    updatePerson();
                }
                goMain();

            }
        });
    }
    public void radioLoad(){
        radioGroupStatus = (RadioGroup) findViewById(R.id.radioGroupStatus);
        radioActive = (RadioButton)findViewById(R.id.radioActive);
        radioInactive= (RadioButton)findViewById(R.id.radioInactive);
    }


    public void spinnerLoad(){
        spinnerSex = (Spinner)findViewById(R.id.spinner1);
        listSex = new ArrayList<String>();
        listSex.add("-- Seleccione --");
        listSex.add("Masculino");
        listSex.add("Famenino");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listSex);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(dataAdapter);
    }

    public void newPerson() {
        Random rn = new Random();
        int answer = rn.nextInt(1000) + 1;

        Person person =new Person();
        person.setId(answer);
        person.setName(txtName.getText().toString());
        person.setLastNameF(txtLastNameF.getText().toString());
        person.setLastNameM(txtLastNameM.getText().toString());
        person.setSite("www.ocalsin.com");
        person.setAddress(txtAddress.getText().toString());

        person.setSex( String.valueOf(spinnerSex.getSelectedItem()) );

        int selectedId = radioGroupStatus.getCheckedRadioButtonId();
        if(selectedId == radioActive.getId()) {
            person.setStatus("Activo");
        } else if(selectedId == radioInactive.getId()) {
            person.setStatus("Inactivo");
        }

        person.setStatusMarried(textViewMarried.getText().toString());

        person.setScore(score);

        MainActivity.listPerson.add(person);

    }

        public void updatePerson() {
        List<Person> listPerson2=new ArrayList<Person>();
        for (Person person:MainActivity.listPerson){
            if(person.getId()==personId){
                person.setName(txtName.getText().toString());
                person.setLastNameF(txtLastNameF.getText().toString());
                person.setLastNameM(txtLastNameM.getText().toString());

                person.setSex(String.valueOf(spinnerSex.getSelectedItem()));

                int selectedId = radioGroupStatus.getCheckedRadioButtonId();
                if(selectedId == radioActive.getId()) {
                    person.setStatus("Activo");
                } else if(selectedId == radioInactive.getId()) {
                    person.setStatus("Inactivo");
                }

                person.setStatusMarried(textViewMarried.getText().toString());

                person.setScore(score);
            }
            listPerson2.add(person);
        }
    }

    public void goMain(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_back:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public Person getPersonById(int personId){
        List<Person> listPerson = MainActivity.listPerson ;

        for (Person person:listPerson){
            if(person.getId()==personId){
                return person;
            }
        }
        return null;
    }

    // validations for form

    private boolean isNullSex() {
        tilSex.setError(null);
        if (String.valueOf(spinnerSex.getSelectedItem()).equals("-- Seleccione --")) {
            tilSex.setError("Seleccione sexo por favor");
            return true;
        }
        return false;

    }


    private boolean isFileValid(String file) {
        tilName.setError(null);
        if (validarFile(file,30)) {
            tilName.setError("Campo inválido");
            return true;
        }
        return false;
    }

    private boolean validarFile(String file, int sizeFile){
//        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
//        return patron.matcher(nombre).matches() || nombre.length() > sizeFile;
        return file.length() > sizeFile;
    }
    private boolean esCorreoValido(String correo){
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }
    private boolean validadTelefono(String telefono){
        return Patterns.PHONE.matcher(telefono).matches();
    }

}
