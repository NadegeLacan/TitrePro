package com.example.trainingmoto_serveur.dao;

import com.example.trainingmoto_serveur.bean.ClubBean;
import com.example.trainingmoto_serveur.bean.PiloteBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubDao extends JpaRepository<ClubBean, Long> {

    ClubBean findByIdClubEquals(Long idClub);
}
