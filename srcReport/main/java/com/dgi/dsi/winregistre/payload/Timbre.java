package com.dgi.dsi.winregistre.payload;

import com.dgi.dsi.winregistre.entites.Commune;
import com.dgi.dsi.winregistre.entites.NatureActe;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

public class Timbre {


    private Double valeurTimbre = 1.;//A créer dans acte
    private Double nombreTimbre = 1.;//A créer dans acte

    public Timbre() {
    }

    public Timbre(Double valeurTimbre, Double nombreTimbre) {
        this.valeurTimbre = valeurTimbre;
        this.nombreTimbre = nombreTimbre;
    }

    public Double getValeurTimbre() {
        return valeurTimbre;
    }

    public void setValeurTimbre(Double valeurTimbre) {
        this.valeurTimbre = valeurTimbre;
    }

    public Double getNombreTimbre() {
        return nombreTimbre;
    }

    public void setNombreTimbre(Double nombreTimbre) {
        this.nombreTimbre = nombreTimbre;
    }
}

