package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="typeproduit", schema = "winregist")
public class TypeProduit extends EntityBaseBean implements Serializable  {





	private static final long serialVersionUID = 1L;
	private String code;
	private String designation;

    @OneToMany(mappedBy = "typeProduit")
    private List<Quittance> quittancesTypeProduit = new ArrayList<>();


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TypeProduit that = (TypeProduit) o;
		return Objects.equals(code, that.code) &&
				Objects.equals(designation, that.designation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, designation);
	}

	@Override
	public String toString() {
		return "TypeProduit{" +
				"code='" + code + '\'' +
				", designation='" + designation + '\'' +
				'}';
	}
}
