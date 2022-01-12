package com.example.trainingmoto_serveur.dao;

import com.example.trainingmoto_serveur.bean.InscrireBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscrireDao extends JpaRepository<InscrireBean, Long> {
}
