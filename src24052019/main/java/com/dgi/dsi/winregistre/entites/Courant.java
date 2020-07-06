package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="courant")
public class Courant extends Compte implements Serializable  {





	private static final long serialVersionUID = 1L;
	private Double decouvert;


	public Double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(Double decouvert) {
		this.decouvert = decouvert;
	}

	public Courant() {
	}
}
