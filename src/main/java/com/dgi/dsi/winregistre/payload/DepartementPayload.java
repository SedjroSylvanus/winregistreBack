package com.dgi.dsi.winregistre.payload;

import com.dgi.dsi.winregistre.entites.Commune;
import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DepartementPayload extends EntityBaseBean implements Serializable{


	private static final long serialVersionUID = 1L;

	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModification ;

	private String encodeur;
	private String Observation;



	private String code;

	private String designation;


	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Date getDateCreation() {
		return dateCreation;
	}

	@Override
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public Date getDateModification() {
		return dateModification;
	}

	@Override
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	@Override
	public String getEncodeur() {
		return encodeur;
	}

	@Override
	public void setEncodeur(String encodeur) {
		this.encodeur = encodeur;
	}

	@Override
	public String getObservation() {
		return Observation;
	}

	@Override
	public void setObservation(String observation) {
		Observation = observation;
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

	public DepartementPayload(Long id, Date dateCreation, Date dateModification, String encodeur, String observation, String code, String designation) {
		this.id = id;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.encodeur = encodeur;
		Observation = observation;
		this.code = code;
		this.designation = designation;
	}
}
