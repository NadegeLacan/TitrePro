package com.nadegelacan.modules.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.nadegelacan.modules.bean.TrainingBean;
import com.nadegelacan.trainingmotocross.R;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter <TrainingAdapter.ViewHolder>{
    private ArrayList<TrainingBean> data;
    private String urlIcon;

    public TrainingAdapter(ArrayList<TrainingBean> data) {
        this.data = data;
    }

    public ArrayList<TrainingBean> getData() {
        return data;
    }

    public void setData(ArrayList<TrainingBean> data) {
        this.data = data;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    @NonNull
    @Override
    public TrainingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_training, parent, false);
        return new TrainingAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.ViewHolder holder, int position) {
        TrainingBean datum = data.get(position);
        holder.tvClubTraining.setText(datum.getNomClub());
        holder.tvDateTraining.setText(datum.getDateEntrainement());
        holder.tvRegion.setText(datum.getRegion());
        holder.tvAdresseClub.setText(datum.getAdresseClub());
        holder.tvVilleClub.setText(datum.getVille());
        holder.tvOpenHour.setText(datum.getHeureOuverture());
        holder.tvCloseHour.setText(datum.getHeureFermeture());
        /*if (datum.getKidAutorise()){
            holder.tvKidOK.setText("Pilotes kids acceptés");
        }
        else {
            holder.tvKidOK.setText("Pilotes kids refusés");
        }
        holder.tvPrix.setText(datum.getPrixEntrainement());*/
        //holder.ivLogoClub.
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateTraining, tvClubTraining, tvRegion, tvAdresseClub, tvVilleClub, tvOpenHour, tvCloseHour, tvKidOK, tvPrix;
        ImageView ivLogoClub;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDateTraining = itemView.findViewById(R.id.tvDateTraining);
            tvClubTraining = itemView.findViewById(R.id.tvClubTraining);
            tvRegion = itemView.findViewById(R.id.tvRegion);
            tvAdresseClub = itemView.findViewById(R.id.tvAdresseClub);
            tvVilleClub = itemView.findViewById(R.id.tvVilleClub);
            tvOpenHour = itemView.findViewById(R.id.tvOpenHour);
            tvCloseHour = itemView.findViewById(R.id.tvCloseHour);
            /*tvKidOK = itemView.findViewById(R.id.tvKidOk);
            tvPrix = itemView.findViewById(R.id.tvPrix);
            ivLogoClub = itemView.findViewById(R.id.ivLogoClub);*/
        }
    }
}
