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
@Table(name="natureImpot", schema = "winregist")
public class NatureImpot extends EntityBaseBean implements Serializable  {





	private static final long serialVersionUID = 1L;
	private String code;

	private String designation;

	@OneToMany(mappedBy = "natureImpot")
    private List<Quittance> QuittancesNatueImpot = new ArrayList<>();


	public List<Quittance> getQuittancesNatueImpot() {
		return QuittancesNatueImpot;
	}

	@JsonIgnore
	public void setQuittancesNatueImpot(List<Quittance> quittancesNatueImpot) {
		QuittancesNatueImpot = quittancesNatueImpot;
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



	public NatureImpot(String code, String designation) {
		super();
		this.code = code;
		this.designation = designation;
	}

	public NatureImpot() {
		super();
	}

	@Override
	public String toString() {
		return "Banque [code=" + code + ", designation=" + designation + "]";
	}

}
