package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="natureacte", schema = "winregist")
//@Table(name="natureacte")

public class NatureActe extends EntityBaseBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;	
	private String code;
	private String designation;

	private String nouveauCode;
	private String nouveauDesignation;
	private Boolean nouveau = Boolean.TRUE;

	private Double tauxInflation;
	private Double tauxFormaliteNotaire;
	
	@ManyToOne
	private Tarif tarif;

	@OneToMany(mappedBy = "natureActe")
	@JsonIgnore
	private List<Acte> actes = new ArrayList<>();


	private Double taux;
	private Double mtFixe;
	private Double mtMinimum;
	private Double tauxRedevance;

	private Boolean gratis = Boolean.FALSE;
	private Double tauxFixePenalite;
	private Double mtFixePenalite;
	private Boolean ifuBeneficiaire = Boolean.FALSE;
	private Boolean ifuPrestataire = Boolean.FALSE;
	private Double minRedevance;
//	 `Ancien_PA` VARCHAR(10)  NOT NULL ,
	private String ancienPA;



	//    `CA_Code` VARCHAR(10)  NOT NULL ); nature acte 1
	@ManyToOne
	private CategorieActe categorieActe;
//    `CodePA` VARCHAR(10)  NOT NULL ,  code TypePenaliteAmende
	@ManyToOne
	private TypePenaliteAmende typePenaliteAmende;
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

	public Tarif getTarif() {
		return tarif;
	}

	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}

	public List<Acte> getActes() {
		return actes;
	}

	public void setActes(List<Acte> actes) {
		this.actes = actes;
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

	public Boolean getIfuBeneficiaire() {
		return ifuBeneficiaire;
	}

	public void setIfuBeneficiaire(Boolean ifuBeneficiaire) {
		this.ifuBeneficiaire = ifuBeneficiaire;
	}

	public Boolean getIfuPrestataire() {
		return ifuPrestataire;
	}

	public void setIfuPrestataire(Boolean ifuPrestataire) {
		this.ifuPrestataire = ifuPrestataire;
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

	public TypePenaliteAmende getTypePenaliteAmende() {
		return typePenaliteAmende;
	}

	public void setTypePenaliteAmende(TypePenaliteAmende typePenaliteAmende) {
		this.typePenaliteAmende = typePenaliteAmende;
	}

	public Integer getNbreTimbre() {
		return nbreTimbre;
	}
	public void setNbreTimbre(Integer nbreTimbre) {
		this.nbreTimbre = nbreTimbre;
	}
	@Override
	public String toString() {
		return "NatureActeDao [code=" + code + ", designation=" + designation + ", tarif=" + tarif + ", taux=" + taux
				+ ", mtFixe=" + mtFixe + ", mtMinimum=" + mtMinimum + ", tauxRedevance=" + tauxRedevance + ", gratis="
				+ gratis + ", tauxFixePenalite=" + tauxFixePenalite + ", mtFixePenalite=" + mtFixePenalite + ", ifuBénéficiaire="
				+ ifuBeneficiaire + ", minRedevance=" + minRedevance + ", ancienPA=" + ancienPA + ", categorieActe=" + categorieActe
				+ ", penaliteAmende=" + typePenaliteAmende + ", nbreTimbre=" + nbreTimbre + "]";
	}
	public NatureActe() {
		super();
	}
	public NatureActe(String code, String designation, String tarif, Double taux, Double mtFixe, Double mtMinimum,
					  Double tauxRedevance, Boolean gratis, Double tauxFixePenalite, Double mtFixePenalite, Boolean ifuBeneficiaire,  Boolean ifuPrestataire,
					  Double minRedevance, String ancienPA, CategorieActe categorieActe, TypePenaliteAmende penaliteAmende,
					  Integer nbreTimbre) {
		super();
		this.code = code;
		this.designation = designation;

		this.taux = taux;
		this.mtFixe = mtFixe;
		this.mtMinimum = mtMinimum;
		this.tauxRedevance = tauxRedevance;
		this.gratis = gratis;
		this.tauxFixePenalite = tauxFixePenalite;
		this.mtFixePenalite = mtFixePenalite;
		this.ifuBeneficiaire = ifuBeneficiaire;
		this.ifuPrestataire = ifuPrestataire;
		this.minRedevance = minRedevance;
		this.ancienPA = ancienPA;
		this.categorieActe = categorieActe;
		this.typePenaliteAmende = penaliteAmende;
		this.nbreTimbre = nbreTimbre;
	}
	public Boolean getNouveau() {
		return nouveau;
	}
	public void setNouveau(Boolean nouveau) {
		this.nouveau = nouveau;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNouveauCode() {
		return nouveauCode;
	}
	public void setNouveauCode(String nouveauCode) {
		this.nouveauCode = nouveauCode;
	}
	public String getNouveauDesignation() {
		return nouveauDesignation;
	}
	public void setNouveauDesignation(String nouveauDesignation) {
		this.nouveauDesignation = nouveauDesignation;
	}
	public Double getTauxInflation() {
		return tauxInflation;
	}
	public void setTauxInflation(Double tauxInflation) {
		this.tauxInflation = tauxInflation;
	}
	public Double getTauxFormaliteNotaire() {
		return tauxFormaliteNotaire;
	}
	public void setTauxFormaliteNotaire(Double tauxFormaliteNotaire) {
		this.tauxFormaliteNotaire = tauxFormaliteNotaire;
	}	
	
	



	
	

}
