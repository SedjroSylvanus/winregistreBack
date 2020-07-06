package com.dgi.dsi.winregistre.parent.entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@MappedSuperclass
public abstract class EntityBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;


//	@Id
//	@GeneratedValue(generator = "hibernate_generator")
//	@SequenceGenerator(
//			name = "hibernate_generator",
//			sequenceName = "hibernate_sequence",
//			initialValue = 1000
//	)

	@Id
//	@GeneratedValue(
//			generator = "sequence",
//			strategy = GenerationType.SEQUENCE)
//	@SequenceGenerator(
//			name = "sequence",
//			allocationSize = 10
//	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation = new Date();

	private String encodeur;



	@PreUpdate
	public void PreUpdate() {


	}

	@PostUpdate
	public void updateEncodeur() {


	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getEncodeur() {
		return encodeur;
	}

	public void setEncodeur(String encodeur) {
		this.encodeur = encodeur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EntityBaseBean() {
		super();
	}



}