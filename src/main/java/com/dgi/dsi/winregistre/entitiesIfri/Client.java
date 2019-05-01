
package com.dgi.dsi.winregistre.entitiesIfri;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="TClient")

public class Client implements Serializable {
    public Client() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer numcli;
    private  String nomcli;
    private  Integer telcli;
    @OneToMany(mappedBy = "client")
    private List<Facture> factures = new ArrayList<>();

    @Override
    public String toString() {
        return "Client{" +
                "numcli=" + numcli +
                ", nomcli='" + nomcli + '\'' +
                ", telcli=" + telcli +
                '}';
    }

    public Client(Integer numcli, String nomcli, Integer telcli) {
        this.numcli = numcli;
        this.nomcli = nomcli;
        this.telcli = telcli;
    }


    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public Integer getNumcli() {
        return numcli;
    }

    public void setNumcli(Integer numcli) {
        this.numcli = numcli;
    }

    public String getNomcli() {
        return nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public Integer getTelcli() {
        return telcli;
    }

    public void setTelcli(Integer telcli) {
        this.telcli = telcli;
    }



}
