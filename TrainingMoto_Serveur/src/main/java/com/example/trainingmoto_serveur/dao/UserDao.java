package com.example.trainingmoto_serveur.dao;

import com.example.trainingmoto_serveur.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserBean,Long> {

}
