package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name="typeacte")
public class TypeActe extends EntityBaseBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;	
	private String code;
	
	private String designation;
	private String tarif;
	private Double taux;
	private Double mtFixe;
	private Double mtMinimum;
	private Double tauxRedevance;

	private Boolean gratis = Boolean.FALSE;
	private Double tauxFixePenalite;
	private Double mtFixePenalite;
	private Boolean ifu = Boolean.FALSE;
	private Double minRedevance;
//	 `Ancien_PA` VARCHAR(10)  NOT NULL ,
	private String ancienPA;

//    `CA_Code` VARCHAR(10)  NOT NULL ); nature acte 1
	@ManyToOne
	private CategorieActe categorieActe;
//    `CodePA` VARCHAR(10)  NOT NULL ,  code PenaliteAmende
	@ManyToOne
	private PenaliteAmende penaliteAmende;	
//	 `NA_Code` VARCHAR(20)  NOT NULL , nature Acte Est ce que c'est tjr obligatoire
//	    `Trimbre` VARCHAR(20)  NOT NULL , vers timbre
	private Integer nbreTimbre;
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
	public String getTarif() {
		return tarif;
	}
	public void setTarif(String tarif) {
		this.tarif = tarif;
	}
	public Double getTaux() {
		return taux;
	}
	public void setTaux(Double taux) {
		this.taux = taux;
	}
	public Double getMtFixe() {
		return mtFixe;
	}
	public void setMtFixe(Double mtFixe) {
		this.mtFixe = mtFixe;
	}
	public Double getMtMinimum() {
		return mtMinimum;
	}
	public void setMtMinimum(Double mtMinimum) {
		this.mtMinimum = mtMinimum;
	}
	public Double getTauxRedevance() {
		return tauxRedevance;
	}
	public void setTauxRedevance(Double tauxRedevance) {
		this.tauxRedevance = tauxRedevance;
	}
	public Boolean getGratis() {
		return gratis;
	}
	public void setGratis(Boolean gratis) {
		this.gratis = gratis;
	}
	public Double getTauxFixePenalite() {
		return tauxFixePenalite;
	}
	public void setTauxFixePenalite(Double tauxFixePenalite) {
		this.tauxFixePenalite = tauxFixePenalite;
	}
	public Double getMtFixePenalite() {
		return mtFixePenalite;
	}
	public void setMtFixePenalite(Double mtFixePenalite) {
		this.mtFixePenalite = mtFixePenalite;
	}
	public Boolean getIfu() {
		return ifu;
	}
	public void setIfu(Boolean ifu) {
		this.ifu = ifu;
	}
	public Double getMinRedevance() {
		return minRedevance;
	}
	public void setMinRedevance(Double minRedevance) {
		this.minRedevance = minRedevance;
	}
	public String getAncienPA() {
		return ancienPA;
	}
	public void setAncienPA(String ancienPA) {
		this.ancienPA = ancienPA;
	}
	public CategorieActe getCategorieActe() {
		return categorieActe;
	}
	public void setCategorieActe(CategorieActe categorieActe) {
		this.categorieActe = categorieActe;
	}
	public PenaliteAmende getPenaliteAmende() {
		return penaliteAmende;
	}
	public void setPenaliteAmende(PenaliteAmende penaliteAmende) {
		this.penaliteAmende = penaliteAmende;
	}
	public Integer getNbreTimbre() {
		return nbreTimbre;
	}
	public void setNbreTimbre(Integer nbreTimbre) {
		this.nbreTimbre = nbreTimbre;
	}
	@Override
	public String toString() {
		return "TypeActeDao [code=" + code + ", designation=" + designation + ", tarif=" + tarif + ", taux=" + taux
				+ ", mtFixe=" + mtFixe + ", mtMinimum=" + mtMinimum + ", tauxRedevance=" + tauxRedevance + ", gratis="
				+ gratis + ", tauxFixePenalite=" + tauxFixePenalite + ", mtFixePenalite=" + mtFixePenalite + ", ifu="
				+ ifu + ", minRedevance=" + minRedevance + ", ancienPA=" + ancienPA + ", categorieActe=" + categorieActe
				+ ", penaliteAmende=" + penaliteAmende + ", nbreTimbre=" + nbreTimbre + "]";
	}
	public TypeActe() {
		super();
	}
	public TypeActe(String code, String designation, String tarif, Double taux, Double mtFixe, Double mtMinimum,
			Double tauxRedevance, Boolean gratis, Double tauxFixePenalite, Double mtFixePenalite, Boolean ifu,
			Double minRedevance, String ancienPA, CategorieActe categorieActe, PenaliteAmende penaliteAmende,
			Integer nbreTimbre) {
		super();
		this.code = code;
		this.designation = designation;
		this.tarif = tarif;
		this.taux = taux;
		this.mtFixe = mtFixe;
		this.mtMinimum = mtMinimum;
		this.tauxRedevance = tauxRedevance;
		this.gratis = gratis;
		this.tauxFixePenalite = tauxFixePenalite;
		this.mtFixePenalite = mtFixePenalite;
		this.ifu = ifu;
		this.minRedevance = minRedevance;
		this.ancienPA = ancienPA;
		this.categorieActe = categorieActe;
		this.penaliteAmende = penaliteAmende;
		this.nbreTimbre = nbreTimbre;
	}	
	
	



	
	

}
