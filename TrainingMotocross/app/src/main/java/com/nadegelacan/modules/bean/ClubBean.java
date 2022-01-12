package com.nadegelacan.modules.bean;

public class ClubBean {
    private Long id_club;
    private String nom_club;
    private String adresse_club;
    private String tel_club;
    private int longueur_piste;
    private int pilote_maxi;
    private boolean kid_autorise;
    private String civilite_responsable;
    private String nom_responsable;
    private String prenom_responsable;
    private Long id_user;
    private Long id_ville;

    public Long getId_club() {
        return id_club;
    }

    public void setId_club(Long id_club) {
        this.id_club = id_club;
    }

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getAdresse_club() {
        return adresse_club;
    }

    public void setAdresse_club(String adresse_club) {
        this.adresse_club = adresse_club;
    }

    public String getTel_club() {
        return tel_club;
    }

    public void setTel_club(String tel_club) {
        this.tel_club = tel_club;
    }

    public int getLongueur_piste() {
        return longueur_piste;
    }

    public void setLongueur_piste(int longueur_piste) {
        this.longueur_piste = longueur_piste;
    }

    public int getPilote_maxi() {
        return pilote_maxi;
    }

    public void setPilote_maxi(int pilote_maxi) {
        this.pilote_maxi = pilote_maxi;
    }

    public boolean isKid_autorise() {
        return kid_autorise;
    }

    public void setKid_autorise(boolean kid_autorise) {
        this.kid_autorise = kid_autorise;
    }

    public String getCivilite_responsable() {
        return civilite_responsable;
    }

    public void setCivilite_responsable(String civilite_responsable) {
        this.civilite_responsable = civilite_responsable;
    }

    public String getNom_responsable() {
        return nom_responsable;
    }

    public void setNom_responsable(String nom_responsable) {
        this.nom_responsable = nom_responsable;
    }

    public String getPrenom_responsable() {
        return prenom_responsable;
    }

    public void setPrenom_responsable(String prenom_responsable) {
        this.prenom_responsable = prenom_responsable;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId_ville() {
        return id_ville;
    }

    public void setId_ville(Long id_ville) {
        this.id_ville = id_ville;
    }
}
