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
@Table(name = "categorieacte")
public class CategorieActe extends EntityBaseBean implements Serializable {


	private static final long serialVersionUID = 1L;	

	private String code;

	private String designation;

	@OneToMany(mappedBy="categorieActe")
	private List<TypeActe> typeActes= new ArrayList<>();
	
	
	@OneToMany(mappedBy="categorieActe")
	private List<BordereauActe> borderauActes= new ArrayList<>();
	
	
	public List<BordereauActe> getBorderauActes() {
		return borderauActes;
	}

	@JsonIgnore
	public void setBorderauActes(List<BordereauActe> borderauActes) {
		this.borderauActes = borderauActes;
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

	
	
	public CategorieActe(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public CategorieActe() {
		super();
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}

	public List<TypeActe> getTypeActes() {
		return typeActes;
	}


	@JsonIgnore
	public void setTypeActes(List<TypeActe> typeActes) {
		this.typeActes = typeActes;
	}

}
