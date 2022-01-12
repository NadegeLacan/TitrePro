package com.example.trainingmoto_serveur.bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="users")
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long idUser;
    @Column(name="mail_user")
    private String mailUser;
    @Column(name="pwd_user")
    private String pwdUser;

    /*@Override
    public String toString() {
        return "UserBean{" +
                "id_user=" + id_user +
                ", mail_user='" + mail_user + '\'' +
                ", pwd_user='" + pwd_user + '\'' +
                '}';
    }*/

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
