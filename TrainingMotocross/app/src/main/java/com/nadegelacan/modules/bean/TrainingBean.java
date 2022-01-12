package com.nadegelacan.modules.bean;

import java.sql.Time;
import java.util.Date;

public class TrainingBean {
    private Long idEntrainement;
    private String dateEntrainement;
    private String heureOuverture;
    private String heureFermeture;
    private int prixEntrainement;
    private Boolean kidAutorise;
    private Long idClub;
    private String nomClub;
    private String adresseClub;
    private String telClub;
    private int longueurPiste;
    private int piloteMaxi;
    private String civiliteResponsable;
    private String nomResponsable;
    private String prenomResponsable;
    private Long idVille;
    private String code_postal;
    private String ville;
    private String region;

    public Long getIdEntrainement() {
        return idEntrainement;
    }

    public void setIdEntrainement(Long idEntrainement) {
        this.idEntrainement = idEntrainement;
    }

    public String getDateEntrainement() {
        return dateEntrainement;
    }

    public void setDateEntrainement(String dateEntrainement) {
        this.dateEntrainement = dateEntrainement;
    }

    public String getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(String heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public String getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(String heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public int getPrixEntrainement() {
        return prixEntrainement;
    }

    public void setPrixEntrainement(int prixEntrainement) {
        this.prixEntrainement = prixEntrainement;
    }

    public Boolean getKidAutorise() {
        return kidAutorise;
    }

    public void setKidAutorise(Boolean kidAutorise) {
        this.kidAutorise = kidAutorise;
    }

    public Long getIdClub() {
        return idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public String getNomClub() {
        return nomClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public String getAdresseClub() {
        return adresseClub;
    }

    public void setAdresseClub(String adresseClub) {
        this.adresseClub = adresseClub;
    }

    public String getTelClub() {
        return telClub;
    }

    public void setTelClub(String telClub) {
        this.telClub = telClub;
    }

    public int getLongueurPiste() {
        return longueurPiste;
    }

    public void setLongueurPiste(int longueurPiste) {
        this.longueurPiste = longueurPiste;
    }

    public int getPiloteMaxi() {
        return piloteMaxi;
    }

    public void setPiloteMaxi(int piloteMaxi) {
        this.piloteMaxi = piloteMaxi;
    }

    public String getCiviliteResponsable() {
        return civiliteResponsable;
    }

    public void setCiviliteResponsable(String civiliteResponsable) {
        this.civiliteResponsable = civiliteResponsable;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public String getPrenomResponsable() {
        return prenomResponsable;
    }

    public void setPrenomResponsable(String prenomResponsable) {
        this.prenomResponsable = prenomResponsable;
    }

    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
