package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="modepaiement")
public class ModePaiement implements Serializable{
	

	private static final long serialVersionUID = 1L;	
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String code;
	
	private String designation;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation = new Date();
	
	private String encodeur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getEncodeur() {
		return encodeur;
	}

	public void setEncodeur(String encodeur) {
		this.encodeur = encodeur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


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




	public ModePaiement() {
		super();
	}


	@Override
	public String toString() {
		return "JourFerie [code=" + code + ", designation=" + designation + ","
				+ "dateCreation=" + dateCreation + ", encodeur=" + encodeur + "]";
	}

	public ModePaiement(String code, String designation, String encodeur) {
		super();
		this.code = code;
		this.designation = designation;
		this.encodeur = encodeur;
	}


	

	
	

}
