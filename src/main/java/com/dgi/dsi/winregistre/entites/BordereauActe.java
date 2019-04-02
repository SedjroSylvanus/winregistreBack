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
@Table(name="bordereauacte")
public class BordereauActe extends EntityBaseBean implements Serializable  {
	

	


	private static final long serialVersionUID = 1L;	
	private String numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBordereau ;
	
	
	private Integer nbreActe;
	private Double totalDS;
	private Double totalPenalite;
	private Double totalAmende ;
	
	@ManyToOne
	private CategorieActe categorieActe;


	@ManyToOne
	private Agent agent;

	@ManyToOne
	private Institution institution;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateBordereau() {
		return dateBordereau;
	}

	public void setDateBordereau(Date dateBordereau) {
		this.dateBordereau = dateBordereau;
	}

	public Integer getNbreActe() {
		return nbreActe;
	}

	public void setNbreActe(Integer nbreActe) {
		this.nbreActe = nbreActe;
	}

	public Double getTotalDS() {
		return totalDS;
	}

	public void setTotalDS(Double totalDS) {
		this.totalDS = totalDS;
	}

	public Double getTotalPenalite() {
		return totalPenalite;
	}

	public void setTotalPenalite(Double totalPenalite) {
		this.totalPenalite = totalPenalite;
	}

	public Double getTotalAmende() {
		return totalAmende;
	}

	public void setTotalAmende(Double totalAmende) {
		this.totalAmende = totalAmende;
	}

	public CategorieActe getCategorieActe() {
		return categorieActe;
	}

	public void setCategorieActe(CategorieActe categorieActe) {
		this.categorieActe = categorieActe;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	@Override
	public String toString() {
		return "BordereauActeDao [numero=" + numero + ", dateBordereau=" + dateBordereau + ", nbreActe=" + nbreActe
				+ ", totalDS=" + totalDS + ", totalPenalite=" + totalPenalite + ", totalAmende=" + totalAmende
				+ ", categorieActe=" + categorieActe + ", agent=" + agent + ", institution=" + institution + "]";
	}

	public BordereauActe() {
		super();
	}

	public BordereauActe(String numero, Date dateBordereau, Integer nbreActe, Double totalDS, Double totalPenalite,
			Double totalAmende, CategorieActe categorieActe, Agent agent, Institution institution) {
		super();
		this.numero = numero;
		this.dateBordereau = dateBordereau;
		this.nbreActe = nbreActe;
		this.totalDS = totalDS;
		this.totalPenalite = totalPenalite;
		this.totalAmende = totalAmende;
		this.categorieActe = categorieActe;
		this.agent = agent;
		this.institution = institution;
	}




//	  `CONT_NUM` varchar(13) NOT NULL,
	



	

	
	

}
