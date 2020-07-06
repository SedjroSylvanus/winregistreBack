package com.dgi.dsi.winregistre.entites;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "contribuablebis")
//,

//		uniqueConstraints = {
//		@UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) ,
//		@UniqueConstraint(name = "matricule", columnNames = { "matricule" })

//		})
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class ContribuableBis implements Serializable {     //extends Personne


	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@NotBlank
	@Column(unique = true)
	@Size(max = 13, min = 13)
	private String cont_immatr;//CONT_IMMATR;
	private String cont_nom;//CONT_NOM;
	private String cont_pren;//CONT_PREN;
	private String cont_tel; //CONT_TEL;
	private String cont_rais; //CONT_RAIS;
	private String cont_mail; //CONT_MAIL;

	public ContribuableBis() {
	}

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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCont_immatr() {
		return cont_immatr;
	}

	public void setCont_immatr(String cont_immatr) {
		this.cont_immatr = cont_immatr;
	}

	public String getCont_nom() {
		return cont_nom;
	}

	public void setCont_nom(String cont_nom) {
		this.cont_nom = cont_nom;
	}

	public String getCont_pren() {
		return cont_pren;
	}

	public void setCont_pren(String cont_pren) {
		this.cont_pren = cont_pren;
	}

	public String getCont_tel() {
		return cont_tel;
	}

	public void setCont_tel(String cont_tel) {
		this.cont_tel = cont_tel;
	}

	public String getCont_rais() {
		return cont_rais;
	}

	public void setCont_rais(String cont_rais) {
		this.cont_rais = cont_rais;
	}

	public String getCont_mail() {
		return cont_mail;
	}

	public void setCont_mail(String cont_mail) {
		this.cont_mail = cont_mail;
	}
}
