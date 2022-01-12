package com.example.trainingmoto_serveur.bean;

import jdk.jfr.DataAmount;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="pilotes")
public class PiloteBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pilote")
    private Long idPilote;
    @Column(name="numero_licence")
    private String numeroLicence;
    @Column(name="civilite_pilote")
    private String civilitePilote;
    @Column(name="nom_pilote")
    private String nomPilote;
    @Column(name="prenom_pilote")
    private String prenomPilote;
    @Column(name="date_naissance")
    private Date dateNaissance;
    @Column(name="adresse_pilote")
    private String adressePilote;
    @Column(name="tel_pilote")
    private String telPilote;
    @Column(name="personne_contact")
    private String personneContact;
    @Column(name="tel_personne_contact")
    private String telPersonneContact;
    @Column(name="responsable_legal")
    private String responsableLegal;
    @Column(name="id_user")
    private Long idUser;
    @Column(name="id_ville")
    private Long idVille;

    public PiloteBean() {
    }

    @Override
    public String toString() {
        return "PiloteBean{" +
                "id_pilote=" + idPilote +
                ", numero_licence='" + numeroLicence + '\'' +
                ", civilite_pilote='" + civilitePilote + '\'' +
                ", nom_pilote='" + nomPilote + '\'' +
                ", prenom_pilote='" + prenomPilote + '\'' +
                ", date_naissance=" + dateNaissance +
                ", adresse_pilote='" + adressePilote + '\'' +
                ", tel_pilote='" + telPilote + '\'' +
                ", personne_contact='" + personneContact + '\'' +
                ", tel_personne_contact='" + telPersonneContact + '\'' +
                ", responsable_legal='" + responsableLegal + '\'' +
                ", id_user=" + idUser +
                ", id_ville=" + idVille +
                '}';
    }

    public Long getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Long idPilote) {
        this.idPilote = idPilote;
    }

    public String getNumeroLicence() {
        return numeroLicence;
    }

    public void setNumeroLicence(String numeroLicence) {
        this.numeroLicence = numeroLicence;
    }

    public String getCivilitePilote() {
        return civilitePilote;
    }

    public void setCivilitePilote(String civilitePilote) {
        this.civilitePilote = civilitePilote;
    }

    public String getNomPilote() {
        return nomPilote;
    }

    public void setNomPilote(String nomPilote) {
        this.nomPilote = nomPilote;
    }

    public String getPrenomPilote() {
        return prenomPilote;
    }

    public void setPrenomPilote(String prenomPilote) {
        this.prenomPilote = prenomPilote;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdressePilote() {
        return adressePilote;
    }

    public void setAdressePilote(String adressePilote) {
        this.adressePilote = adressePilote;
    }

    public String getTelPilote() {
        return telPilote;
    }

    public void setTelPilote(String telPilote) {
        this.telPilote = telPilote;
    }

    public String getPersonneContact() {
        return personneContact;
    }

    public void setPersonneContact(String personneContact) {
        this.personneContact = personneContact;
    }

    public String getTelPersonneContact() {
        return telPersonneContact;
    }

    public void setTelPersonneContact(String telPersonneContact) {
        this.telPersonneContact = telPersonneContact;
    }

    public String getResponsableLegal() {
        return responsableLegal;
    }

    public void setResponsableLegal(String responsableLegal) {
        this.responsableLegal = responsableLegal;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }
}
