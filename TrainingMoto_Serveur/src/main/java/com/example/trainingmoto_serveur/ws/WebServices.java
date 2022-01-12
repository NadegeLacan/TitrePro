package com.example.trainingmoto_serveur.ws;

import com.example.trainingmoto_serveur.bean.*;
import com.example.trainingmoto_serveur.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

@RestController
public class WebServices {
    @Autowired
    private UserDao userDAO;
    @Autowired
    private ClubDao clubDAO;
    @Autowired
    private EntrainementDao entrainementDAO;
    @Autowired
    private PiloteDao piloteDAO;
    @Autowired
    private VilleDao villeDAO;
    @Autowired
    private InscrireDao inscrireDAO;

    @GetMapping("/HelloWorld")
    public String HelloWord() {
        System.out.println("/HelloWorld");
        return "Hello World";
    }

    @GetMapping("/GetPilote")
    public List<PiloteBean> GetPilote() {
        System.out.println("/GetPilote");
        return piloteDAO.findAll();
    }

    //http://localhost:8080/GetPiloteName?name=lacan
    @GetMapping("/GetPiloteName")
    public List<PiloteBean> GetPiloteName(String name) {
        System.out.println("/GetPiloteName "+name);
        return piloteDAO.findAllByNomPilote(name);
    }

    //http://localhost:8080/GetTrainingTest
    @GetMapping("/GetTrainingTest")
    public List<EntrainementBean> GetTrainingTest() {
        System.out.println("/GetTrainingTest");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return entrainementDAO.findAllByDateEntrainementAfter(date);
    }

    //http://localhost:8080/GetTraining
    @GetMapping("/GetTraining")
    public ArrayList<TrainingBean> GetTraining() {
        System.out.println("/GetTraining");
        ArrayList<TrainingBean> trainingBeanArrayList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        List<EntrainementBean> entrainementBeanList = entrainementDAO.findAllByDateEntrainementAfter(date);
        for (int i=0;i<entrainementBeanList.size();i++){
            Long id = entrainementBeanList.get(i).getIdEntrainement();
            String dateEntrainement = OkHttpUtils.dateToString(entrainementBeanList.get(i).getDateEntrainement());
            String timeOuverture = entrainementBeanList.get(i).getHeureOuverture().toString();
            String timeFermeture = entrainementBeanList.get(i).getHeureFermeture().toString();
            int prix = entrainementBeanList.get(i).getPrixEntrainement();
            boolean kid = entrainementBeanList.get(i).getKidAutorise();
            Long idClub = entrainementBeanList.get(i).getIdClub();
            TrainingBean trainingBean = new TrainingBean(id, dateEntrainement,timeOuverture,timeFermeture,prix,kid,idClub);
            ClubBean club = clubDAO.findByIdClubEquals(idClub);
            VilleBean ville = villeDAO.findByIdVilleEquals(club.getIdVille());
            trainingBean.setNomClub(club.getNomClub());
            trainingBean.setAdresseClub(club.getAdresseClub());
            trainingBean.setTelClub(club.getTelClub());
            trainingBean.setLongueurPiste(club.getLongueurPiste());
            trainingBean.setPiloteMaxi(club.getPiloteMaxi());
            trainingBean.setCiviliteResponsable(club.getCiviliteResponsable());
            trainingBean.setNomResponsable(club.getNomResponsable());
            trainingBean.setPrenomResponsable(club.getPrenomResponsable());
            trainingBean.setIdVille(club.getIdVille());
            trainingBean.setCode_postal(ville.getCodePostal());
            trainingBean.setVille(ville.getVille());
            trainingBean.setRegion(ville.getRegion());
            trainingBeanArrayList.add(trainingBean);
        }
        return trainingBeanArrayList;
    }

    //http://localhost:8080/login
    // JSON attendu : {
    //  "mailUser":"castelnau@orange.fr",
    //  "pwdUser":"password"
    //}
    // retour : 0 : "Utilisateur ou mot de passe invalide"
    // retour : -1 : "Utilisateur ou mot de passe introuvable"
    // retour : x : "OK utilisateur indentifié, retour de l'id_user"
    @PostMapping("/login")
    public int login(@RequestBody UserBean user) throws Exception {
        System.out.println("/login");
        System.out.println(user.getMailUser()+user.getPwdUser());
        List<UserBean> userBDD = userDAO.findAll();
        if (user.getMailUser()==null || user.getPwdUser()==null || user.getMailUser().trim().isEmpty() || user.getPwdUser().trim().isEmpty()){
            return 0;
        }

        for (int i=0; i<userBDD.size();i++){
            if (OkHttpUtils.verifUser(user.getMailUser(), userBDD.get(i).getMailUser(), user.getPwdUser(), userBDD.get(i).getPwdUser())){
                return userBDD.get(i).getIdUser().intValue();
            }
        }
        return -1;
    }


    /*@GetMapping("/GetAllTraining")
    public List<EntrainementBean> GetAllTraining() {
        System.out.println("/GetAllTraining");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        //return entrainementDAO.findAll();
        return entrainementDAO.findAllByDate_entrainementAfterOrderByDate_entrainementAsc(date);
    }*/

    /*
    //http://localhost:8080/login
    // JSON attendu :
    @PostMapping("/login")
    public String login(@RequestBody UserBean user) throws Exception {
        System.out.println("/login");
        System.out.println(user.getUser_login()+user.getUser_pwd());
        List<UserBean> userBDD = userDAO.findAll();
        String userAPI="";
        Random rand = new Random();
        if (user.getUser_login()==null || user.getUser_pwd()==null || user.getUser_login().trim().isEmpty() || user.getUser_pwd().trim().isEmpty()){
            return "Utilisateur ou mot de passe invalide";
        }

        for (int i=0; i<userBDD.size();i++){
            if (OkHttpUtils.verifUser(user.getUser_login(), userBDD.get(i).getUser_login(), user.getUser_pwd(), userBDD.get(i).getUser_pwd())){
                return userBDD.get(i).getUser_api_key();
            }
        }
        // boucle pour générer une clé API
        for(int i = 0 ; i < 20 ; i++){
            char c = (char)(rand.nextInt(26) + 97);
            userAPI += c;
        }

        //sauvegarde du nouveau user en BDD
        user.setUser_api_key(userAPI);
        String pwd = user.getUser_pwd();
        user.setUser_pwd(OkHttpUtils.hashPass(pwd));
        userDAO.save(user);
        return userAPI;
    }*/

}
