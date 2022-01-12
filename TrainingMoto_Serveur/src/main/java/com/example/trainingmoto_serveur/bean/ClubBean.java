package com.example.trainingmoto_serveur.bean;

import javax.persistence.*;

@Entity
@Table(name="clubs")
public class ClubBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_club")
    private Long idClub;
    @Column(name="nom_club")
    private String nomClub;
    @Column(name="adresse_club")
    private String adresseClub;
    @Column(name="tel_club")
    private String telClub;
    @Column(name="longueur_piste")
    private int longueurPiste;
    @Column(name="pilote_maxi")
    private int piloteMaxi;
    @Column(name="kid_autorise")
    private boolean kidAutorise;
    @Column(name="civilite_responsable")
    private String civiliteResponsable;
    @Column(name="nom_responsable")
    private String nomResponsable;
    @Column(name="prenom_responsable")
    private String prenomResponsable;
    @Column(name="id_user")
    private Long idUser;
    @Column(name="id_ville")
    private Long idVille;

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

    public boolean isKidAutorise() {
        return kidAutorise;
    }

    public void setKidAutorise(boolean kidAutorise) {
        this.kidAutorise = kidAutorise;
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

    /*
    @Override
    public String toString() {
        return "ClubBean{" +
                "id_lub=" + id_club +
                ", nom_club='" + nom_club + '\'' +
                ", adresse_club='" + adresse_club + '\'' +
                ", tel_club='" + tel_club + '\'' +
                ", longueur_piste=" + longueur_piste +
                ", pilote_maxi=" + pilote_maxi +
                ", kid_autorise=" + kid_autorise +
                ", civilite_responsable='" + civilite_responsable + '\'' +
                ", nom_responsable='" + nom_responsable + '\'' +
                ", prenom_responsable='" + prenom_responsable + '\'' +
                ", id_user=" + id_user +
                ", id_ville=" + id_ville +
                '}';
    }*/
}
