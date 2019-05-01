package com.dgi.dsi.winregistre.entitiesIfri;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TProduit")
public class Produit {
    @Id
    private String code;
    private String designation;
    private Integer prix;

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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Produit(String code, String designation, Integer prix) {
        this.code = code;
        this.designation = designation;
        this.prix = prix;
    }

}
