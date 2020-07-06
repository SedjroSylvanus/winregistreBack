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
@Table(name = "institution", schema = "winregist")
public class Institution extends EntityBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;

	private String designation;
	private String adresse;

	private String telephone;
	
//	@OneToMany(mappedBy="institution")
//	private List<BordereauActe> borderauActes = new ArrayList<>();


//	public List<BordereauActe> getBorderauActes() {
//		return borderauActes;
//	}
//
//	@JsonIgnore
//	public void setBorderauActes(List<BordereauActe> borderauActes) {
//		this.borderauActes = borderauActes;
//	}

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

	
	public Institution(String code, String designation, String adresse, String telephone) {
		super();
		this.code = code;
		this.designation = designation;
		this.adresse = adresse;
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Institution(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public Institution() {
		super();
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}

}
