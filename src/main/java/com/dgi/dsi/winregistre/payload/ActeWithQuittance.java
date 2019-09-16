package com.dgi.dsi.winregistre.payload;

import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.Quittance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
public class ObjetActeQuittance {
    private Acte acte;
    private List<Quittance> quittances = new ArrayList<>();

    public ObjetActeQuittance(Acte acte, List<Quittance> quittances) {
        this.acte = acte;
        this.quittances = quittances;
    }

    public Acte getActe() {
        return acte;
    }

    public void setActe(Acte acte) {
        this.acte = acte;
    }

    public List<Quittance> getQuittances() {
        return quittances;
    }

    public void setQuittances(List<Quittance> quittances) {
        this.quittances = quittances;
    }
}
