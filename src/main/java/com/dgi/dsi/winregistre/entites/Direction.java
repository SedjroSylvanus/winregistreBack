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
@Table(name = "direction", schema = "winregist")
public class Direction extends EntityBaseBean implements Serializable {



	private static final long serialVersionUID = 1L;	
	private String code;

	private String designation;

	private String email;

	private String telephone;

	@OneToMany(mappedBy="direction")
	private List<Service> services = new ArrayList<>();

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


	public List<Service> getServices() {
		return services;
	}

	@JsonIgnore
	public void setServices(List<Service> services) {
		this.services = services;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Direction(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}
	
	

	public Direction(String code, String designation, List<Service> services) {
		super();
		this.code = code;
		this.designation = designation;
		this.services = services;
	}

	public Direction() {
		super();
	}

	
	public Direction(String code, String designation, String email, String telephone, List<Service> services) {
		super();
		this.code = code;
		this.designation = designation;
		this.email = email;
		this.telephone = telephone;
		this.services = services;
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}

}
