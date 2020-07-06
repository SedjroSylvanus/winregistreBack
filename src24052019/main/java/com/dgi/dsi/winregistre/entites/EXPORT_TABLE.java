package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.Personne;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "export_table")
//,

//		uniqueConstraints = {
//		@UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) ,
//		@UniqueConstraint(name = "matricule", columnNames = { "matricule" })

//		})
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class EXPORT_TABLE extends Personne  implements Serializable {

    private static final long serialVersionUID = 1L;


//
//    @NotBlank
//	@Column(unique = true)
//	@Size(max = 13, min = 13)
//	private String CONT_IMMATR;
//	private String CONT_NOM;
//	private String CONT_PREN;
//	private String CONT_TEL;
//	private String CONT_RAIS;
//	private String CONT_MAIL;
//
//
//	public EXPORT_TABLE() {
//		super();
//	}
//
//	public String getCONT_IMMATR() {
//		return CONT_IMMATR;
//	}
//
//	public void setCONT_IMMATR(String CONT_IMMATR) {
//		this.CONT_IMMATR = CONT_IMMATR;
//	}
//
//	public String getCONT_NOM() {
//		return CONT_NOM;
//	}
//
//	public void setCONT_NOM(String CONT_NOM) {
//		this.CONT_NOM = CONT_NOM;
//	}
//
//	public String getCONT_PREN() {
//		return CONT_PREN;
//	}
//
//	public void setCONT_PREN(String CONT_PREN) {
//		this.CONT_PREN = CONT_PREN;
//	}
//
//	public String getCONT_TEL() {
//		return CONT_TEL;
//	}
//
//	public void setCONT_TEL(String CONT_TEL) {
//		this.CONT_TEL = CONT_TEL;
//	}
//
//	public String getCONT_RAIS() {
//		return CONT_RAIS;
//	}
//
//	public void setCONT_RAIS(String CONT_RAIS) {
//		this.CONT_RAIS = CONT_RAIS;
//	}
//
//	public String getCONT_MAIL() {
//		return CONT_MAIL;
//	}
//
//	public void setCONT_MAIL(String CONT_MAIL) {
//		this.CONT_MAIL = CONT_MAIL;
//	}
}
