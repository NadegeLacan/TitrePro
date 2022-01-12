package com.nadegelacan.modules.bean;

public class UserBean {
    private Long idUser;
    private String mailUser;
    private String pwdUser;

    public UserBean(String mailUser, String pwdUser) {
        this.mailUser = mailUser;
        this.pwdUser = pwdUser;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getPwdUser() {
        return pwdUser;
    }

    public void setPwdUser(String pwdUser) {
        this.pwdUser = pwdUser;
    }
}
