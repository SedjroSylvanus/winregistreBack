package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="bordereauacte", schema = "winregist")
//@Table(name="bordereauacte")

public class BordereauActe extends EntityBaseBean implements Serializable  {
	

	


	private static final long serialVersionUID = 1L;


//	@Column(unique = true)
	private String numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateBordereau ;
	
	
	private Integer nbreActe ;
	private Double totalDS = 0.;
	private Double totalPenalite = 0.;
	private Double totalAmende = 0.;
	private Double totalRedevance = 0.;
//	private Double droitSimple = 0.;
	private Double totalTimbre = 0.;
	private Double totalPlusValueImobiliere = 0.;

	
	private Boolean validated ;
	private Boolean sourceInterne ;
	private Boolean transferred = Boolean.FALSE ;
	private String numeroBordereauValidateur ;

//	@ManyToOne
//	private CategorieActe categorieActe;




	@JsonIgnore
	@OneToMany(mappedBy="bordereauActe")
	List<Acte> actesBordereau = new ArrayList<>();


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

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

    public Boolean getTransferred() {
        return transferred;
    }

    public void setTransferred(Boolean transferred) {
        this.transferred = transferred;
    }

    public Boolean getSourceInterne() {
		return sourceInterne;
	}

	public void setSourceInterne(Boolean sourceInterne) {
		this.sourceInterne = sourceInterne;
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
				+ ", agent=" + agent + "]"+ super.toString();
	}

	public BordereauActe() {
		super();
			this.totalDS = 0.;
		this.totalPenalite = 0.;
		this.totalAmende = 0.;
		this.totalRedevance = 0.;
		this.totalTimbre = 0.;
	}


    public BordereauActe(String numero, Date dateBordereau, Integer nbreActe, Double totalDS, Double totalPenalite, Double totalAmende, Double totalRedevance, Double totalTimbre, List<Acte> actes, Agent agent) {
        this.numero = numero;
        this.dateBordereau = dateBordereau;
        this.nbreActe = nbreActe;
        this.totalDS = totalDS;
        this.totalPenalite = totalPenalite;
        this.totalAmende = totalAmende;
        this.totalRedevance = totalRedevance;
        this.totalTimbre = totalTimbre;
        this.actesBordereau = actes;
        this.agent = agent;
    }

	public List<Acte> getActesBordereau() {
		return actesBordereau;
	}

	public void setActesBordereau(List<Acte> actesBordereau) {
		this.actesBordereau = actesBordereau;
	}

	//	  `CONT_NUM` varchar(13) NOT NULL,


	public Double getTotalRedevance() {
		return totalRedevance;
	}

	public void setTotalRedevance(Double totalRedevance) {
		this.totalRedevance = totalRedevance;
	}

    public Double getTotalTimbre() {
        return totalTimbre;
    }

    public void setTotalTimbre(Double totalTimbre) {
        this.totalTimbre = totalTimbre;
    }

	public String getNumeroBordereauValidateur() {
		return numeroBordereauValidateur;
	}

	public void setNumeroBordereauValidateur(String numeroBordereauValidateur) {
		this.numeroBordereauValidateur = numeroBordereauValidateur;
	}

	public Double getTotalPlusValueImobiliere() {
		return totalPlusValueImobiliere;
	}

	public void setTotalPlusValueImobiliere(Double totalPlusValueImobiliere) {
		this.totalPlusValueImobiliere = totalPlusValueImobiliere;
	}
	
	
}
