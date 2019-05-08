package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="modepaiement")
public class ModePaiement  extends EntityBaseBean implements Serializable{
	

	private static final long serialVersionUID = 1L;	


	private Long id;
	
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




	public ModePaiement() {
		super();
	}


	@Override
	public String toString() {
		return "JourFerieService [code=" + code + ", designation=" + designation + ","
				 + "]";
	}

	public ModePaiement(String code, String designation, String encodeur) {
		super();
		this.code = code;
		this.designation = designation;

	}


	

	
	

}
