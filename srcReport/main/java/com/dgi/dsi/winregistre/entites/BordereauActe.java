package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="bordereauacte")
public class BordereauActe extends EntityBaseBean implements Serializable  {
	

	


	private static final long serialVersionUID = 1L;	
	private String numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBordereau ;
	
	
	private Integer nbreActe ;
	private Double totalDS = 0.;
	private Double totalPenalite = 0.;
	private Double totalAmende = 0.;
	private Double totalRedevance = 0.;

//	@ManyToOne
//	private CategorieActe categorieActe;




	@OneToMany(mappedBy="bordereauActe")
	List<Acte> actes = new ArrayList<>();


	@ManyToOne
	private Agent agent;

//	@ManyToOne
//	private Institution institution;

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

//	public CategorieActe getCategorieActe() {
//		return categorieActe;
//	}
//
//	public void setCategorieActe(CategorieActe categorieActe) {
//		this.categorieActe = categorieActe;
//	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

//	public Institution getInstitution() {
//		return institution;
//	}
//
//	public void setInstitution(Institution institution) {
//		this.institution = institution;
//	}

	@Override
	public String toString() {
		return "BordereauActeDao [numero=" + numero + ", dateBordereau=" + dateBordereau + ", nbreActe=" + nbreActe
				+ ", totalDS=" + totalDS + ", totalPenalite=" + totalPenalite + ", totalAmende=" + totalAmende
				+ ", agent=" + agent + "]";
	}

	public BordereauActe() {
		super();
			this.totalDS = 0.;
		this.totalPenalite = 0.;
		this.totalAmende = 0.;
		this.totalRedevance = 0.;
	}

	public BordereauActe(String numero, Date dateBordereau, Integer nbreActe, Double totalDS, Double totalPenalite, Double totalAmende, Double totalRedevance, List<Acte> actes, Agent agent) {
		this.numero = numero;
		this.dateBordereau = dateBordereau;
		this.nbreActe = nbreActe;
		this.totalDS = totalDS;
		this.totalPenalite = totalPenalite;
		this.totalAmende = totalAmende;
		this.totalRedevance = totalRedevance;
		this.actes = actes;
		this.agent = agent;
	}

	public List<Acte> getActes() {
		return actes;
	}

	@JsonIgnore
	public void setActes(List<Acte> actes) {
		this.actes = actes;
	}

//	  `CONT_NUM` varchar(13) NOT NULL,


	public Double getTotalRedevance() {
		return totalRedevance;
	}

	public void setTotalRedevance(Double totalRedevance) {
		this.totalRedevance = totalRedevance;
	}
}
