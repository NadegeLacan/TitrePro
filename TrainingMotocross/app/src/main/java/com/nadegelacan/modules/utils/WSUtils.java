package com.nadegelacan.modules.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nadegelacan.modules.bean.ClubBean;
import com.nadegelacan.modules.bean.TrainingBean;
import com.nadegelacan.modules.bean.UserBean;
import com.nadegelacan.modules.bean.VilleBean;

import java.util.ArrayList;
import java.util.List;

public class WSUtils {

    public static ArrayList<TrainingBean> loadTraining () throws Exception {
        Gson g = new Gson();
        String json = OkHttpUtils.sendGetOkHttpRequest("http://176.191.236.103/GetTraining");
        System.out.println("json : " + json);
        ArrayList<TrainingBean> listTraining = g.fromJson(json, new TypeToken<ArrayList<TrainingBean>>() {
        }.getType());
        return listTraining;
    }

    public static UserBean connectUser(String pEmail, String pPwd) throws Exception {
        String url = "http://localhost:8080/getConnexion?email="+pEmail+"&pwd="+pPwd;
        System.out.println(url);
        String json = OkHttpUtils.sendGetOkHttpRequest(url);
        System.out.println(json);
        UserBean data = new Gson().fromJson(json,UserBean.class);
        return data;
    }

    public static UserBean createUser(String pMail, String pPwd) throws Exception {
        String paramJson = "{\"mail_user\": \""+pMail + "\",\"pwd_user\": \""+pPwd+"\"}";
        System.out.println(paramJson);
        String url = "http://localhost:8080/saveUser";
        System.out.println(url);
        String json = OkHttpUtils.sendPostOkHttpRequest(url,paramJson);
        System.out.println(json);
        UserBean data = new Gson().fromJson(json,UserBean.class);
        return data;
    }

    public static UserBean createClub(String pMail, String pPwd) throws Exception {
        String paramJson = "{\"mail_user\": \""+pMail + "\",\"pwd_user\": \""+pPwd+"\"}";
        System.out.println(paramJson);
        String url = "http://localhost:8080/saveUser";
        System.out.println(url);
        String json = OkHttpUtils.sendPostOkHttpRequest(url,paramJson);
        System.out.println(json);
        UserBean data = new Gson().fromJson(json,UserBean.class);
        return data;
    }

    public static UserBean createVille(String pMail, String pPwd) throws Exception {
        String paramJson = "{\"mail_user\": \""+pMail + "\",\"pwd_user\": \""+pPwd+"\"}";
        System.out.println(paramJson);
        String url = "http://localhost:8080/saveUser";
        System.out.println(url);
        String json = OkHttpUtils.sendPostOkHttpRequest(url,paramJson);
        System.out.println(json);
        UserBean data = new Gson().fromJson(json,UserBean.class);
        return data;
    }

  /*  public static VilleBean loadVille(String pCodePostal, String pVille) throws Exception {
        String url = "http://localhost:8080/getVille?email="+pEmail+"&pwd="+pPwd;
       // String paramJson = "{\"mail_user\": \""+pMail + "\",\"pwd_user\": \""+pPwd+"\"}";
       // System.out.println(paramJson);
        String url = "http://localhost:8080/saveUser";
        System.out.println(url);
       // String json = OkHttpUtils.sendPostOkHttpRequest(url,paramJson);
       // System.out.println(json);
        //VilleBean data = new Gson().fromJson(json,UserBean.class);
        return data;
    }*/

    public static ClubBean loadClub(int pIdUser) throws Exception {
        String url = "http://adresseAPI/idUser="+pIdUser;
        System.out.println(url);
        String json = OkHttpUtils.sendGetOkHttpRequest(url);
        System.out.println(json);
        ClubBean data = new Gson().fromJson(json,ClubBean.class);
        return data;
    }

    public static ArrayList<VilleBean> loadVille(String pCodePostal) throws Exception {
        Gson gson = new Gson();
        String url = "https://geo.api.gouv.fr/communes?codePostal="+pCodePostal;
        System.out.println(url);
        String json = OkHttpUtils.sendGetOkHttpRequest(url);
        System.out.println(json);
        ArrayList<VilleBean> list = gson.fromJson(json,new TypeToken<ArrayList<VilleBean>>(){}.getType());
        return list;
    }

}
