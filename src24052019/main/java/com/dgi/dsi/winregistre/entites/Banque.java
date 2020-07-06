package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;


import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name="banque")
public class Banque extends EntityBaseBean implements Serializable  {
	

	


	private static final long serialVersionUID = 1L;	
	private String code;
	
	private String designation;
	



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

	

	public Banque(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public Banque() {
		super();
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}


	

	
	

}
