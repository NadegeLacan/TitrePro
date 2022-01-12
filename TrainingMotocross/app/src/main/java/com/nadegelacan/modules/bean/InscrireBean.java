package com.nadegelacan.modules.bean;

public class InscrireBean {
    private Long id_pilote;
    private Long id_entrainement;
    private Boolean pilote_valide;

    public Long getId_pilote() {
        return id_pilote;
    }

    public void setId_pilote(Long id_pilote) {
        this.id_pilote = id_pilote;
    }

    public Long getId_entrainement() {
        return id_entrainement;
    }

    public void setId_entrainement(Long id_entrainement) {
        this.id_entrainement = id_entrainement;
    }

    public Boolean getPilote_valide() {
        return pilote_valide;
    }

    public void setPilote_valide(Boolean pilote_valide) {
        this.pilote_valide = pilote_valide;
    }
}
