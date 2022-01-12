package com.nadegelacan.modules.bean;

import java.util.Date;

public class PiloteBean {
    private Long id_pilote;
    private String numero_licence;
    private String civilite_pilote;
    private String nom_pilote;
    private String prenom_pilote;
    private Date date_naissance;
    private String adresse_pilote;
    private String tel_pilote;
    private String personne_contact;
    private String tel_personne_contact;
    private String responsable_legal;
    private Long id_user;
    private Long id_ville;

    public Long getId_pilote() {
        return id_pilote;
    }

    public void setId_pilote(Long id_pilote) {
        this.id_pilote = id_pilote;
    }

    public String getNumero_licence() {
        return numero_licence;
    }

    public void setNumero_licence(String numero_licence) {
        this.numero_licence = numero_licence;
    }

    public String getCivilite_pilote() {
        return civilite_pilote;
    }

    public void setCivilite_pilote(String civilite_pilote) {
        this.civilite_pilote = civilite_pilote;
    }

    public String getNom_pilote() {
        return nom_pilote;
    }

    public void setNom_pilote(String nom_pilote) {
        this.nom_pilote = nom_pilote;
    }

    public String getPrenom_pilote() {
        return prenom_pilote;
    }

    public void setPrenom_pilote(String prenom_pilote) {
        this.prenom_pilote = prenom_pilote;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse_pilote() {
        return adresse_pilote;
    }

    public void setAdresse_pilote(String adresse_pilote) {
        this.adresse_pilote = adresse_pilote;
    }

    public String getTel_pilote() {
        return tel_pilote;
    }

    public void setTel_pilote(String tel_pilote) {
        this.tel_pilote = tel_pilote;
    }

    public String getPersonne_contact() {
        return personne_contact;
    }

    public void setPersonne_contact(String personne_contact) {
        this.personne_contact = personne_contact;
    }

    public String getTel_personne_contact() {
        return tel_personne_contact;
    }

    public void setTel_personne_contact(String tel_personne_contact) {
        this.tel_personne_contact = tel_personne_contact;
    }

    public String getResponsable_legal() {
        return responsable_legal;
    }

    public void setResponsable_legal(String responsable_legal) {
        this.responsable_legal = responsable_legal;
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
