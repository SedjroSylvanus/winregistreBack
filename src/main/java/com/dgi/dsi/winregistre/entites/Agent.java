package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.dgi.dsi.winregistre.parent.entites.Personne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "agent")

//		, uniqueConstraints = { @UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) })
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class Agent extends Personne  implements Serializable {

    private static final long serialVersionUID = 1L;



    @NotBlank
	private String matricule;
	private Boolean caissier;
	private String indiceCaisse;
//	`CAI_Code` VARCHAR(10)  NOT NULL ,
	private String cServiceRecette;
	private String cServiceAssiette;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt = new Date();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt  = new Date();;



	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();





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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }







	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}




	public Boolean getCaissier() {
		return caissier;
	}

	public void setCaissier(Boolean caissier) {
		this.caissier = caissier;
	}

	public String getIndiceCaisse() {
		return indiceCaisse;
	}

	public void setIndiceCaisse(String indiceCaisse) {
		this.indiceCaisse = indiceCaisse;
	}

	public String getcServiceRecette() {
		return cServiceRecette;
	}

	public void setcServiceRecette(String cServiceRecette) {
		this.cServiceRecette = cServiceRecette;
	}

	public String getcServiceAssiette() {
		return cServiceAssiette;
	}

	public void setcServiceAssiette(String cServiceAssiette) {
		this.cServiceAssiette = cServiceAssiette;
	}

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
