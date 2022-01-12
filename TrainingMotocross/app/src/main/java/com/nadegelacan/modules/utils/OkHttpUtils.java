package com.nadegelacan.modules.utils;

import static java.sql.DriverManager.println;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class OkHttpUtils {

        public static String sendGetOkHttpRequest(String url) throws Exception {
            Log.w("tag", "url : " + url);
            OkHttpClient client = new OkHttpClient();
            //Création de la requete
            Request request = new Request.Builder().url(url).build();
            //Execution de la requête
            Response response = client.newCall(request).execute();
            System.out.println("reponse : " + response);
            //Analyse du code retour
            if (response.code() < 200 || response.code() >= 300) {
                throw new Exception("Réponse du serveur incorrect : " + response.code());
            }
            else {
                return response.body().string();
            }
        }

    public static String sendPostOkHttpRequest(String url, String paramJson )
            throws Exception {
        Log.w("tag", "url : " + url);
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //Corps de la requête
        RequestBody body = RequestBody.create(JSON, paramJson);
        //Création de la requete
        Request request = new Request.Builder().url(url).post(body).build();
        //Execution de la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if (response.code() < 200 || response.code() >= 300) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requete.
            return response.body().string();
        }
    }

    /*public static Date stringToDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }*/

}
