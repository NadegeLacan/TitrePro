package com.nadegelacan.trainingmotocross;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nadegelacan.modules.bean.ClubBean;
import com.nadegelacan.modules.bean.UserBean;
import com.nadegelacan.modules.bean.VilleBean;
import com.nadegelacan.modules.utils.WSUtils;
import com.nadegelacan.trainingmotocross.databinding.ActivityClubNewCountBinding;

import java.util.ArrayList;

public class ClubNewCount extends AppCompatActivity implements View.OnClickListener{

    private ActivityClubNewCountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityClubNewCountBinding.inflate(getLayoutInflater());

        //Affichage
        setContentView(binding.getRoot());

        // ecoute sur le bouton
        binding.btReinitialiser.setOnClickListener(this);
        binding.btValider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==binding.btReinitialiser){
            //reinitialise le formulaire
            binding = ActivityClubNewCountBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
        }
        else if (v==binding.btValider){
            // enregistrement et controle des données
            try {
                saveClub();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private ClubBean saveClub() throws Exception {
        ClubBean club = new ClubBean();
        if ((binding.etNomClub.getText().length()==0) || (binding.etAdresse.getText().length()==0) ||
                (binding.etTelephone.getText().length()==0) || (binding.etNom.getText().length()==0) ||
                (binding.etPrenom.getText().length()==0) || (binding.etLongueurPiste.getText().length()==0) ||
                (binding.etNbPiloteMax.getText().length()==0) ||
                (!binding.rbMr.isChecked() && !binding.rbMme.isChecked()) ||
                (!binding.rbKidOK.isChecked() && !binding.rbKidKO.isChecked())){
            Toast.makeText(this, "Merci de renseigner tous les champs", Toast.LENGTH_SHORT).show();
        }
        else {

             Thread thread = new Thread() {
                 public void run(){
                     UserBean newUser = null;
                     try {
                         newUser = WSUtils.createUser(binding.etIdEmail.getText().toString(),binding.etPassword.getText().toString());
                         System.out.println(newUser.toString());

                     } catch (Exception e) {
                         e.printStackTrace();
                     }

                 }
            };
            thread.start();
        }
        /*else {
            club.setNomClub(binding.etNomClub.getText().toString());
            club.setAdresseClub(binding.etAdresse.getText().toString());
            club.setTelClub(binding.etTelephone.getText().toString());
            if (binding.rbMr.isChecked()){
                club.setCiviliteResponsable("Mr");
            }
            else if (binding.rbMme.isChecked()){
                club.setCiviliteResponsable("Mme");
            }
            club.setNomResponsable(binding.etNom.getText().toString());
            club.setPrenomResponsable(binding.etPrenom.getText().toString());
            if (binding.rbKidOK.isChecked()){
                club.setKidAutorise(true);
            }
            else if (binding.rbKidKO.isChecked()){
                club.setKidAutorise(false);
            }
            club.setLongueurPiste(Integer.parseInt(binding.etLongueurPiste.getText().toString()));
            club.setPiloteMaxi(Integer.parseInt(binding.etNbPiloteMax.getText().toString()));
        }*/
        return club;
    }
}
