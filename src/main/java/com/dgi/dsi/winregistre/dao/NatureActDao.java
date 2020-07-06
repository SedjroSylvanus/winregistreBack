package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.NatureActe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.dgi.dsi.winregistre.dao")
public interface NatureActDao  extends JpaRepository<NatureActe, Long> {

}
