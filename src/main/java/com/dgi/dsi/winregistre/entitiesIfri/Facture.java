package com.dgi.dsi.winregistre.entitiesIfri;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="TFacture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numfact;
    private Date datefact;
    @ManyToOne
    private Client client;


    public String toString(){
        return "La facture N°" + numfact + " du client N°" + client.getNumcli() + " est établie le " + datefact;
    }
    public Integer getNumfact() {
        return numfact;
    }

    public void setNumfact(Integer numfact) {
        this.numfact = numfact;
    }

    public Date getDatefact() {
        return datefact;
    }

    public void setDatefact(Date datefact) {
        this.datefact = datefact;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Facture(Integer numfact, Date datefact, Client client) {
        this.numfact = numfact;
        this.datefact = datefact;
        this.client = client;
    }


}
