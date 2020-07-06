package com.dgi.dsi.winregistre.entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class AppUser {
	@Id @GeneratedValue
	private Long id;


	private String nom;
	private String prenom;
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	@Column(unique=true)
	private String username;
	private String password;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private long tel;
	private String photo;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "confirmation_token")
	private String confirmationToken;
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	@ManyToMany(fetch=FetchType.EAGER)	
	private Collection<AppRole> roles = new ArrayList<>();
	


	/*@OneToMany(mappedBy="utilisateur")
	private List<Compte> comptes= new ArrayList<>();*/
	
	
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getConfirmationToken() {
		return confirmationToken;
	}
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Collection<AppRole> getRoles() {
		return roles;
	}
	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}	




	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	
	/*public AppUser(String nom, String prenom, Date dateNaissance, String username, String password, String email,
			long tel, String photo, boolean enabled, String confirmationToken, Date expiryDate,
			Collection<AppRole> roles, List<Bien> biens, List<Compte> comptes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.photo = photo;
		this.enabled = enabled;
		this.confirmationToken = confirmationToken;
		this.expiryDate = expiryDate;
		this.roles = roles;
		this.biens = biens;
		this.comptes = comptes;
	}*/
	
		
	/*public AppUser(String nom, String prenom, Date dateNaissance, String username, String password, String email,
			long tel, Collection<AppRole> roles, List<Bien> biens, List<Compte> comptes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.roles = roles;
		this.biens = biens;
		this.comptes = comptes;
	}*/
	
	public AppUser(String nom, String prenom, Date dateNaissance, String username, String password, String email,
			long tel, String photo, Collection<AppRole> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.photo = photo;
		this.roles = roles;		
	}
	
	
	
	
	
	
	public AppUser(String nom, String prenom, Date dateNaissance, String username, String password, String email,
			long tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;		
	}
	
	
	public AppUser(String nom, String prenom, Date dateNaissance, String email,	long tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;		
		this.email = email;
		this.tel = tel;		
	}




	public AppUser(String nom, String prenom, String username, String password, long tel) {
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.tel = tel;
	}

	public AppUser(String nom, String prenom, Date dateNaissance, String username, String password, String email,
				   long tel, String photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.photo = photo;
	}
	
	
	
	public AppUser(String nom, String prenom, Date dateNaissance, String username, String password, String email,
			long tel, Collection<AppRole> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.roles = roles;
	}


	public AppUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", tel=" + tel + ", photo="
				+ photo + ", enabled=" + enabled + ", confirmationToken=" + confirmationToken + ", roles=" + roles
				+ "]";
	}		
	
	
	
}
