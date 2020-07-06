package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.dgi.dsi.winregistre.parent.entites.Personne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contribuablebis", schema = "winregist")
//@Table(name = "contribuablebis")

//@Table(name= "contribuable_base")
//,

//		uniqueConstraints = {
//		@UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) ,
//		@UniqueConstraint(name = "matricule", columnNames = { "matricule" })

//		})
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class ContribuableBis extends EntityBaseBean implements Serializable {     //extends Personne


	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


//	@NotBlank
//	@Column(unique = true)
	@Size(max = 13, min = 13)
	@Column( name = "cont_immatr")
	private String contImmatr;
	@Column( name = "cont_nom")
	private String contNom;
	@Column( name = "cont_pren")
	private String contPren;
	@Column( name = "cont_tel")
	private String contTel;
	@Column( name = "cont_rais")
	private String contRais;
	@Column( name = "cont_mail")
	private String contMail;


	 @OneToMany(mappedBy = "contribuableBeneficiaire")
	 @JsonIgnore
    private List<Acte> acteBeneficiaire = new ArrayList<>();


	  @OneToMany(mappedBy = "contribuablePrestataire")
	  @JsonIgnore
    private List<Acte> actePrestataire = new  ArrayList<>();




	public ContribuableBis() {
	}


	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getContImmatr() {
		return contImmatr;
	}

	public void setContImmatr(String contImmatr) {
		this.contImmatr = contImmatr;
	}

	public String getContNom() {
		return contNom;
	}

	public void setContNom(String contNom) {
		this.contNom = contNom;
	}

	public String getContPren() {
		return contPren;
	}

	public void setContPren(String contPren) {
		this.contPren = contPren;
	}

	public String getContTel() {
		return contTel;
	}

	public void setContTel(String contTel) {
		this.contTel = contTel;
	}

	public String getContRais() {
		return contRais;
	}

	public void setContRais(String contRais) {
		this.contRais = contRais;
	}

	public String getContMail() {
		return contMail;
	}

	public void setContMail(String contMail) {
		this.contMail = contMail;
	}

	public List<Acte> getActeBeneficiaire() {
        return acteBeneficiaire;
    }

//    @JsonIgnore
    public void setActeBeneficiaire(List<Acte> acteBeneficiaire) {
        this.acteBeneficiaire = acteBeneficiaire;
    }

    public List<Acte> getActePrestataire() {
        return actePrestataire;
    }

//    @JsonIgnore
    public void setActePrestataire(List<Acte> actePrestataire) {
        this.actePrestataire = actePrestataire;
    }

//	public List<Acte> getActeBeneficiaire() {
//		return acteBeneficiaire;
//	}
//
//	@JsonIgnore
//	public void setActeBeneficiaire(List<Acte> acteBeneficiaire) {
//		this.acteBeneficiaire = acteBeneficiaire;
//	}
//
//	public List<Acte> getActePrestataire() {
//		return actePrestataire;
//	}
//
//	@JsonIgnore
//	public void setActePrestataire(List<Acte> actePrestataire) {
//		this.actePrestataire = actePrestataire;
//	}
}
