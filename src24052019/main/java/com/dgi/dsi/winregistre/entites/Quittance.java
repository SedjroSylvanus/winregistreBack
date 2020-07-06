package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="quittance")
public class Quittance extends EntityBaseBean implements Serializable {

    private String numeroQuittance;
    private LocalDate dateQuittance;

    @ManyToOne
    private Acte acte;

    public Quittance() {
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public LocalDate getDateQuittance() {
        return dateQuittance;
    }

    public void setDateQuittance(LocalDate dateQuittance) {
        this.dateQuittance = dateQuittance;
    }

    public Acte getActe() {
        return acte;
    }

    public void setActe(Acte acte) {
        this.acte = acte;
    }
}
