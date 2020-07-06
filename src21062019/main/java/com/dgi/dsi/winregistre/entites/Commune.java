package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="commune")
public class Commune extends EntityBaseBean implements Serializable{


		private static final long serialVersionUID = 1L;

	
	private String code;
	
	private String designation;
	private Boolean centreEnregistrement;


	@ManyToOne
	@NotNull(message="Le Département est obligatoire")
	private Departement departement;



	@OneToMany(mappedBy="communeActe")
	List<Acte> actes = new ArrayList<>();

	@OneToMany(mappedBy="communeSituationObjet")
	List<Acte> actesSituationObjet = new ArrayList<>();

	public List<Acte> getActes() {
		return actes;
	}

	@JsonIgnore
	public void setActes(List<Acte> actes) {
		this.actes = actes;
	}

//	@Temporal(TemporalType.TIMESTAMP)
//	private Date dateCreation = new Date();
//
//
//	private String encodeur;


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

//	public Date getDateCreation() {
//		return dateCreation;
//	}
//
//	public void setDateCreation(Date dateCreation) {
//		this.dateCreation = dateCreation;
//	}
//
//	public String getEncodeur() {
//		return encodeur;
//	}
//
//	public void setEncodeur(String encodeur) {
//		this.encodeur = encodeur;
//	}


	public List<Acte> getActesSituationObjet() {
		return actesSituationObjet;
	}


	@JsonIgnore
	public void setActesSituationObjet(List<Acte> actesSituationObjet) {
		this.actesSituationObjet = actesSituationObjet;
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

	public Boolean getCentreEnregistrement() {
		return centreEnregistrement;
	}

	public void setCentreEnregistrement(Boolean centreEnregistrement) {
		this.centreEnregistrement = centreEnregistrement;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Commune(String code, String designation, String encodeur) {
		super();
		this.code = code;
		this.designation = designation;


		}
	

	public Commune(String code, String designation, Departement departement, String encodeur) {
		super();
		this.code = code;
		this.designation = designation;
		this.departement = departement;

	}

	@Override
	public String toString() {
		return "Commune [code=" + code + ", libelle=" + designation + ", departement=" + departement + ", dateCreation="
				+ "]";
	}



	
	

}
