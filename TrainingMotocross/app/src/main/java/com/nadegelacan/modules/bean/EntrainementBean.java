package com.nadegelacan.modules.bean;

import java.sql.Time;
import java.util.Date;

public class EntrainementBean {
    private Long id_entrainement;
    private String date_entrainement;
    private String heure_ouverture;
    private String heure_fermeture;
    private int prix_entrainement;
    private Boolean kid_autorise;
    private Long id_club;

    public Long getId_entrainement() {
        return id_entrainement;
    }

    public void setId_entrainement(Long id_entrainement) {
        this.id_entrainement = id_entrainement;
    }

    public String getDate_entrainement() {
        return date_entrainement;
    }

    public void setDate_entrainement(String date_entrainement) {
        this.date_entrainement = date_entrainement;
    }

    public String getHeure_ouverture() {
        return heure_ouverture;
    }

    public void setHeure_ouverture(String heure_ouverture) {
        this.heure_ouverture = heure_ouverture;
    }

    public String getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(String heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    public int getPrix_entrainement() {
        return prix_entrainement;
    }

    public void setPrix_entrainement(int prix_entrainement) {
        this.prix_entrainement = prix_entrainement;
    }

    public Boolean getKid_autorise() {
        return kid_autorise;
    }

    public void setKid_autorise(Boolean kid_autorise) {
        this.kid_autorise = kid_autorise;
    }

    public Long getId_club() {
        return id_club;
    }

    public void setId_club(Long id_club) {
        this.id_club = id_club;
    }
}
