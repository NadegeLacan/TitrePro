package com.nadegelacan.trainingmotocross;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nadegelacan.modules.adapter.TrainingAdapter;
import com.nadegelacan.modules.bean.TrainingBean;
import com.nadegelacan.modules.bean.UserBean;
import com.nadegelacan.modules.utils.OkHttpUtils;
import com.nadegelacan.modules.utils.WSUtils;
import com.nadegelacan.trainingmotocross.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private ArrayList<TrainingBean> listTraining = new ArrayList<>();
    private TrainingAdapter adapter = new TrainingAdapter(listTraining);
    private boolean connectVisibility = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // chargement de l'interface graphique
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        showProgressBar(false);
        showConnection(false);

        //chargement de l'adapter
        binding.rvTraining.setAdapter(adapter);
        binding.rvTraining.setLayoutManager(new GridLayoutManager(this,1));

        // affichage
        setContentView(binding.getRoot());

        // ecoute sur le bouton connexion
        binding.btConnect.setOnClickListener(this);

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
        if (v==binding.btConnect){
            if (!connectVisibility){
                showConnection(true);
            }
            else {
                //vérifier les infos de connexion et lancer la page accueil connecté
                showProgressBar(true);
                new Thread(() -> {
                    try {
                        UserBean user = new UserBean(binding.etMailuser.getText().toString(), binding.etPassUser.getText().toString());
                        String json = "{\"mailUser\":\"" + user.getMailUser() + "\",\"pwdUser\":\"" + user.getPwdUser() + "\"}";
                        System.out.println(json);
                        String userOK = OkHttpUtils.sendPostOkHttpRequest("http://176.191.236.103/login", json);
                        if (userOK.equals("0")) {
                            showErrorMessage("Utilisateur ou mot de passe invalide");
                        } else if (userOK.equals("-1")) {
                            showErrorMessage("Utilisateur ou mot de passe non trouvé");
                        } else {
                            // lancer la page menu connecté
                            Intent intent = new Intent(this, AccueilConnecte.class);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    showProgressBar(false);
                }).start();
            }

        }
    }
    /* -------------------------------- */
    // Mise àn jour UI
    /* -------------------------------- */

    private void showErrorMessage(String errorMessage) {
        runOnUiThread(() -> binding.tvError.setText(errorMessage));
    }

    //Affiche masque la progressBar
    private void showProgressBar(boolean visible) {
        runOnUiThread(() -> binding.pb.setVisibility(visible ? View.VISIBLE : View.GONE));
    }

    private void showConnection(boolean visible) {
        runOnUiThread(() -> binding.llMail.setVisibility(visible ? View.VISIBLE : View.GONE));
        runOnUiThread(() -> binding.llPass.setVisibility(visible ? View.VISIBLE : View.GONE));
        connectVisibility = visible;

    }

    private void showList() {
        runOnUiThread(() -> {
            adapter.notifyDataSetChanged();
        });
    }
}