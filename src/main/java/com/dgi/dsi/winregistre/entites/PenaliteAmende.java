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

import org.springframework.format.number.money.MonetaryAmountFormatter;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "penaliteamende")
public class PenaliteAmende extends EntityBaseBean implements Serializable {


	private static final long serialVersionUID = 1L;	
	private String codePenaliteAmende;
	private String libellePenaliteAmende;

	private String periodicite;
	private Double mtPenaliteAmende;
	private Integer dalaiEcheance;
	private String grille;

	private Double mtMinimum;
	private Double mtMaximum;
	private String jourMoisAnnee;
	private Double mtPenaliteEcheance;
	private Double droitSimpleMinimum;

	@OneToMany(mappedBy = "penaliteAmende")
	private List<TypeActe> typeActes = new ArrayList<>();

	public PenaliteAmende() {
		super();
	}

	public String getCodePenaliteAmende() {
		return codePenaliteAmende;
	}

	public void setCodePenaliteAmende(String codePenaliteAmende) {
		this.codePenaliteAmende = codePenaliteAmende;
	}

	public String getLibellePenaliteAmende() {
		return libellePenaliteAmende;
	}

	public void setLibellePenaliteAmende(String libellePenaliteAmende) {
		this.libellePenaliteAmende = libellePenaliteAmende;
	}

	public String getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public Double getMtPenaliteAmende() {
		return mtPenaliteAmende;
	}

	public void setMtPenaliteAmende(Double mtPenaliteAmende) {
		this.mtPenaliteAmende = mtPenaliteAmende;
	}

	public Integer getDalaiEcheance() {
		return dalaiEcheance;
	}

	public void setDalaiEcheance(Integer dalaiEcheance) {
		this.dalaiEcheance = dalaiEcheance;
	}

	public String getGrille() {
		return grille;
	}

	public void setGrille(String grille) {
		this.grille = grille;
	}

	public Double getMtMinimum() {
		return mtMinimum;
	}

	public void setMtMinimum(Double mtMinimum) {
		this.mtMinimum = mtMinimum;
	}

	public Double getMtMaximum() {
		return mtMaximum;
	}

	public void setMtMaximum(Double mtMaximum) {
		this.mtMaximum = mtMaximum;
	}

	public String getJourMoisAnnee() {
		return jourMoisAnnee;
	}

	public void setJourMoisAnnee(String jourMoisAnnee) {
		this.jourMoisAnnee = jourMoisAnnee;
	}

	public Double getMtPenaliteEcheance() {
		return mtPenaliteEcheance;
	}

	public void setMtPenaliteEcheance(Double mtPenaliteEcheance) {
		this.mtPenaliteEcheance = mtPenaliteEcheance;
	}

	public Double getDroitSimpleMinimum() {
		return droitSimpleMinimum;
	}

	public void setDroitSimpleMinimum(Double droitSimpleMinimum) {
		this.droitSimpleMinimum = droitSimpleMinimum;
	}

	@Override
	public String toString() {
		return "PenaliteAmende [codePenaliteAmende=" + codePenaliteAmende + ", libellePenaliteAmende="
				+ libellePenaliteAmende + ", periodicite=" + periodicite + ", mtPenaliteAmende=" + mtPenaliteAmende
				+ ", dalaiEcheance=" + dalaiEcheance + ", grille=" + grille + ", mtMinimum=" + mtMinimum
				+ ", mtMaximum=" + mtMaximum + ", jourMoisAnnee=" + jourMoisAnnee + ", mtPenaliteEcheance="
				+ mtPenaliteEcheance + ", droitSimpleMinimum=" + droitSimpleMinimum + "]";
	}

	public PenaliteAmende(String codePenaliteAmende, String libellePenaliteAmende, String periodicite,
			Double mtPenaliteAmende, Integer dalaiEcheance, String grille, Double mtMinimum, Double mtMaximum,
			String jourMoisAnnee, Double mtPenaliteEcheance, Double droitSimpleMinimum) {
		super();
		this.codePenaliteAmende = codePenaliteAmende;
		this.libellePenaliteAmende = libellePenaliteAmende;
		this.periodicite = periodicite;
		this.mtPenaliteAmende = mtPenaliteAmende;
		this.dalaiEcheance = dalaiEcheance;
		this.grille = grille;
		this.mtMinimum = mtMinimum;
		this.mtMaximum = mtMaximum;
		this.jourMoisAnnee = jourMoisAnnee;
		this.mtPenaliteEcheance = mtPenaliteEcheance;
		this.droitSimpleMinimum = droitSimpleMinimum;
	}

	public List<TypeActe> getTypeActes() {
		return typeActes;
	}

	@JsonIgnore
	public void setTypeActes(List<TypeActe> typeActes) {
		this.typeActes = typeActes;
	}

}
