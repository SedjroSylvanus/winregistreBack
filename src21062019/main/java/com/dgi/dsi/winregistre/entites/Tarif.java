package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tarif")
public class Tarif extends EntityBaseBean implements Serializable  {





	private static final long serialVersionUID = 1L;
	private String code;

	private String designation;

	@OneToMany(mappedBy = "tarif")
	private List<NatureActe> natureActes = new ArrayList<>();


	public List<NatureActe> getNatureActes() {
		return natureActes;
	}

	@JsonIgnore
	public void setNatureActes(List<NatureActe> natureActes) {
		natureActes = natureActes;
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



	public Tarif(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public Tarif() {
		super();
	}

	@Override
	public String toString() {
		return "Tarif [code=" + code + ", designation=" + designation + "]";
	}


	

	
	

}
