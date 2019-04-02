package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="degresuccession")
public class DegreSuccession extends EntityBaseBean implements Serializable  {
	

	


	private static final long serialVersionUID = 1L;	
	private String code;
	
	private String designation;
	
	@OneToMany(mappedBy="degreSuccession")
	private List<AvoirPourcentage> avoirpourcentages = new ArrayList<>();



	public List<AvoirPourcentage> getAvoirpourcentages() {
		return avoirpourcentages;
	}

	@JsonIgnore
	public void setAvoirpourcentages(List<AvoirPourcentage> avoirpourcentages) {
		this.avoirpourcentages = avoirpourcentages;
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

	

	public DegreSuccession(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public DegreSuccession() {
		super();
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}


	

	
	

}
