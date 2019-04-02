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

@Entity
@Table(name="statut")
public class Statut extends EntityBaseBean implements Serializable  {
	

	private static final long serialVersionUID = 1L;	
	
	private String code;
	
	private String designation;
	

	@OneToMany(mappedBy="statut")
	private List<AvoirStatut> avoirStatuts= new ArrayList<>();


	public List<AvoirStatut> getAvoirStatuts() {
		return avoirStatuts;
	}

	@JsonIgnore
	public void setAvoirStatuts(List<AvoirStatut> avoirStatuts) {
		this.avoirStatuts = avoirStatuts;
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

	

	public Statut(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public Statut() {
		super();
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}


	

	
	

}
