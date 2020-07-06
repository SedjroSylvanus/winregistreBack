package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="jourferie", schema = "winregist")
public class JourFerie extends EntityBaseBean implements Serializable{
	

	private static final long serialVersionUID = 1L;	

	
	private String code;
	
	private String designation;
	
//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate dateJourFerie ;
	

	



	public String getCode() {
		return code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setCode(String code) {
		this.code = code;
	}




	public JourFerie() {
		super();
	}

	public LocalDate getDateJourFerie() {
		return dateJourFerie;
	}

	public void setDateJourFerie(LocalDate dateJourFerie) {
		this.dateJourFerie = dateJourFerie;
	}

	@Override
	public String toString() {
		return "JourFerieService [code=" + code + ", designation=" + designation + ", dateJourFerie=" + dateJourFerie
				+ "]";
	}

	public JourFerie(String code, String designation, LocalDate dateJourFerie, String encodeur) {
		super();
		this.code = code;
		this.designation = designation;
		this.dateJourFerie = dateJourFerie;

	}


	

	
	

}
