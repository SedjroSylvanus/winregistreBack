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
@Table(name = "tranche", schema = "winregist")
public class Tranche extends EntityBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private Double borneInferieur;
	private Double borneSuperieur;
	
	@OneToMany(mappedBy="tranche")
	private List<AvoirPourcentage> avoirpourcentages = new ArrayList<>();

	
	public List<AvoirPourcentage> getAvoirpourcentages() {
		return avoirpourcentages;
	}

	@JsonIgnore
	public void setAvoirpourcentages(List<AvoirPourcentage> avoirpourcentages) {
		this.avoirpourcentages = avoirpourcentages;
	}

	public Double getBorneInferieur() {
		return borneInferieur;
	}

	public void setBorneInferieur(Double borneInferieur) {
		this.borneInferieur = borneInferieur;
	}

	public Double getBorneSuperieur() {
		return borneSuperieur;
	}

	public void setBorneSuperieur(Double borneSuperieur) {
		this.borneSuperieur = borneSuperieur;
	}

	public Tranche() {
		super();
	}

	@Override
	public String toString() {
		return "Tranche [borneInferieur=" + borneInferieur + ", borneSuperieur=" + borneSuperieur + "]";
	}

	public Tranche(Double borneInferieur, Double borneSuperieur) {
		super();
		this.borneInferieur = borneInferieur;
		this.borneSuperieur = borneSuperieur;
	}

}
