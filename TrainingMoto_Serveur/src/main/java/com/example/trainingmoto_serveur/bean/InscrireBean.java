package com.example.trainingmoto_serveur.bean;

import javax.persistence.*;

@Entity
@Table(name="inscrire")
public class InscrireBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pilote")
    private Long idPilote;
    @Column(name="id_entrainement")
    private Long idEntrainement;
    @Column(name="pilote_valide")
    private Boolean piloteValide;

    public Long getIdPilote() {
        return idPilote;
    }

    public void setIdPilote(Long idPilote) {
        this.idPilote = idPilote;
    }

    public Long getIdEntrainement() {
        return idEntrainement;
    }

    public void setIdEntrainement(Long idEntrainement) {
        this.idEntrainement = idEntrainement;
    }

    public Boolean getPiloteValide() {
        return piloteValide;
    }

    public void setPiloteValide(Boolean piloteValide) {
        this.piloteValide = piloteValide;
    }

    /*@Override
    public String toString() {
        return "InscrireBean{" +
                "id_pilote=" + id_pilote +
                ", id_entrainement=" + id_entrainement +
                ", pilote_valide=" + pilote_valide +
                '}';
    }*/

}
