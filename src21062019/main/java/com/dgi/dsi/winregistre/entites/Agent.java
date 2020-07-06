package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.dgi.dsi.winregistre.parent.entites.Personne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "agent")
//,

//		uniqueConstraints = {
//		@UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) ,
//		@UniqueConstraint(name = "matricule", columnNames = { "matricule" })

//		})
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class Agent extends Personne  implements Serializable {

    private static final long serialVersionUID = 1L;



    @NotBlank
	@Column(unique = true)
	private String matricule;

//	private Boolean caissier;
	private String indiceAgent;




	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();

	@ManyToOne
	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}



	public Agent() {
		super();
	}









	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}



	public String getIndiceAgent() {
		return indiceAgent;
	}

	public void setIndiceAgent(String indiceAgent) {
		this.indiceAgent = indiceAgent;
	}



	@OneToMany(mappedBy="agent")
	List<BordereauActe> bordereauActes = new ArrayList<>();




	@OneToMany(mappedBy="agentLiquidateur")
	List<Acte> actesLiquidateur = new ArrayList<>();

	@OneToMany(mappedBy="caissier")
	List<Quittance> quittanceCaissier = new ArrayList<>();
//
//	@OneToMany(mappedBy="agentLiquidateur")
//	List<Acte> actesLiquidateur = new ArrayList<>();


	public List<BordereauActe> getBordereauActes() {
		return bordereauActes;
	}

	@JsonIgnore
	public void setBordereauActes(List<BordereauActe> bordereauActes) {
		this.bordereauActes = bordereauActes;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}


	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}

	public List<Acte> getActesLiquidateur() {
		return actesLiquidateur;
	}

	@JsonIgnore
	public void setActesLiquidateur(List<Acte> actesLiquidateur) {
		this.actesLiquidateur = actesLiquidateur;
	}

	public List<Quittance> getQuittanceCaissier() {
		return quittanceCaissier;
	}

	@JsonIgnore
	public void setQuittanceCaissier(List<Quittance> quittanceCaissier) {
		this.quittanceCaissier = quittanceCaissier;
	}
}
