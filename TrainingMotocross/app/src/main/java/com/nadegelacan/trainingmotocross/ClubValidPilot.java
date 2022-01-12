package com.nadegelacan.trainingmotocross;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nadegelacan.trainingmotocross.databinding.ActivityClubValidPilotBinding;

public class ClubValidPilot extends AppCompatActivity implements View.OnClickListener{
    private ActivityClubValidPilotBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityClubValidPilotBinding.inflate(getLayoutInflater());

        //Affichage
        setContentView(binding.getRoot());

        //remplissage des champs depuis les infos de la BDD

    }

    @Override
    public void onClick(View view) {

    }
}
