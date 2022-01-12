package com.example.trainingmoto_serveur.bean;

import javax.persistence.*;

@Entity
@Table(name="villes")
public class VilleBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ville")
    private Long idVille;
    @Column(name="code_postal")
    private String codePostal;
    private String ville;
    private String region;

    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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

   /*@Override
    public String toString() {
        return "VilleBean{" +
                "id_ville=" + id_ville +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                ", region='" + region + '\'' +
                '}';
    }*/

}
