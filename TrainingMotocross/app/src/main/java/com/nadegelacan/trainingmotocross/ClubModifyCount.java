package com.nadegelacan.trainingmotocross;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nadegelacan.trainingmotocross.databinding.ActivityClubModifyCountBinding;


public class ClubModifyCount extends AppCompatActivity implements View.OnClickListener{

    private ActivityClubModifyCountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityClubModifyCountBinding.inflate(getLayoutInflater());

        //Affichage
        setContentView(binding.getRoot());

        //remplissage des champs depuis les infos de la BDD
        binding.tvIdEmail.setText("email a recuperer en bdd");
        binding.etNomClub.setText("nom a recuperer en bdd");

        // ecoute sur le bouton
        binding.btReinitialiser.setOnClickListener(this);
        binding.btValider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==binding.btReinitialiser){
            //reinitialise le formulaire
            binding = ActivityClubModifyCountBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
        }
        else if (v==binding.btValider){
            // mise à jour en bdd
        }

    }
}
