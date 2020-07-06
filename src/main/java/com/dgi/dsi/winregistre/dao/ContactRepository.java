package com.dgi.dsi.winregistre.dao;




import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {


	public Contact findByIdIs(String id);
	public Contact findByIdIs(Long id);
	@Query("select c from Contact c where c.nom like :x")
	public Page<Contact> chercher(@Param("x") String mc, Pageable pageable);
}
