package com.dgi.dsi.winregistre.dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.dgi.dsi.winregistre.entites.AppUser;
import com.dgi.dsi.winregistre.entites.Service;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import com.dgi.dsi.winregistre.entites.Agent;


@EnableJpaRepositories ("com.dgi.dsi.winregistre.dao")
public interface AgentDao extends JpaRepository<Agent, Long>{

    Agent findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


    Agent findByMatriculeEquals(String matricule);


    @Transactional
    void deleteByUsername(String username);



//    Boolean existsByLogin(String username);

//    Boolean existsByEmail(String email);



    Optional<Agent> findById(Long pollId);



    List<Agent> findByIdIn(List<Long> pollIds);

    List<Agent> findByIdIn(List<Long> pollIds, Sort sort);

    public Agent findByMatricule(String matricule);
//    Optional<Agent> findByLogin(String login);

    List<Agent> findAllByNom(String param, String param2);

//    public List<Agent> findByNom(String nom);

//	    private SessionFactory sessionFactoryV;
//	    
//	    @Autowired 
//	    public AgentDao(SessionFactory sessionFactory) {
//	        this.sessionFactory = sessionFactory;
//	    }
//
//
//	    public Agent findById(int id) {
//	        Session session = this.sessionFactory.getCurrentSession();
//	        TypedQuery<Agent> query = session.getNamedQuery("findAccountById");  
//	        query.setParameter("id", id);
//	        Agent account = query.getSingleResult();
//	        return account;
//	    }
	
	
}
	


