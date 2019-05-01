package com.dgi.dsi.winregistre;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.dgi.dsi.winregistre.dao.ClientDao;
import com.dgi.dsi.winregistre.dao.ContactRepository;


import com.dgi.dsi.winregistre.entites.modelIreport.OrderModel;
import com.dgi.dsi.winregistre.entitiesIfri.Client;
import com.dgi.dsi.winregistre.service.AccountService;

import com.dgi.dsi.winregistre.service.JourFerie;
import com.dgi.dsi.winregistre.service.serviceIreport.InvoiceService;
import com.dgi.dsi.winregistre.service.serviceIreport.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
//import java.util.Calendar;
//import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
    private ContactRepository contactRepository;

    @Autowired
    private ClientDao clientDao;

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

        invoiceService.generateInvoiceFor(order, Locale.FRANCE);

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



        JourFerie testFonctionJF = new JourFerie();
        LocalDate date = LocalDate.of(2019,05,04);
        System.out.println("<<<<<<<-------->>>>>>>>>>>>>"+(date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy",Locale.FRENCH))));
        System.out.println("<<<<<<<-------->>>>>>>>>>>>>"+(date.format(DateTimeFormatter.ofPattern("EEEE",Locale.FRENCH))));

        System.out.println("<<<<<<<<<<<Samedi>>>>>>>>"+testFonctionJF.isSamedi(date));
        System.out.println("<<<<<<<<<Jour +2>>>>>><<"+date.plus(2, ChronoUnit.DAYS));


        System.out.println("<<<<<<<<<<<Dimanche>>>>>>>>"+testFonctionJF.isDimanche(date));
        System.out.println("<<<<<<<<<Jour +1>>>>>><<"+date.plus(1, ChronoUnit.DAYS));

//        if((date.format(DateTimeFormatter.ofPattern("EEEE",Locale.FRENCH))).equals("samedi")
//        || (date.format(DateTimeFormatter.ofPattern("EEEE",Locale.FRENCH))).equals("dimanche")){
//            System.out.println("OK");
//        }else{
//            System.out.println("Non Ok");
//        }
        System.out.println("<<<<<<<-------->>>>>>>>>>>>>"+(date.getDayOfWeek()));
        System.out.println(testFonctionJF.isBankHoliday( LocalDate.of(2019,01,10)));

//        SimpleDateFormat df = new SimpleDateFormat("EEE");
//        String date = "01/05/2019";
//        System.out.println("------------->"+df.parse(date));
//
//        if(df.format(date)== "mer."){
//            System.out.println("Ok");
//        }else{
//            System.out.println("Non Ok");
//        }


//        AgentDao.save(new Agent("Wassani", "Barow", df.format("12/01/1982"), "wassan@gmail.com", 97543734, "wassan.png"));
//        System.out.println("Debut d'enregistrement dans client");
//        Client c1 = new Client(10, "Ostian", 9876);
//        Client c2 = new Client(13, "toto", 1234);
//        Client c3 = new Client(14, "tata", 8976);
        /*clientDao.save(c1);
        clientDao.save(c2);
        clientDao.save(c3);*/
//
//        List<Client> clients = clientDao.findByNomcliLike("%to%");
//
//        System.out.println(clients.get(0).getNumcli() + " " + clients.get(0).getNomcli());
    }


}
