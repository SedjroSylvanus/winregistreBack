package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="grilleActeSousSeingPrive", schema = "winregist")
public class GrilleActeSousSeingPrive extends EntityBaseBean implements Serializable  {





	private static final long serialVersionUID = 1L;
	private Integer delaiMin;
	private Integer delaiMax;
	private Double taux;
	private String designation;





//	@OneToMany(mappedBy = "grilleActeSousSeingPrive")
//    private List<TypePenaliteAmende> typePenaliteAmendes = new ArrayList<>();

	public Integer getDelaiMin() {
		return delaiMin;
	}

	public void setDelaiMin(Integer delaiMin) {
		this.delaiMin = delaiMin;
	}

	public Integer getDelaiMax() {
		return delaiMax;
	}

	public void setDelaiMax(Integer delaiMax) {
		this.delaiMax = delaiMax;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

//	@JsonIgnore
//	public List<TypePenaliteAmende> getTypePenaliteAmendes() {
//		return typePenaliteAmendes;
//	}
//
//	public void setTypePenaliteAmendes(List<TypePenaliteAmende> typePenaliteAmendes) {
//		this.typePenaliteAmendes = typePenaliteAmendes;
//	}

	public GrilleActeSousSeingPrive() {
	}

	public GrilleActeSousSeingPrive(Integer delaiMin, Integer delaiMax, Double taux) {
		this.delaiMin = delaiMin;
		this.delaiMax = delaiMax;
		this.taux = taux;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GrilleActeSousSeingPrive that = (GrilleActeSousSeingPrive) o;
		return Objects.equals(delaiMin, that.delaiMin) &&
				Objects.equals(delaiMax, that.delaiMax) &&
				Objects.equals(taux, that.taux);
	}

    @Override
    public String toString() {
        return "GrilleActeSousSeingPrive{" +
                "delaiMin=" + delaiMin +
                ", delaiMax=" + delaiMax +
                ", taux=" + taux +
                ", designation='" + designation + '\'' +
                '}';
    }

    @Override
	public int hashCode() {
		return Objects.hash(delaiMin, delaiMax, taux);
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
}
