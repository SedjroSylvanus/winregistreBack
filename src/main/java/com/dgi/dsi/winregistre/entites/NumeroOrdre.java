package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="numeroOrdre", schema = "winregist")
public class NumeroOrdre extends EntityBaseBean implements Serializable{


	private static final long serialVersionUID = 1L;

	private Integer numeroOrdre;
//	private LocalDate dateNumeroOrdre;

	@OneToOne
	private Service service;


    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public NumeroOrdre() {
    }

    public NumeroOrdre(Integer numeroOrdre, Service service) {
        this.numeroOrdre = numeroOrdre;
        this.service = service;
    }
}
