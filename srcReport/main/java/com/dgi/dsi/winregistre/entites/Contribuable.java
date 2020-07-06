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
@Table(name = "contribuable")
//,

//		uniqueConstraints = {
//		@UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) ,
//		@UniqueConstraint(name = "matricule", columnNames = { "matricule" })

//		})
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class Contribuable extends Personne  implements Serializable {

    private static final long serialVersionUID = 1L;



//    @NotBlank
//	@Column(unique = true)
//	@Size(max = 13, min = 13)
//	private String ifu;
//	private String raisonSocial;
//	private String telephone;
//	private String email;


		@NotBlank
	@Column(unique = true)
	@Size(max = 13, min = 13)
	private String CONT_IMMATR;
	private String CONT_NOM;
	private String CONT_PREN;
	private String CONT_TEL;
	private String CONT_RAIS;
	private String CONT_MAIL;


	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<AppRole> rolesCont = new ArrayList<>();


//	@OneToMany(mappedBy="contribuableB")
//	private List<Acte> actesB = new ArrayList<>();
//
//	@OneToMany(mappedBy="contribuableP")
//	private List<Acte> actesP = new ArrayList<>();

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

	public Collection<AppRole> getRolesCont() {
		return rolesCont;
	}

	public void setRolesCont(Collection<AppRole> rolesCont) {
		this.rolesCont = rolesCont;
	}

//	public List<Acte> getActesB() {
//		return actesB;
//	}
//
//	public void setActesB(List<Acte> actesB) {
//		this.actesB = actesB;
//	}
//
//	public List<Acte> getActesP() {
//		return actesP;
//	}
//
//	public void setActesP(List<Acte> actesP) {
//		this.actesP = actesP;
//	}
}
