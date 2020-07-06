package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="modepaiement")
public class ModePaiement  extends EntityBaseBean implements Serializable{
	

	private static final long serialVersionUID = 1L;	


	private String code;
	
	private String designation;
	


	@OneToMany(mappedBy = "modePaiement")
    private List<Quittance> quittaneModePaiement = new ArrayList<>();





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

	public List<Quittance> getQuittaneModePaiement() {
		return quittaneModePaiement;
	}

	public void setQuittaneModePaiement(List<Quittance> quittaneModePaiement) {
		this.quittaneModePaiement = quittaneModePaiement;
	}
}
