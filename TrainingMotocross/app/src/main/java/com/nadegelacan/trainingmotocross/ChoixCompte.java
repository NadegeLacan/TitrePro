package com.nadegelacan.trainingmotocross;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nadegelacan.trainingmotocross.databinding.ActivityChoixCompteBinding;
import com.nadegelacan.trainingmotocross.databinding.ActivityClubModifyCountBinding;

public class ChoixCompte extends AppCompatActivity implements View.OnClickListener {

    private ActivityChoixCompteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityChoixCompteBinding.inflate(getLayoutInflater());

        //Affichage
        setContentView(binding.getRoot());

        // ecoute sur le bouton valider
        binding.btValiderChoix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (binding.rbClub.isChecked()){
            Intent intent = new Intent(this,ClubNewCount.class);
            startActivity(intent);
        }
        else if (binding.rbPilote.isChecked()){
            Intent intent = new Intent(this,PilotNewCount.class);
            startActivity(intent);
        }

    }
}
