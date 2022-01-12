package com.nadegelacan.modules.bean;

import java.util.ArrayList;

public class VilleBean {
    private Long id_ville;
    private String code_postal;
    private String ville;
    private String region;

    public Long getId_ville() {
        return id_ville;
    }

    public void setId_ville(Long id_ville) {
        this.id_ville = id_ville;
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
