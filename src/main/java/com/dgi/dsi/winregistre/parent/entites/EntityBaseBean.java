package com.dgi.dsi.winregistre.parent.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityBaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.AUTO, generator="seqgen")
//    @SequenceGenerator(name="seqgen", sequenceName="winregist.DB_AUTOINC_SEQ")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hibernate_sequence")
//    @SequenceGenerator(name="hibernate_sequence", sequenceName="hibernate_sequence", allocationSize=1)

    private Long id;


//	@Id
//	@GeneratedValue(
//			generator = "sequence",
//			strategy = GenerationType.SEQUENCE
//	)
//	@SequenceGenerator(
//			name = "sequence",
//			allocationSize = 10
//	)
//	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModification ;

	private String encodeur;
	private String Observation;



	@PreUpdate
	public void PreUpdate() {

		dateModification = new Date();
        Observation = "Modification par Front End Applicatif";

	}



	@PostUpdate
	public void updateEncodeur() {


	}


    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
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

	@Override
	public String toString() {
		return "EntityBaseBean{" +
				"id=" + id +
				", dateCreation=" + dateCreation +
				", dateModification=" + dateModification +
				", encodeur='" + encodeur + '\'' +
				'}';
	}
}