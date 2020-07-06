package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acte", schema = "winregist")
//@Table(name = "acte")

//		, uniqueConstraints = { @UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) })
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


@Audited
public class Acte extends EntityBaseBean implements Serializable {

    private static final long serialVersionUID = 1L;


//    @Column(unique = true)
    private String numero;



//    @Convert(converter = LocalDateConverter.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDate dateActe = LocalDate.now();
    
    private LocalDate dateAcquisitionActuelle;
    private LocalDate ancienneDateAcquisition;
    

    private Double montantAcquisitionActuelle;
    private Double ancienMontantAcquisition;
    private Double plusValueImmobiliere;

    
    private LocalDate dateDepot;
    
//    @Convert(converter = LocalDateConverter.class)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateEnregistrement = LocalDate.now();

    private String reference;
//    private Double provision;
    private Double droitSimple;

    private Double penalite;
    private Double amende;
//    private String etat;

//    private String code;


    private Double valeurTimbre;
    private Double nombreTimbre;

    private Double montantActe;
    private Double manqueeGain;
    private String designationMarche;
    private String designation;

    private Integer folioSommierAss;
    private Integer caseSommierAss;
    private String volumeSommierAss;
    private Integer numeroOrdreSommierAss;

    private LocalDate dateEnregistrementSommier;


//    private String depotSousMinite;

    private Integer nombreEnfantSuccession;

    //Table journal pour l'historique du statut courant d'un acte
    private String statutCourant;

    private LocalDate dateStatut;
    private String beneficiaireMarche;
    private Double redevance;

    private LocalDate dateDebutBail;
    private LocalDate dateFinBail;

    private String numeroTransfert;
    private String nomParties;
    private String nomPrestataireSansIfu;
    private String nomBeneficiaireSansIfu;
    private String souceFinancementMarche;
    private Boolean sourceInterne;

    private Double montantDu; //net à payer - montant paye

    //Complément de droit à gérer
    private Double montantPaye; //Complément de droit à gérer

    private Boolean contratRenouvelable;



    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private BordereauActe bordereauActe;
    

    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private BordereauActe bordereauActeContrib;
    
    
    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentDepot;

    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentLiquidateur;
    
    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentCaisse;
    
    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentValidation;
    
    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentSommier;
    
    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentTransfert;

    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentValidateurFinal;


    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent agentRejet;
    
    

    public Agent getAgentRejet() {
		return agentRejet;
	}

	public void setAgentRejet(Agent agentRejet) {
		this.agentRejet = agentRejet;
	}

	public Agent getAgentValidateurFinal() {
		return agentValidateurFinal;
	}

	public void setAgentValidateurFinal(Agent agentValidateurFinal) {
		this.agentValidateurFinal = agentValidateurFinal;
	}

	public BordereauActe getBordereauActeContrib() {
		return bordereauActeContrib;
	}

	public void setBordereauActeContrib(BordereauActe bordereauActeContrib) {
		this.bordereauActeContrib = bordereauActeContrib;
	}

	@ManyToOne
    private Commune communeActe;

    @ManyToOne
    private Commune communeSituationObjet;

    @ManyToOne
    @NotAudited
    private NatureActe natureActe;


    @ManyToOne
    @NotAudited
    private ContribuableBis contribuablePrestataire;


    @ManyToOne
    @NotAudited
    private ContribuableBis contribuableBeneficiaire                                                                                                                                                                                                                                                                                                         ;


    @ManyToOne
    @NotAudited
    private PenaliteAmende penaliteAmende;

    @OneToMany(mappedBy = "acteQuittance")
    @JsonIgnore
    private List<Quittance> quittances = new  ArrayList<>();

    public BordereauActe getBordereauActe() {
        return bordereauActe;
    }

    public void setBordereauActe(BordereauActe bordereauActe) {
        this.bordereauActe = bordereauActe;
    }

    public Agent getAgentLiquidateur() {
        return agentLiquidateur;
    }

    public void setAgentLiquidateur(Agent agentLiquidateur) {
        this.agentLiquidateur = agentLiquidateur;
    }

    public Acte() {
    }

    public Acte(String numero, LocalDate dateActe, LocalDate dateEnregistrement,
                String reference, Double droitSimple, Double penalite, Double amende,
                Double valeurTimbre, Double nombreTimbre, Double montantActe,
                Double manqueeGain, String designationMarche, String designation,
                Integer folioSommierAss, Integer caseSommierAss, String volumeSommierAss,
                Integer numeroOrdreSommierAss, Integer nombreEnfantSuccession, String statutCourant, LocalDate dateStatut, String beneficiaireMarche, Double redevance, LocalDate dateDebutBail, LocalDate dateFinBail, String numeroTransfert, String nomParties, String nomPrestataireSansIfu, String nomBeneficiaireSansIfu, String souceFinancementMarche, Boolean sourceInterne, Double montantDu, Double montantPaye, Boolean contratRenouvelable, BordereauActe bordereauActe, Agent agentLiquidateur, Agent agentCaisse, Agent agentValidation, Agent agentSommier, Agent agentTransfert, Commune communeActe, Commune communeSituationObjet, NatureActe natureActe, ContribuableBis contribuablePrestataire, ContribuableBis contribuableBeneficiaire, PenaliteAmende penaliteAmende, List<Quittance> quittances) {
        this.numero = numero;
        this.dateActe = dateActe;
        this.dateEnregistrement = dateEnregistrement;
        this.reference = reference;
        this.droitSimple = droitSimple;
        this.penalite = penalite;
        this.amende = amende;
        this.valeurTimbre = valeurTimbre;
        this.nombreTimbre = nombreTimbre;
        this.montantActe = montantActe;
        this.manqueeGain = manqueeGain;
        this.designationMarche = designationMarche;
        this.designation = designation;
        this.folioSommierAss = folioSommierAss;
        this.caseSommierAss = caseSommierAss;
        this.volumeSommierAss = volumeSommierAss;
        this.numeroOrdreSommierAss = numeroOrdreSommierAss;
        this.nombreEnfantSuccession = nombreEnfantSuccession;
        this.statutCourant = statutCourant;
        this.dateStatut = dateStatut;
        this.beneficiaireMarche = beneficiaireMarche;
        this.redevance = redevance;
        this.dateDebutBail = dateDebutBail;
        this.dateFinBail = dateFinBail;
        this.numeroTransfert = numeroTransfert;
        this.nomParties = nomParties;
        this.nomPrestataireSansIfu = nomPrestataireSansIfu;
        this.nomBeneficiaireSansIfu = nomBeneficiaireSansIfu;
        this.souceFinancementMarche = souceFinancementMarche;
        this.sourceInterne = sourceInterne;
        this.montantDu = montantDu;
        this.montantPaye = montantPaye;
        this.contratRenouvelable = contratRenouvelable;
        this.bordereauActe = bordereauActe;
        this.agentLiquidateur = agentLiquidateur;
        this.agentCaisse = agentCaisse;
        this.agentValidation = agentValidation;
        this.agentSommier = agentSommier;
        this.agentTransfert = agentTransfert;
        this.communeActe = communeActe;
        this.communeSituationObjet = communeSituationObjet;
        this.natureActe = natureActe;
        this.contribuablePrestataire = contribuablePrestataire;
        this.contribuableBeneficiaire = contribuableBeneficiaire;
        this.penaliteAmende = penaliteAmende;
        this.quittances = quittances;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDateActe() {
        return dateActe;
    }

    public void setDateActe(LocalDate dateActe) {
        this.dateActe = dateActe;
    }

    public LocalDate getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(LocalDate dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getDroitSimple() {
        return droitSimple;
    }

    public void setDroitSimple(Double droitSimple) {
        this.droitSimple = droitSimple;
    }

    public Double getPenalite() {
        return penalite;
    }

    public void setPenalite(Double penalite) {
        this.penalite = penalite;
    }

    public Double getAmende() {
        return amende;
    }

    public void setAmende(Double amende) {
        this.amende = amende;
    }

    public Double getValeurTimbre() {
        return valeurTimbre;
    }

    public void setValeurTimbre(Double valeurTimbre) {
        this.valeurTimbre = valeurTimbre;
    }

    public Double getNombreTimbre() {
        return nombreTimbre;
    }

    public void setNombreTimbre(Double nombreTimbre) {
        this.nombreTimbre = nombreTimbre;
    }

    public Double getMontantActe() {
        return montantActe;
    }

    public void setMontantActe(Double montantActe) {
        this.montantActe = montantActe;
    }

    public String getDesignationMarche() {
        return designationMarche;
    }

    public void setDesignationMarche(String designationMarche) {
        this.designationMarche = designationMarche;
    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Integer getNombreEnfantSuccession() {
        return nombreEnfantSuccession;
    }

    public void setNombreEnfantSuccession(Integer nombreEnfantSuccession) {
        this.nombreEnfantSuccession = nombreEnfantSuccession;
    }

    public String getStatutCourant() {
        return statutCourant;
    }

    public void setStatutCourant(String statutCourant) {
        this.statutCourant = statutCourant;
    }

    public LocalDate getDateStatut() {
        return dateStatut;
    }

    public void setDateStatut(LocalDate dateStatut) {
        this.dateStatut = dateStatut;
    }

//    public Agent getAgentLiquidateur() {
//        return agentLiquidateur;
//    }
//
//    public void setAgentLiquidateur(Agent agentLiquidateur) {
//        this.agentLiquidateur = agentLiquidateur;
//    }

    public Agent getAgentCaisse() {
        return agentCaisse;
    }

    public void setAgentCaisse(Agent agentCaisse) {
        this.agentCaisse = agentCaisse;
    }

    public Agent getAgentValidation() {
        return agentValidation;
    }

    public void setAgentValidation(Agent agentValidation) {
        this.agentValidation = agentValidation;
    }

    public Agent getAgentSommier() {
        return agentSommier;
    }

    public void setAgentSommier(Agent agentSommier) {
        this.agentSommier = agentSommier;
    }

    public Agent getAgentTransfert() {
        return agentTransfert;
    }

    public void setAgentTransfert(Agent agentTransfert) {
        this.agentTransfert = agentTransfert;
    }

    public String getBeneficiaireMarche() {
        return beneficiaireMarche;
    }

    public void setBeneficiaireMarche(String beneficiaireMarche) {
        this.beneficiaireMarche = beneficiaireMarche;
    }

    public Double getRedevance() {
        return redevance;
    }

    public void setRedevance(Double redevance) {
        this.redevance = redevance;
    }

    public LocalDate getDateDebutBail() {
        return dateDebutBail;
    }

    public void setDateDebutBail(LocalDate dateDebutBail) {
        this.dateDebutBail = dateDebutBail;
    }

    public LocalDate getDateFinBail() {
        return dateFinBail;
    }

    public void setDateFinBail(LocalDate dateFinBail) {
        this.dateFinBail = dateFinBail;
    }

    public String getNumeroTransfert() {
        return numeroTransfert;
    }

    public void setNumeroTransfert(String numeroTransfert) {
        this.numeroTransfert = numeroTransfert;
    }

    public String getNomParties() {
        return nomParties;
    }

    public void setNomParties(String nomParties) {
        this.nomParties = nomParties;
    }

    public String getNomContribuableSansIfu() {
        return nomPrestataireSansIfu;
    }

    public void setNomContribuableSansIfu(String nomPrestataireSansIfu) {
        this.nomPrestataireSansIfu = nomPrestataireSansIfu;
    }

    public String getNomBeneficiaireSansIfu() {
        return nomBeneficiaireSansIfu;
    }

    public void setNomBeneficiaireSansIfu(String nomBeneficiaireSansIfu) {
        this.nomBeneficiaireSansIfu = nomBeneficiaireSansIfu;
    }

    public String getSouceFinancementMarche() {
        return souceFinancementMarche;
    }

    public void setSouceFinancementMarche(String souceFinancementMarche) {
        this.souceFinancementMarche = souceFinancementMarche;
    }

    public Boolean getContratRenouvelable() {
        return contratRenouvelable;
    }

    public void setContratRenouvelable(Boolean contratRenouvelable) {
        this.contratRenouvelable = contratRenouvelable;
    }

    public Commune getCommuneActe() {
        return communeActe;
    }

    public void setCommuneActe(Commune communeActe) {
        this.communeActe = communeActe;
    }

    public NatureActe getNatureActe() {
        return natureActe;
    }

    public void setNatureActe(NatureActe natureActe) {
        this.natureActe = natureActe;
    }


    public ContribuableBis getContribuablePrestataire() {
        return contribuablePrestataire;
    }

    public void setContribuablePrestataire(ContribuableBis contribuablePrestataire) {
        this.contribuablePrestataire = contribuablePrestataire;
    }

    public ContribuableBis getContribuableBeneficiaire() {
        return contribuableBeneficiaire;
    }

    public void setContribuableBeneficiaire(ContribuableBis contribuableBeneficiaire) {
        this.contribuableBeneficiaire = contribuableBeneficiaire;
    }

    public PenaliteAmende getPenaliteAmende() {
        return penaliteAmende;
    }

    public void setPenaliteAmende(PenaliteAmende penaliteAmende) {
        this.penaliteAmende = penaliteAmende;
    }

    public List<Quittance> getQuittances() {
        return quittances;
    }

    public Double getMontantDu() {
        return montantDu;
    }

    public void setMontantDu(Double montantDu) {
        this.montantDu = montantDu;
    }

//    @JsonIgnore
    public void setQuittances(List<Quittance> quittances) {
        this.quittances = quittances;
    }

    public Integer getFolioSommierAss() {
        return folioSommierAss;
    }

    public void setFolioSommierAss(Integer folioSommierAss) {
        this.folioSommierAss = folioSommierAss;
    }

    public Integer getCaseSommierAss() {
        return caseSommierAss;
    }

    public void setCaseSommierAss(Integer caseSommierAss) {
        this.caseSommierAss = caseSommierAss;
    }

    public String getVolumeSommierAss() {
        return volumeSommierAss;
    }

    public void setVolumeSommierAss(String volumeSommierAss) {
        this.volumeSommierAss = volumeSommierAss;
    }

    public Integer getNumeroOrdreSommierAss() {
        return numeroOrdreSommierAss;
    }

    public void setNumeroOrdreSommierAss(Integer numeroOrdreSommierAss) {
        this.numeroOrdreSommierAss = numeroOrdreSommierAss;
    }

    public Double getManqueeGain() {
        return manqueeGain;
    }

    public void setManqueeGain(Double manqueeGain) {
        this.manqueeGain = manqueeGain;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Commune getCommuneSituationObjet() {
        return communeSituationObjet;
    }

    public void setCommuneSituationObjet(Commune communeSituationObjet) {
        this.communeSituationObjet = communeSituationObjet;
    }


    @Override
    public String toString() {
        return "Acte{" +
                "numero='" + numero + '\'' +
                ", dateActe=" + dateActe +
                ", dateEnregistrement=" + dateEnregistrement +
                ", reference='" + reference + '\'' +
                ", droitSimple=" + droitSimple +
                ", penalite=" + penalite +
                ", amende=" + amende +
                ", valeurTimbre=" + valeurTimbre +
                ", nombreTimbre=" + nombreTimbre +
                ", montantActe=" + montantActe +
                ", manqueeGain=" + manqueeGain +
                ", designationMarche='" + designationMarche + '\'' +
                ", designation='" + designation + '\'' +
                ", folioSommierAss='" + folioSommierAss + '\'' +
                ", caseSommierAss='" + caseSommierAss + '\'' +
                ", volumeSommierAss='" + volumeSommierAss + '\'' +
                ", numeroOrdreSommierAss='" + numeroOrdreSommierAss + '\'' +
                ", nombreEnfantSuccession=" + nombreEnfantSuccession +
                ", statutCourant='" + statutCourant + '\'' +
                ", dateStatut=" + dateStatut +
                ", beneficiaireMarche='" + beneficiaireMarche + '\'' +
                ", redevance=" + redevance +
                ", dateDebutBail=" + dateDebutBail +
                ", dateFinBail=" + dateFinBail +
                ", numeroTransfert='" + numeroTransfert + '\'' +
                ", nomParties='" + nomParties + '\'' +
                ", nomPrestataireSansIfu='" + nomPrestataireSansIfu + '\'' +
                ", nomBeneficiaireSansIfu='" + nomBeneficiaireSansIfu + '\'' +
                ", souceFinancementMarche='" + souceFinancementMarche + '\'' +
                ", sourceInterne=" + sourceInterne +
                ", montantDu=" + montantDu +
                ", montantPaye=" + montantPaye +
                ", contratRenouvelable=" + contratRenouvelable +
                ", bordereauActe=" + bordereauActe +
                ", agentLiquidateur=" + agentLiquidateur +
                ", agentCaisse=" + agentCaisse +
                ", agentValidation=" + agentValidation +
                ", agentSommier=" + agentSommier +
                ", agentTransfert=" + agentTransfert +
                ", communeActe=" + communeActe +
                ", communeSituationObjet=" + communeSituationObjet +
                ", natureActe=" + natureActe +
                ", contribuablePrestataire=" + contribuablePrestataire +
                ", contribuableBeneficiaire=" + contribuableBeneficiaire +
                ", penaliteAmende=" + penaliteAmende +
                ", quittances=" + quittances +
                "} " + super.toString();
    }

    public String getNomPrestataireSansIfu() {
        return nomPrestataireSansIfu;
    }

    public void setNomPrestataireSansIfu(String nomPrestataireSansIfu) {
        this.nomPrestataireSansIfu = nomPrestataireSansIfu;
    }

    public Boolean getSourceInterne() {
        return sourceInterne;
    }

    public void setSourceInterne(Boolean sourceInterne) {
        this.sourceInterne = sourceInterne;
    }

    public LocalDate getDateEnregistrementSommier() {
        return dateEnregistrementSommier;
    }

    public void setDateEnregistrementSommier(LocalDate dateEnregistrementSommier) {
        this.dateEnregistrementSommier = dateEnregistrementSommier;
    }

	public LocalDate getDateAcquisitionActuelle() {
		return dateAcquisitionActuelle;
	}

	public void setDateAcquisitionActuelle(LocalDate dateAcquisitionActuelle) {
		this.dateAcquisitionActuelle = dateAcquisitionActuelle;
	}

	public LocalDate getAncienneDateAcquisition() {
		return ancienneDateAcquisition;
	}

	public void setAncienneDateAcquisition(LocalDate ancienneDateAcquisition) {
		this.ancienneDateAcquisition = ancienneDateAcquisition;
	}

	public LocalDate getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(LocalDate dateDepot) {
		this.dateDepot = dateDepot;
	}

	public Agent getAgentDepot() {
		return agentDepot;
	}

	public void setAgentDepot(Agent agentDepot) {
		this.agentDepot = agentDepot;
	}

	public Double getMontantAcquisitionActuelle() {
		return montantAcquisitionActuelle;
	}

	public void setMontantAcquisitionActuelle(Double montantAcquisitionActuelle) {
		this.montantAcquisitionActuelle = montantAcquisitionActuelle;
	}

	public Double getAncienMontantAcquisition() {
		return ancienMontantAcquisition;
	}

	public void setAncienMontantAcquisition(Double ancienMontantAcquisition) {
		this.ancienMontantAcquisition = ancienMontantAcquisition;
	}

	public Double getPlusValueImmobiliere() {
		return plusValueImmobiliere;
	}

	public void setPlusValueImmobiliere(Double plusValueImmobiliere) {
		this.plusValueImmobiliere = plusValueImmobiliere;
	}



    
    
}
