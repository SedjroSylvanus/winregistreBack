package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Epargne")
public class Epargne extends Compte implements Serializable  {





	private static final long serialVersionUID = 1L;
	private Double taux;

	public Epargne() {
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}
}
