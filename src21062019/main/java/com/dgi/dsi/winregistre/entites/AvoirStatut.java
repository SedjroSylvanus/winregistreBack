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
@Table(name = "avoirstatut")
public class AvoirStatut extends EntityBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numero;

	private String designation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;

	private String commentaire;
	
	@ManyToOne
	private Statut statut;

	@ManyToOne
	private Agent agent;

	@ManyToOne
	private MotifRejet motifrejet;
	
//	Act_Num` varchar(25) DEFAULT NULL, pour le num d'acte

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public MotifRejet getMotifrejet() {
		return motifrejet;
	}

	public void setMotifrejet(MotifRejet motifrejet) {
		this.motifrejet = motifrejet;
	}

	public String getDesignation() {
		return designation;
	}

	public AvoirStatut(String designation) {
		super();
		this.designation = designation;
	}

	public AvoirStatut() {
		super();
	}

	@Override
	public String toString() {
		return "Banque [ designation=" + designation + "]";
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

}
