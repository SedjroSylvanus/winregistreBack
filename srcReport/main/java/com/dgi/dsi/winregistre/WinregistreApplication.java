package com.dgi.dsi.winregistre;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


import com.dgi.dsi.winregistre.dao.ContactRepository;


import com.dgi.dsi.winregistre.entites.modelIreport.OrderModel;
import com.dgi.dsi.winregistre.service.AccountService;

import com.dgi.dsi.winregistre.api.JourFerieService;
//import com.dgi.dsi.winregistre.service.serviceIreport.InvoiceService;
import com.dgi.dsi.winregistre.service.InvoiceService;
import com.dgi.dsi.winregistre.service.serviceIreport.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.royken.converter.FrenchNumberToWords;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.*;
//import java.util.Calendar;
//import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@SpringBootApplication
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
//@EnableJpaRepositories
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

public class WinregistreApplication implements CommandLineRunner {


//    @PersistenceContext()
//    private EntityManager entityManager;



    @Autowired
    private ObjectMapper objectMapper;



    @PostConstruct
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Autowired
    private ContactRepository contactRepository;



    @Autowired
    private AccountService accountService;


    @Bean
    public BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(WinregistreApplication.class, args);
    }

    @Resource
    private OrderService orderService;

    @Resource
    private InvoiceService invoiceService;

    Logger log = LogManager.getLogger(WinregistreApplication.class);

    @Override
    public void run(String... params) throws Exception {

        log.info("Start invoice generation...");

        OrderModel order = orderService.getOrderByCode("XYZ123456789");

//        invoiceService.generateInvoiceFor(order, Locale.FRANCE);

        log.info("Invoice generated successfully........>>>>>>>>>>>>>>>>>>>>>>");

//        accountService.saveUser(new AppUser("admin", "admin","admin","1234",123456789));
//		accountService.saveUser(new AppUser("user", "user","user","1234",147896541));
//		accountService.saveRole(new AppRole(null,"ADMIN"));
//		accountService.saveRole(new AppRole(null,"USER"));
//		accountService.addRoleToUse("admin", "ADMIN");
//		accountService.addRoleToUse("admin", "USER");
//		accountService.addRoleToUse("user", "USER");

//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2019);
//        cal.set(Calendar.MONTH, 10);
//        cal.set(Calendar.DAY_OF_MONTH, 02);
//
//        int semaine = cal.get(Calendar.DAY_OF_WEEK);
//        System.out.println("----->Jour de la semaine: " + semaine);// Afficher le numéro du jour de la semaine soit 1 pour dimanche



        JourFerieService testFonctionJF = new JourFerieService();
        LocalDate date = LocalDate.parse("2019-10-05",DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.ENGLISH));
//                LocalDate.of(2019,05,04);
        System.out.println("<<<<<<<-------->>>>>>>>>>>>>"+(date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy",Locale.ENGLISH))));
        System.out.println("<<<<<<<-------->>>>>>>>>>>>>"+(date.format(DateTimeFormatter.ofPattern("EEEE",Locale.ENGLISH))));

        System.out.println("<<<<<<<<<<<Samedi>>>>>>>>"+testFonctionJF.isSamedi("2019-10-05"));
        System.out.println("<<<<<<<<<Jour +2>>>>>><<"+date.plus(2, ChronoUnit.DAYS));


        System.out.println("<<<<<<<<<<<Dimanche>>>>>>>>"+testFonctionJF.isDimanche(date.toString()));
        System.out.println("<<<<<<<<<Jour +1>>>>>><<"+date.plus(1, ChronoUnit.DAYS));

//        if((date.format(DateTimeFormatter.ofPattern("EEEE",Locale.FRENCH))).equals("samedi")
//        || (date.format(DateTimeFormatter.ofPattern("EEEE",Locale.FRENCH))).equals("dimanche")){
//            System.out.println("OK");
//        }else{
//            System.out.println("Non Ok");
//        }
        System.out.println("<<<<<<<----JOUR---->>>>>>>>>>>>>"+(date.getDayOfWeek()));
        System.out.println(testFonctionJF.isFerie( date.toString()));


        LocalDateTime ldt = LocalDateTime.of(2018, Month.DECEMBER, 25, 13, 37, 0);
        LocalDateTime ldt2 = ldt.plus(3, ChronoUnit.DAYS);
        LocalDateTime ldt3 = ldt.minusMinutes(1337);

        System.out.println("ldt " + ldt + " ldt2 " + ldt2 + " ldt3 " + ldt3);
        Period p = Period.between(ldt.toLocalDate(), ldt2.toLocalDate());
        Duration d = Duration.between(ldt.toLocalTime(), ldt3.toLocalTime());
        System.out.println("Période : " + p);
        System.out.println("Durée : " + d.getSeconds());
        System.out.println("Ecart en jour : " + ChronoUnit.DAYS.between(ldt, ldt2));



        System.out.println("*** " + FrenchNumberToWords.convert(0));
        System.out.println("*** " + FrenchNumberToWords.convert(5));
        System.out.println("*** " + FrenchNumberToWords.convert(17));
        System.out.println("*** " + FrenchNumberToWords.convert(25));
        System.out.println("*** " + FrenchNumberToWords.convert(75));
        System.out.println("*** " + FrenchNumberToWords.convert(80));
        System.out.println("*** " + FrenchNumberToWords.convert(83));
        System.out.println("*** " + FrenchNumberToWords.convert(97));
        System.out.println("*** " + FrenchNumberToWords.convert(100));
        System.out.println("*** " + FrenchNumberToWords.convert(101));
        System.out.println("*** " + FrenchNumberToWords.convert(110));
        System.out.println("*** " + FrenchNumberToWords.convert(120));
        System.out.println("*** " + FrenchNumberToWords.convert(200));
        System.out.println("*** " + FrenchNumberToWords.convert(201));
        System.out.println("*** " + FrenchNumberToWords.convert(232));
        System.out.println("*** " + FrenchNumberToWords.convert(999));
        System.out.println("*** " + FrenchNumberToWords.convert(1001));
        System.out.println("*** " + FrenchNumberToWords.convert(10000));
        System.out.println("*** " + FrenchNumberToWords.convert(10001));
        System.out.println("*** " + FrenchNumberToWords.convert(100000));
        System.out.println("*** " + FrenchNumberToWords.convert(2000000));
        System.out.println("*** " + FrenchNumberToWords.convert(3000000000L));
        System.out.println("*** " + FrenchNumberToWords.convert(2147483647));
        System.out.println("*** " + FrenchNumberToWords.convert(5133) + " (5133 F ) FCFA");

    }


}
