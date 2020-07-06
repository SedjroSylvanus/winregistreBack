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
//	`CAI_Code` VARCHAR(10)  NOT NULL ,
//	private Boolean cServiceRecette;
//	private Boolean cServiceAssiette;

//    @Column(nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    private Date createdAt;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private Date updatedAt ;



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


//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "user_roles",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();
//
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}

	public Agent() {
		super();
	}

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }







	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}




//	public Boolean getCaissier() {
//		return caissier;
//	}
//
//	public void setCaissier(Boolean caissier) {
//		this.caissier = caissier;
//	}
//
//
//
	public String getIndiceAgent() {
		return indiceAgent;
	}

	public void setIndiceAgent(String indiceAgent) {
		this.indiceAgent = indiceAgent;
	}
//
//	public Boolean getcServiceRecette() {
//		return cServiceRecette;
//	}
//
//	public void setcServiceRecette(Boolean cServiceRecette) {
//		this.cServiceRecette = cServiceRecette;
//	}
//
//	public Boolean getcServiceAssiette() {
//		return cServiceAssiette;
//	}
//
//	public void setcServiceAssiette(Boolean cServiceAssiette) {
//		this.cServiceAssiette = cServiceAssiette;
//	}

	//	@Override
//	public String toString() {
//		return "Agent{" +
//				"matricule='" + matricule + '\'' +
//				", caissier=" + caissier +
//				", roles=" + roles +
//				'}';
//	}


	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}
}
