package com.example.trainingmoto_serveur.dao;

import com.example.trainingmoto_serveur.bean.VilleBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleDao extends JpaRepository<VilleBean,Long> {
    VilleBean findByIdVilleEquals (Long idVille);
}
