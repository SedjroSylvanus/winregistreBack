package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name="exercice")
public class Exercice extends EntityBaseBean implements Serializable{
	

	private static final long serialVersionUID = 1L;	
	private String annee;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;
	
	private Boolean cloturer;
	





	public Exercice() {
		super();
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
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

	public Boolean getCloturer() {
		return cloturer;
	}

	public void setCloturer(Boolean cloturer) {
		this.cloturer = cloturer;
	}

	public Exercice(String annee, Date dateDebut, Date dateFin, Boolean cloturer) {
		super();
		this.annee = annee;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.cloturer = cloturer;
	}

	@Override
	public String toString() {
		return "Exercice [annee=" + annee + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", cloturer="
				+ cloturer + "]";
	}

	

	



	

}
