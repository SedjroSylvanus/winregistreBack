package com.dgi.dsi.winregistre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.dgi.dsi.winregistre.dao.ContactRepository;


import com.dgi.dsi.winregistre.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
//@EnableJpaRepositories
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

public class WinregistreApplication implements CommandLineRunner {


    @PersistenceContext()
    private EntityManager entityManager;

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

    @Override
    public void run(String... params) throws Exception {

//        accountService.saveUser(new AppUser("admin", "admin","admin","1234",123456789));
//		accountService.saveUser(new AppUser("user", "user","user","1234",147896541));
//		accountService.saveRole(new AppRole(null,"ADMIN"));
//		accountService.saveRole(new AppRole(null,"USER"));
//		accountService.addRoleToUse("admin", "ADMIN");
//		accountService.addRoleToUse("admin", "USER");
//		accountService.addRoleToUse("user", "USER");

//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        AgentDao.save(new Agent("Wassani", "Barow", df.parse("12/01/1982"), "wassan@gmail.com", 97543734, "wassan.png"));

    }


}
