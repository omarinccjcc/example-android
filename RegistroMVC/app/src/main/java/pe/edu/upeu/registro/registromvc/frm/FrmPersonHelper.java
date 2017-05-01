package pe.edu.upeu.registro.registromvc.frm;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.registro.registromvc.R;
import pe.edu.upeu.registro.registromvc.Register;
import pe.edu.upeu.registro.registromvc.bean.Person;

/**
 * Created by omar on 01/05/17.
 */

public class FrmPersonHelper {

    private EditText txtName,txtLastNameF,txtLastNameM,txtAddress;
    private TextInputLayout tilName,tilSex;
    private RadioGroup radioGroupStatus;
    private RadioButton radioActive,radioInactive;

    private RatingBar ratingBarScore;
    private float score;


    public FrmPersonHelper(Register register){
        txtName = (EditText) register.findViewById(R.id.txtNombre);
        txtLastNameF = (EditText) register.findViewById(R.id.txtLastNameF);
        txtLastNameM = (EditText) register.findViewById(R.id.txtLastNameM);
        txtAddress = (EditText) register.findViewById(R.id.txtAddress);

        //status
        radioGroupStatus = (RadioGroup) register.findViewById(R.id.radioGroupStatus);
        radioActive = (RadioButton)register.findViewById(R.id.radioActive);
        radioInactive= (RadioButton)register.findViewById(R.id.radioInactive);

        //score
        ratingBarScore = (RatingBar) register.findViewById(R.id.ratingBarScore);
        ratingBarScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser) {
                score=rating;
            }
        });

        tilName=(TextInputLayout) register.findViewById(R.id.tilName);
        tilSex=(TextInputLayout) register.findViewById(R.id.tilSex);


    }

    public void setPerson(Person person){
        txtName.setText(person.getName());
        txtLastNameF.setText(person.getLastNameF());
        txtLastNameM.setText(person.getLastNameM());
        txtAddress.setText(person.getAddress());

        //status
        // for set value to radio
        if(person.getStatus().equals("Activo")){
            radioGroupStatus.check(R.id.radioActive);
        }else{
            radioGroupStatus.check(R.id.radioInactive);
        }
        //score
        ratingBarScore.setRating( Float.parseFloat(person.getScore().toString())  );

    }

    public Person getPersonOfFrm(Spinner spinnerSex,TextView textViewMarried){
        Person person = new Person();
        person.setName(txtName.getText().toString());
        person.setLastNameF(txtLastNameF.getText().toString());
        person.setLastNameM(txtLastNameM.getText().toString());
        person.setAddress(txtAddress.getText().toString());
        person.setSex(String.valueOf(spinnerSex.getSelectedItem()));

        if(radioGroupStatus.getCheckedRadioButtonId() == radioActive.getId()) {
            person.setStatus("Activo");
        } else if(radioGroupStatus.getCheckedRadioButtonId() == radioInactive.getId()) {
            person.setStatus("Inactivo");
        }
        person.setStatusMarried(textViewMarried.getText().toString());
        person.setScore(Double.valueOf(score));
        return person;
    }







}
