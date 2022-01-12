package com.example.trainingmoto_serveur.dao;

import com.example.trainingmoto_serveur.bean.PiloteBean;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PiloteDao extends JpaRepository<PiloteBean,Long> {

    List<PiloteBean> findAllByNomPilote (String nomPilote);

}
