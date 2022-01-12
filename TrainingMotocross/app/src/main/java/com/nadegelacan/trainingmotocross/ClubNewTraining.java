package com.nadegelacan.trainingmotocross;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.nadegelacan.modules.bean.EntrainementBean;
import com.nadegelacan.modules.utils.OkHttpUtils;
import com.nadegelacan.trainingmotocross.databinding.ActivityClubNewTrainingBinding;

import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class ClubNewTraining extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private ActivityClubNewTrainingBinding binding;
    private Calendar calendar = Calendar.getInstance();
    private Boolean selectTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityClubNewTrainingBinding.inflate(getLayoutInflater());

        //Affichage
        setContentView(binding.getRoot());

        // ecoute sur les boutons
        binding.btReinitialiser.setOnClickListener(this);
        binding.btValider.setOnClickListener(this);

        // ecoute sur changement de focus champs date et heure
        binding.etDateOuverture.setOnFocusChangeListener((View.OnFocusChangeListener) this);
        binding.etHeureOuverture.setOnFocusChangeListener((View.OnFocusChangeListener) this);
        binding.etHeureFermeture.setOnFocusChangeListener((View.OnFocusChangeListener) this);
    }

    @Override
    public void onClick(View v) {
        if (v==binding.btReinitialiser){
            //reinitialise le formulaire
            binding = ActivityClubNewTrainingBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
        }
        else if (v==binding.btValider){
            // sauvegarde en bdd
            EntrainementBean entrainement = new EntrainementBean();
            entrainement.setDate_entrainement(binding.etDateOuverture.getText().toString());
            entrainement.setHeure_ouverture(binding.etHeureOuverture.getText().toString());
            entrainement.setHeure_fermeture(binding.etHeureFermeture.getText().toString());
        }

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view==binding.etDateOuverture && b){
            new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if (view==binding.etHeureOuverture && b){
            selectTimePicker = false;
            new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }
        else if (view==binding.etHeureFermeture && b){
            selectTimePicker = true;
            new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
        }
    }

    /* -------------------------------- */
    // Callback picker
    /* -------------------------------- */

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (selectTimePicker){
            binding.etHeureFermeture.setText(String.format("%02d:%02d",hourOfDay,minute));
        }
        else {
            binding.etHeureOuverture.setText(String.format("%02d:%02d",hourOfDay,minute));
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (month!=12){
            month++;
        }
        else {
            month=1;
        }
        binding.etDateOuverture.setText(String.format("%02d/%02d/%04d",dayOfMonth,month,year));
    }
}
