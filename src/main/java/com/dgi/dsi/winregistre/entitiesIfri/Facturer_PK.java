package com.dgi.dsi.winregistre.entitiesIfri;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Facturer_PK implements Serializable {
    private Integer numfact;
    private String code;

    @Override
    public String toString() {
        return "Facturer_PK{" +
                "numfact=" + numfact +
                ", code='" + code + '\'' +
                '}';
    }

    public Facturer_PK(Integer numfact, String code) {
        this.numfact = numfact;
        this.code = code;
    }

    public Facturer_PK() {
    }

    public Integer getNumfact() {
        return numfact;
    }

    public void setNumfact(Integer numfact) {
        this.numfact = numfact;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
