package com.dgi.dsi.winregistre.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;

@Entity
@Table(name = "avoirstatut", schema = "winregist")
public class AvoirStatut  implements Serializable { //extends EntityBaseBean

	private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @GeneratedValue(strategy = GenerationType.AUTO, generator="seqgen")
//    @SequenceGenerator(name="seqgen", sequenceName="winregist.DB_AUTOINC_SEQ")


    private Long id;



    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification ;

    private String encodeur;
    private String Observation;











    private String numero;

	private String designation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;

	private String commentaire;
	
	@ManyToOne
	private Statut statut;
	private String  statutString;

	@ManyToOne
	private Agent agent;

	@ManyToOne
	private MotifRejet motifrejet;
	private String motifrejetString;

//	Act_Num` varchar(25) DEFAULT NULL, pour le num d'acte

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public MotifRejet getMotifrejet() {
		return motifrejet;
	}

	public void setMotifrejet(MotifRejet motifrejet) {
		this.motifrejet = motifrejet;
	}

	public String getDesignation() {
		return designation;
	}

	public AvoirStatut(String designation) {
		super();
		this.designation = designation;
	}

	public AvoirStatut() {
		super();
	}

    public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getStatutString() {
		return statutString;
	}

	public void setStatutString(String statutString) {
		this.statutString = statutString;
	}

	public String getMotifrejetString() {
		return motifrejetString;
	}

	public void setMotifrejetString(String motifrejetString) {
		this.motifrejetString = motifrejetString;
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


    public Date getDateModification() {
        return dateModification;
    }


    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }


    public String getEncodeur() {
        return encodeur;
    }


    public void setEncodeur(String encodeur) {
        this.encodeur = encodeur;
    }


    public String getObservation() {
        return Observation;
    }


    public void setObservation(String observation) {
        Observation = observation;
    }


    public String toString() {
        return "AvoirStatut{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                ", encodeur='" + encodeur + '\'' +
                ", Observation='" + Observation + '\'' +
                ", numero='" + numero + '\'' +
                ", designation='" + designation + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", commentaire='" + commentaire + '\'' +
                ", statut=" + statut +
                ", statutString='" + statutString + '\'' +
                ", agent=" + agent +
                ", motifrejet=" + motifrejet +
                ", motifrejetString='" + motifrejetString + '\'' +
                '}';
    }
}
