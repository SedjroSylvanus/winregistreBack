package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="detailquittance")
//        ,  uniqueConstraints = { @UniqueConstraint(name = "numquittancer", columnNames = { "numeroQuittance", "codeProduit" }) })
public class DetailQuittance extends EntityBaseBean implements Serializable {


    @ManyToOne
    private Quittance quittance;


    private Double montantDQ;
    private String natureImpot;


    public DetailQuittance() {
    }

    public Quittance getQuittance() {
        return quittance;
    }

    public void setQuittance(Quittance quittance) {
        this.quittance = quittance;
    }

    public Double getMontantDQ() {
        return montantDQ;
    }

    public void setMontantDQ(Double montantDQ) {
        this.montantDQ = montantDQ;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }


    public String getNatureImpot() {
        return natureImpot;
    }

    public void setNatureImpot(String natureImpot) {
        this.natureImpot = natureImpot;
    }
}
