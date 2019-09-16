package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Audited
@Table(name="departement")
public class Departement extends EntityBaseBean implements Serializable{
	

	private static final long serialVersionUID = 1L;	
	

	private String code;
	
	private String designation;
	

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation = new Date();
	
	private String encodeur;
	
	@OneToMany(mappedBy="departement")
//	@NotAudited
	List<Commune> communes = new ArrayList<>();





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

	public Departement() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<Commune> getCommunes() {
		return communes;
	}


	@JsonIgnore
	public void setCommunes(List<Commune> communes) {
		this.communes = communes;
	}

	@Override
	public String toString() {
		return "Departement [code=" + code + ", libelle=" + designation + ", dateCreation=" + dateCreation + ", encodeur="
				+ encodeur + ", communes=" + communes + "]";
	}

	public Departement(String code, String libelle, String encodeur) {
		super();
		this.code = code;
		this.designation = libelle;
		this.encodeur = encodeur;
	}



	
	

}
