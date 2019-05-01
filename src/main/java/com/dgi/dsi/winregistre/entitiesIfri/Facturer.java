package com.dgi.dsi.winregistre.entitiesIfri;

import javax.persistence.*;

@Entity
@Table(name="TFacturer")
public class Facturer {

    @EmbeddedId
    private Facturer_PK pk;
    private Integer qte;

    @Override
    public String toString() {
        return "Facturer{" +
                "pk=" + pk +
                ", qte=" + qte +
                '}';
    }

    public Facturer_PK getPk() {
        return pk;
    }

    public void setPk(Facturer_PK pk) {
        this.pk = pk;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Facturer(Facturer_PK pk, Integer qte) {
        this.pk = pk;
        this.qte = qte;
    }

}
