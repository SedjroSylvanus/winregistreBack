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
@Table(name = "typePenaliteAmende")
public class TypePenaliteAmende extends EntityBaseBean implements Serializable {


	private static final long serialVersionUID = 1L;	
	private String code;
	private String designation;

	private String periodicite;
	private Double mtPenaliteAmende;
	private Integer delaiEcheance;
	private String grille;
	private Integer echeance;

	private Double mtMinimum;
	private Double mtMaximum;
	private String jourMoisAnnee;
	private Double mtPenaliteEcheance;
	private Double droitSimpleMinimum;

	@OneToMany(mappedBy = "typePenaliteAmende")
	private List<NatureActe> natureActes = new ArrayList<>();

	public TypePenaliteAmende() {
		super();
	}


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getDelaiEcheance() {
        return delaiEcheance;
    }

    public void setDelaiEcheance(Integer delaiEcheance) {
        this.delaiEcheance = delaiEcheance;
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

	public Integer getEcheance() {
		return echeance;
	}

	public void setEcheance(Integer echeance) {
		this.echeance = echeance;
	}

	public List<NatureActe> getNatureActes() {
		return natureActes;
	}

@JsonIgnore
	public void setNatureActes(List<NatureActe> natureActes) {
		this.natureActes = natureActes;
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
		return "TypePenaliteAmende [codePenaliteAmende=" + code + ", libellePenaliteAmende="
				+ designation + ", periodicite=" + periodicite + ", mtPenaliteAmende=" + mtPenaliteAmende
				+ ", dalaiEcheance=" + delaiEcheance + ", grille=" + grille + ", mtMinimum=" + mtMinimum
				+ ", mtMaximum=" + mtMaximum + ", jourMoisAnnee=" + jourMoisAnnee + ", mtPenaliteEcheance="
				+ mtPenaliteEcheance + ", droitSimpleMinimum=" + droitSimpleMinimum + "]";
	}

	public TypePenaliteAmende(String code, String designation, String periodicite,
                              Double mtPenaliteAmende, Integer dalaiEcheance, String grille, Double mtMinimum, Double mtMaximum,
                              String jourMoisAnnee, Double mtPenaliteEcheance, Double droitSimpleMinimum) {
		super();
		this.code = code;
		this.designation = designation;
		this.periodicite = periodicite;
		this.mtPenaliteAmende = mtPenaliteAmende;
		this.delaiEcheance = dalaiEcheance;
		this.grille = grille;
		this.mtMinimum = mtMinimum;
		this.mtMaximum = mtMaximum;
		this.jourMoisAnnee = jourMoisAnnee;
		this.mtPenaliteEcheance = mtPenaliteEcheance;
		this.droitSimpleMinimum = droitSimpleMinimum;
	}

	public List<NatureActe> getTypeActes() {
		return natureActes;
	}

	@JsonIgnore
	public void setTypeActes(List<NatureActe> natureActes) {
		this.natureActes = natureActes;
	}

}
