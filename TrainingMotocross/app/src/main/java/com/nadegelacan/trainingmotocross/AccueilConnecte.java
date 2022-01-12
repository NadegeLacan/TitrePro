package com.nadegelacan.trainingmotocross;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.nadegelacan.modules.adapter.TrainingAdapter;
import com.nadegelacan.modules.bean.TrainingBean;
import com.nadegelacan.modules.utils.WSUtils;
import com.nadegelacan.trainingmotocross.databinding.ActivityAccueilConnecteBinding;

import java.util.ArrayList;

public class AccueilConnecte extends AppCompatActivity implements View.OnClickListener{
    private ActivityAccueilConnecteBinding binding;
    private static final int MENU_ID_CLUB_NEW_TRAINING = 17;
    private static final int MENU_ID_CLUB_VALID_PILOT = 18;
    private static final int MENU_ID_PILOT_SIGNUP_TRAINING = 19;
    private static final int MENU_ID_SHOW_TRAINING = 20;
    private ArrayList<TrainingBean> listTraining = new ArrayList<>();
    private TrainingAdapter adapter = new TrainingAdapter(listTraining);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Chargement de l'interface graphique
        binding = ActivityAccueilConnecteBinding.inflate(getLayoutInflater());

        //chargement de l'adapter
        binding.rvTraining.setAdapter(adapter);
        binding.rvTraining.setLayoutManager(new GridLayoutManager(this,1));

        //Affichage
        setContentView(binding.getRoot());

        new Thread(() -> {
            try {
                ArrayList<TrainingBean> list = WSUtils.loadTraining();
                System.out.println("loadTraining : "+list.toString());
                listTraining.clear();
                listTraining.addAll(list);
                showList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // ajout des items au menu
        menu.add(0, MENU_ID_CLUB_NEW_TRAINING, 0, "CLUB Nouveau Training");
        menu.add(0,MENU_ID_CLUB_VALID_PILOT,0,"CLUB Valider les pilotes");
        menu.add(0,MENU_ID_PILOT_SIGNUP_TRAINING,0,"PILOTE S'inscrire à un entrainement");
        menu.add(0,MENU_ID_SHOW_TRAINING,0,"Liste des entrainements");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // lancement de l'activité en fonction de la selection de l'item du menu
        if (item.getItemId() == MENU_ID_CLUB_NEW_TRAINING) {
            Intent intent = new Intent(this,ClubNewTraining.class);
            startActivity(intent);
        }
        else if (item.getItemId() == MENU_ID_CLUB_VALID_PILOT) {
            Intent intent = new Intent(this,ClubValidPilot.class);
            startActivity(intent);
        }
        /*else if (item.getItemId() == MENU_ID_PILOT_SIGNUP_TRAINING) {
            Intent intent = new Intent(this,.class);
            startActivity(intent);
        }
        else if (item.getItemId() == MENU_ID_SHOW_TRAINING) {
            Intent intent = new Intent(this,.class);
            startActivity(intent);
        }*/
        return super.onOptionsItemSelected(item);
    }

    private void showList() {
        runOnUiThread(() -> {
            adapter.notifyDataSetChanged();
        });
    }
}
