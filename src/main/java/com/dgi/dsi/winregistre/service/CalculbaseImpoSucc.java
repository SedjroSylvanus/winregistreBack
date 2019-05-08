package com.dgi.dsi.winregistre.service;


import com.dgi.dsi.winregistre.dao.ActeDao;
import com.dgi.dsi.winregistre.dao.NatureActeDao;
import com.dgi.dsi.winregistre.entites.Acte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Service
@Transactional
public class CalculbaseImpoSucc {

  @Autowired
  private NatureActeDao natureActeDao;

   @Autowired
  private ActeDao acteDao;






    }






