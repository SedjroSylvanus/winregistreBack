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
}
