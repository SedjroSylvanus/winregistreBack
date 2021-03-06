package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name="bordereaucaisse")
public class BordereauCaisse extends EntityBaseBean implements Serializable  {
	

	


	private static final long serialVersionUID = 1L;	
	private String numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBordereauCaisse ;

	private Integer nbreQuittance;

	private Double mtBordereauCaisse;

	private Double totalPenalite;

	private Double totalDroitSimple;

	private Double TotalAmende;
	private Double TotalTimbre;
	private Double totalRedevance;
	private Boolean journeeCloture = Boolean.FALSE;
	private Double totalAutrePaiement;

	@ManyToOne
	private Agent agent;

	@Override
	public String toString() {
		return "BordereauCaisseDao [numero=" + numero + ", dateBordereauCaisse=" + dateBordereauCaisse + ", nbreQuittance="
				+ nbreQuittance + ", mtBordereauCaisse=" + mtBordereauCaisse + ", totalPenalite=" + totalPenalite
				+ ", totalDroitSimple=" + totalDroitSimple + ", TotalAmende=" + TotalAmende + ", TotalTimbre="
				+ TotalTimbre + ", totalRedevance=" + totalRedevance + ", journeeCloture=" + journeeCloture
				+ ", totalAutrePaiement=" + totalAutrePaiement + ", agent=" + agent + "]";
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateBordereauCaisse() {
		return dateBordereauCaisse;
	}

	public void setDateBordereauCaisse(Date dateBordereauCaisse) {
		this.dateBordereauCaisse = dateBordereauCaisse;
	}

	public Integer getNbreQuittance() {
		return nbreQuittance;
	}

	public void setNbreQuittance(Integer nbreQuittance) {
		this.nbreQuittance = nbreQuittance;
	}

	public Double getMtBordereauCaisse() {
		return mtBordereauCaisse;
	}

	public void setMtBordereauCaisse(Double mtBordereauCaisse) {
		this.mtBordereauCaisse = mtBordereauCaisse;
	}

	public Double getTotalPenalite() {
		return totalPenalite;
	}

	public void setTotalPenalite(Double totalPenalite) {
		this.totalPenalite = totalPenalite;
	}

	public Double getTotalDroitSimple() {
		return totalDroitSimple;
	}

	public void setTotalDroitSimple(Double totalDroitSimple) {
		this.totalDroitSimple = totalDroitSimple;
	}

	public Double getTotalAmende() {
		return TotalAmende;
	}

	public void setTotalAmende(Double totalAmende) {
		TotalAmende = totalAmende;
	}

	public Double getTotalTimbre() {
		return TotalTimbre;
	}

	public void setTotalTimbre(Double totalTimbre) {
		TotalTimbre = totalTimbre;
	}

	public Double getTotalRedevance() {
		return totalRedevance;
	}

	public void setTotalRedevance(Double totalRedevance) {
		this.totalRedevance = totalRedevance;
	}

	public Boolean getJourneeCloture() {
		return journeeCloture;
	}

	public void setJourneeCloture(Boolean journeeCloture) {
		this.journeeCloture = journeeCloture;
	}

	public Double getTotalAutrePaiement() {
		return totalAutrePaiement;
	}

	public void setTotalAutrePaiement(Double totalAutrePaiement) {
		this.totalAutrePaiement = totalAutrePaiement;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public BordereauCaisse() {
		super();
	}

	public BordereauCaisse(String numero, Date dateBordereauCaisse, Integer nbreQuittance, Double mtBordereauCaisse,
			Double totalPenalite, Double totalDroitSimple, Double totalAmende, Double totalTimbre,
			Double totalRedevance, Boolean journeeCloture, Double totalAutrePaiement, Agent agent) {
		super();
		this.numero = numero;
		this.dateBordereauCaisse = dateBordereauCaisse;
		this.nbreQuittance = nbreQuittance;
		this.mtBordereauCaisse = mtBordereauCaisse;
		this.totalPenalite = totalPenalite;
		this.totalDroitSimple = totalDroitSimple;
		TotalAmende = totalAmende;
		TotalTimbre = totalTimbre;
		this.totalRedevance = totalRedevance;
		this.journeeCloture = journeeCloture;
		this.totalAutrePaiement = totalAutrePaiement;
		this.agent = agent;
	}





	

	
	

}
