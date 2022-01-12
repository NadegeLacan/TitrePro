package com.example.trainingmoto_serveur.dao;

import com.example.trainingmoto_serveur.bean.EntrainementBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EntrainementDao extends JpaRepository<EntrainementBean, Long> {

    List<EntrainementBean> findAllByDateEntrainementAfter(Date dateEntrainement);

    //@Override
    //List<EntrainementBean> findEntrainementBeanByKid_autoriseTrue();

    //List<EntrainementBean> findAllByDate_entrainementAfterOrderByDate_entrainementAsc(Date date_entrainement);

    //@Query(value="SELECT * FROM entrainements WHERE date_entrainement>=DATE(NOW())")
    //EntrainementBean loadTraining();
    //UserBean findConnectUser(String email, String pwd);

    //@Query(value="SELECT * FROM users u WHERE u.mail_user=?1")
    //UserBean loadUser(String mail_user);
}
