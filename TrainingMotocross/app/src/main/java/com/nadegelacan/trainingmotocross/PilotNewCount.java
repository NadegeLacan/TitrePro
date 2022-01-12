package com.nadegelacan.trainingmotocross;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.nadegelacan.trainingmotocross.databinding.ActivityPilotNewCountBinding;

import java.util.Calendar;

public class PilotNewCount extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener, DatePickerDialog.OnDateSetListener{
    private ActivityPilotNewCountBinding binding;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityPilotNewCountBinding.inflate(getLayoutInflater());

        //Affichage
        setContentView(binding.getRoot());

        // ecoute sur le bouton
        binding.btReinitialiser.setOnClickListener(this);
        binding.btValider.setOnClickListener(this);

        //ecoute changement focus date naissance
        binding.etDateNaiss.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==binding.btReinitialiser){
            //reinitialise le formulaire
            binding = ActivityPilotNewCountBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
        }
        else if (v==binding.btValider){
            // sauvegarde en bdd
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        // sur le focus de la date de naissance lancement d'un datepicker
        if (view==binding.etDateNaiss && b){
            new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        if (month!=12){
            month++;
        }
        else {
            month=1;
        }
        binding.etDateNaiss.setText(String.format("%02d/%02d/%04d",day,month,year));
    }
}
