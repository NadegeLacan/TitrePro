package com.example.trainingmoto_serveur.ws;

import com.example.trainingmoto_serveur.bean.TrainingBean;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OkHttpUtils {

    public static String hashPass(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public static boolean verifUser(String login, String loginBDD, String password, String passwordBDD){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return ((login.equals(loginBDD)) && (passwordEncoder.matches(password, passwordBDD)));
    }

    public static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String dateStr="";
        try {
            Date date1 = dateFormat.parse(date.toString());
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateStr = formatter.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

}
