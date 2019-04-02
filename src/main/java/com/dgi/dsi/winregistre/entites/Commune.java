package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="commune")
public class Commune implements Serializable{
	

	private static final long serialVersionUID = 1L;	
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String code;
	
	private String libelle;
	

	@ManyToOne
	@NotNull(message="Le Département est obligatoire")
	private Departement departement;
	
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

	public Commune() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Commune(String code, String libelle, String encodeur) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.encodeur = encodeur;
	}
	
	

	public Commune(String code, String libelle, Departement departement, String encodeur) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.departement = departement;
		this.encodeur = encodeur;
	}

	@Override
	public String toString() {
		return "Commune [code=" + code + ", libelle=" + libelle + ", departement=" + departement + ", dateCreation="
				+ dateCreation + ", encodeur=" + encodeur + "]";
	}









	
	

}
