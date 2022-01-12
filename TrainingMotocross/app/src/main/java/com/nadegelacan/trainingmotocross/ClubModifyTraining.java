package com.nadegelacan.trainingmotocross;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nadegelacan.trainingmotocross.databinding.ActivityClubModifyTrainingBinding;

public class ClubModifyTraining extends AppCompatActivity implements View.OnClickListener{
    private ActivityClubModifyTrainingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityClubModifyTrainingBinding.inflate(getLayoutInflater());

        //Affichage
        setContentView(binding.getRoot());

        //remplissage de le view a partir des infos de la bdd
    }

    @Override
    public void onClick(View view) {

    }
}
