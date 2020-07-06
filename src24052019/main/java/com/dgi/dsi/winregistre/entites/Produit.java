package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name="produit")
public class Produit extends EntityBaseBean implements Serializable  {
	

	


	private static final long serialVersionUID = 1L;	
	private String code;
	private String designation;
	
	private Double prixUnitaire;
	private String type;
	



	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	

	public Produit(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public Produit() {
		super();
	}

	
	public Produit(String code, String designation, Double prixUnitaire, String type) {
		super();
		this.code = code;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}


	

	
	

}
