package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name = "avoirpourcentage", schema = "winregist")
public class AvoirPourcentage extends EntityBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double pourcentage;

	@ManyToOne
	private Tranche tranche;
	
	@ManyToOne
	private DegreSuccession degreSuccession;

	
	public Double getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}

	public Tranche getTranche() {
		return tranche;
	}

	public void setTranche(Tranche tranche) {
		this.tranche = tranche;
	}

	public DegreSuccession getDegreSuccession() {
		return degreSuccession;
	}

	public void setDegreSuccession(DegreSuccession degreSuccession) {
		this.degreSuccession = degreSuccession;
	}

	@Override
	public String toString() {
		return "AvoirPourcentage [pourcentage=" + pourcentage + ", tranche=" + tranche + ", degreSuccession="
				+ degreSuccession + "]";
	}

	public AvoirPourcentage() {
		super();
	}

	public AvoirPourcentage(Double pourcentage, Tranche tranche, DegreSuccession degreSuccession) {
		super();
		this.pourcentage = pourcentage;
		this.tranche = tranche;
		this.degreSuccession = degreSuccession;
	}


}
