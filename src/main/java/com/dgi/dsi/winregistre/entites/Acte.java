package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.Convertisseur.LocalDateConverter;
import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.dgi.dsi.winregistre.parent.entites.Personne;
import com.dgi.dsi.winregistre.entites.Contribuable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "acte")

//		, uniqueConstraints = { @UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) })
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class Acte extends EntityBaseBean implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(unique = true)
    private String numero;



//    @Convert(converter = LocalDateConverter.class)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDate dateActe = LocalDate.now();
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


    private String valeurTimbre;
    private Integer nombreTimbre;

    private Double montantActe;
    private Double manqueeGain;
    private String designationMarche;
    private String designation;

    private String folioSommierAss;
    private String caseSommierAss;
    private String volumeSommierAss;
    private String numeroOrdreSommierAss;


//    private String depotSousMinite;

    private Integer nombreEnfantSuccession;

    //Table journal pour l'historique du statut courant d'un acte
    private String statutCourant;

    private LocalDate dateStatut;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agentLiquidateur;
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agentCaisse;
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agentValidation;
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agentSommier;
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agentTransfert;


    private String beneficiaireMarche;
    private Double redevance;

    private LocalDate dateDebutBail;
    private LocalDate dateFinBail;

    private String numeroTransfert;
    private String nomParties;
    private String nomContribuableSansIfu;
    private String nomBeneficiaireSansIfu;
    private String souceFinancementMarche;

    private Double montantDu; //net à payer - montant paye

    //Complément de droit à gérer
    private Double montantPaye; //Complément de droit à gérer

    private Boolean contratRenouvelable;

    @ManyToOne
    private Commune communeActe;

    @ManyToOne
    private NatureActe natureActe;


    @ManyToOne(fetch = FetchType.LAZY)
    private Contribuable contribuableBeneficiaire;
    @ManyToOne(fetch = FetchType.LAZY)
    private Contribuable contribuablePrestataire;

    @ManyToOne(fetch = FetchType.LAZY)
    private PenaliteAmende penaliteAmende;

    @OneToMany(mappedBy = "acte")
    private List<Quittance> quittances = new  ArrayList<>();

    public Acte() {
    }

    public Acte(String numero, LocalDate dateActe, LocalDate dateEnregistrement, String reference, Double droitSimple, Double penalite, Double amende, String valeurTimbre, Integer nombreTimbre, Double montantActe, String designationMarche, Double montantPaye, Integer nombreEnfantSuccession, String statutCourant, LocalDate dateStatut, Agent agentLiquidateur, Agent agentCaisse, Agent agentValidation, Agent agentSommier, Agent agentTransfert, String beneficiaireMarche, Double redevance, LocalDate dateDebutBail, LocalDate dateFinBail, String numeroTransfert, String nomParties, String nomContribuableSansIfu, String nomBeneficiaireSansIfu, String souceFinancementMarche, Boolean contratRenouvelable, Commune communeActe, NatureActe natureActe, Contribuable contribuableBeneficiaire, Contribuable contribuablePrestataire, PenaliteAmende penaliteAmende, List<Quittance> quittances) {
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
        this.designationMarche = designationMarche;
        this.montantPaye = montantPaye;
        this.nombreEnfantSuccession = nombreEnfantSuccession;
        this.statutCourant = statutCourant;
        this.dateStatut = dateStatut;
        this.agentLiquidateur = agentLiquidateur;
        this.agentCaisse = agentCaisse;
        this.agentValidation = agentValidation;
        this.agentSommier = agentSommier;
        this.agentTransfert = agentTransfert;
        this.beneficiaireMarche = beneficiaireMarche;
        this.redevance = redevance;
        this.dateDebutBail = dateDebutBail;
        this.dateFinBail = dateFinBail;
        this.numeroTransfert = numeroTransfert;
        this.nomParties = nomParties;
        this.nomContribuableSansIfu = nomContribuableSansIfu;
        this.nomBeneficiaireSansIfu = nomBeneficiaireSansIfu;
        this.souceFinancementMarche = souceFinancementMarche;
        this.contratRenouvelable = contratRenouvelable;
        this.communeActe = communeActe;
        this.natureActe = natureActe;
        this.contribuableBeneficiaire = contribuableBeneficiaire;
        this.contribuablePrestataire = contribuablePrestataire;
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

    public String getValeurTimbre() {
        return valeurTimbre;
    }

    public void setValeurTimbre(String valeurTimbre) {
        this.valeurTimbre = valeurTimbre;
    }

    public Integer getNombreTimbre() {
        return nombreTimbre;
    }

    public void setNombreTimbre(Integer nombreTimbre) {
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

    public Agent getAgentLiquidateur() {
        return agentLiquidateur;
    }

    public void setAgentLiquidateur(Agent agentLiquidateur) {
        this.agentLiquidateur = agentLiquidateur;
    }

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
        return nomContribuableSansIfu;
    }

    public void setNomContribuableSansIfu(String nomContribuableSansIfu) {
        this.nomContribuableSansIfu = nomContribuableSansIfu;
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

    public Contribuable getContribuableBeneficiaire() {
        return contribuableBeneficiaire;
    }

    public void setContribuableBeneficiaire(Contribuable contribuableBeneficiaire) {
        this.contribuableBeneficiaire = contribuableBeneficiaire;
    }

    public Contribuable getContribuablePrestataire() {
        return contribuablePrestataire;
    }

    public void setContribuablePrestataire(Contribuable contribuablePrestataire) {
        this.contribuablePrestataire = contribuablePrestataire;
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

    @JsonIgnore
    public void setQuittances(List<Quittance> quittances) {
        this.quittances = quittances;
    }

    public String getFolioSommierAss() {
        return folioSommierAss;
    }

    public void setFolioSommierAss(String folioSommierAss) {
        this.folioSommierAss = folioSommierAss;
    }

    public String getCaseSommierAss() {
        return caseSommierAss;
    }

    public void setCaseSommierAss(String caseSommierAss) {
        this.caseSommierAss = caseSommierAss;
    }

    public String getVolumeSommierAss() {
        return volumeSommierAss;
    }

    public void setVolumeSommierAss(String volumeSommierAss) {
        this.volumeSommierAss = volumeSommierAss;
    }

    public String getNumeroOrdreSommierAss() {
        return numeroOrdreSommierAss;
    }

    public void setNumeroOrdreSommierAss(String numeroOrdreSommierAss) {
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
}
