package com.dgi.dsi.winregistre.dao;

import com.dgi.dsi.winregistre.entites.DBFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {


}
