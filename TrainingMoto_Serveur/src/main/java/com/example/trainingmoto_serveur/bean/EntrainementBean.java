package com.example.trainingmoto_serveur.bean;

import javax.persistence.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name="entrainements")
public class EntrainementBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_entrainement")
    private Long idEntrainement;
    @Column(name="date_entrainement")
    private Date dateEntrainement;
    @Column(name="heure_ouverture")
    private Time heureOuverture;
    @Column(name="heure_fermeture")
    private Time heureFermeture;
    @Column(name="prix_entrainement")
    private int prixEntrainement;
    @Column(name="kid_autorise")
    private Boolean kidAutorise;
    @Column(name="id_club")
    private Long idClub;

    public Long getIdEntrainement() {
        return idEntrainement;
    }

    public void setIdEntrainement(Long idEntrainement) {
        this.idEntrainement = idEntrainement;
    }

    public Date getDateEntrainement() {
        return dateEntrainement;
    }

    public void setDateEntrainement(Date dateEntrainement) {

        this.dateEntrainement = dateEntrainement;
    }

    public Time getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(Time heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public Time getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(Time heureFermeture) {
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

    /*@Override
    public String toString() {
        return "EntrainementBean{" +
                "id_entrainement=" + id_entrainement +
                ", date_entrainement=" + date_entrainement +
                ", heure_ouverture=" + heure_ouverture +
                ", heure_fermeture=" + heure_fermeture +
                ", prix_entrainement=" + prix_entrainement +
                ", kid_autorise=" + kid_autorise +
                ", id_club=" + id_club +
                '}';
    }*/

}
