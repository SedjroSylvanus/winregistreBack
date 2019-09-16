package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.Personne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "contribuableb")
//,

//		uniqueConstraints = {
//		@UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) ,
//		@UniqueConstraint(name = "matricule", columnNames = { "matricule" })

//		})
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class Contribuable  implements Serializable {     //extends Personne

//    private static final long serialVersionUID = 1L;
//
//
//
//    @NotBlank
//	@Column(unique = true)
//	@Size(max = 13, min = 13)
//	private String ifu;
//	private String raisonSocial;
//	private String telephone;
//	private String email;
//
////	@ManyToMany(fetch=FetchType.EAGER)
////	private Collection<AppRole> rolesCont = new ArrayList<>();
//
//
////	@OneToMany(mappedBy="contribuable")
////	private List<Acte> actes = new ArrayList<>();
//
//
//
//	public Contribuable() {
//		super();
//	}
//
//	public String getIfu() {
//		return ifu;
//	}
//
//	public void setIfu(String ifu) {
//		this.ifu = ifu;
//	}
//
//	public String getRaisonSocial() {
//		return raisonSocial;
//	}
//
//	public void setRaisonSocial(String raisonSocial) {
//		this.raisonSocial = raisonSocial;
//	}
//
//	public String getTelephone() {
//		return telephone;
//	}
//
//	public void setTelephone(String telephone) {
//		this.telephone = telephone;
//	}
//
//	@Override
//	public String getEmail() {
//		return email;
//	}
//
//	@Override
//	public void setEmail(String email) {
//		this.email = email;
//	}

//	public Collection<AppRole> getRoles() {
//		return rolesCont;
//	}
//
//	public void setRoles(Collection<AppRole> roles) {
//		this.rolesCont = roles;
//	}

//	public List<Acte> getActes() {
//		return actes;
//	}
//
//	@JsonIgnore
//	public void setActes(List<Acte> actes) {
//		this.actes = actes;
//	}




	private static final long serialVersionUID = 1L;



	@NotBlank
	@Column(unique = true)
	@Size(max = 13, min = 13)
	private String CONT_IMMATR;
	private String CONT_NOM;
	private String CONT_PREN;
	private String CONT_TEL;
	private String CONT_RAIS;
	private String CONT_MAIL;

	public Contribuable() {
	}

	public String getCONT_IMMATR() {
		return CONT_IMMATR;
	}

	public void setCONT_IMMATR(String CONT_IMMATR) {
		this.CONT_IMMATR = CONT_IMMATR;
	}

	public String getCONT_NOM() {
		return CONT_NOM;
	}

	public void setCONT_NOM(String CONT_NOM) {
		this.CONT_NOM = CONT_NOM;
	}

	public String getCONT_PREN() {
		return CONT_PREN;
	}

	public void setCONT_PREN(String CONT_PREN) {
		this.CONT_PREN = CONT_PREN;
	}

	public String getCONT_TEL() {
		return CONT_TEL;
	}

	public void setCONT_TEL(String CONT_TEL) {
		this.CONT_TEL = CONT_TEL;
	}

	public String getCONT_RAIS() {
		return CONT_RAIS;
	}

	public void setCONT_RAIS(String CONT_RAIS) {
		this.CONT_RAIS = CONT_RAIS;
	}

	public String getCONT_MAIL() {
		return CONT_MAIL;
	}

	public void setCONT_MAIL(String CONT_MAIL) {
		this.CONT_MAIL = CONT_MAIL;
	}
}
