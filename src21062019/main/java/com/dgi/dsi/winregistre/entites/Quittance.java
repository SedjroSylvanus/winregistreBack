package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.OverridesAttribute;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="quittance")
public class Quittance extends EntityBaseBean implements Serializable {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long quittanceId;

    private String numeroQuittance;
    private LocalDate dateQuittance;
    private LocalDate dateCheque;
//    private Double netAPaye = 0.;
    private Double montantPaye = 0.;
    private Double montantAutrePaiement = 0.;
//    private Double montantDu = 0.;
    private String referenceCheque;
    private String observation;
    private Boolean validation = Boolean.FALSE;

    @ManyToOne
    private BordereauCaisse bordereauCaisse;


    @ManyToOne
    private TypeProduit typeProduit;

    @ManyToOne
    private ModePaiement modePaiement;

    @ManyToOne
    private Banque banque;


    @OneToMany(mappedBy = "quittance")
    private List<DetailQuittance> quittancesDetail = new ArrayList<>();


    @ManyToOne
    private Acte acteQuittance;

    @ManyToOne//(fetch = FetchType.LAZY)
    private Agent caissier;

    public Quittance() {
    }

    public String getNumeroQuittance() {
        return numeroQuittance;
    }

    public void setNumeroQuittance(String numeroQuittance) {
        this.numeroQuittance = numeroQuittance;
    }

    public LocalDate getDateQuittance() {
        return dateQuittance;
    }

    public void setDateQuittance(LocalDate dateQuittance) {
        this.dateQuittance = dateQuittance;
    }

//    @JsonIgnore
    public Acte getActeQuittance() {
        return acteQuittance;
    }

    public void setActeQuittance(Acte acteQuittance) {
        this.acteQuittance = acteQuittance;
    }

    public BordereauCaisse getBordereauCaisse() {
        return bordereauCaisse;
    }

    public void setBordereauCaisse(BordereauCaisse bordereauCaisse) {
        this.bordereauCaisse = bordereauCaisse;
    }


    public LocalDate getDateCheque() {
        return dateCheque;
    }

    public void setDateCheque(LocalDate dateCheque) {
        this.dateCheque = dateCheque;
    }

//    public Double getNetAPaye() {
//        return netAPaye;
//    }
//
//    public void setNetAPaye(Double netAPaye) {
//        this.netAPaye = netAPaye;
//    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

//    public Double getMontantDu() {
//        return montantDu;
//    }
//
//    public void setMontantDu(Double montantDu) {
//        this.montantDu = montantDu;
//    }

    public String getReferenceCheque() {
        return referenceCheque;
    }

    public void setReferenceCheque(String referenceCheque) {
        this.referenceCheque = referenceCheque;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public List<DetailQuittance> getQuittancesDetail() {
        return quittancesDetail;
    }

    @JsonIgnore
    public void setQuittancesDetail(List<DetailQuittance> quittancesDetail) {
        this.quittancesDetail = quittancesDetail;
    }

    public Agent getCaissier() {
        return caissier;
    }

    public void setCaissier(Agent caissier) {
        this.caissier = caissier;
    }

    public Double getMontantAutrePaiement() {
        return montantAutrePaiement;
    }

    public void setMontantAutrePaiement(Double montantAutrePaiement) {
        this.montantAutrePaiement = montantAutrePaiement;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    @Override
    public String toString() {
        return "Quittance{" +
                "numeroQuittance='" + numeroQuittance + '\'' +
                ", dateQuittance=" + dateQuittance +
                ", dateCheque=" + dateCheque +
                ", montantPaye=" + montantPaye +
                ", montantAutrePaiement=" + montantAutrePaiement +
                ", referenceCheque='" + referenceCheque + '\'' +
                ", observation='" + observation + '\'' +
                ", validation=" + validation +
                ", bordereauCaisse=" + bordereauCaisse +
                ", typeProduit=" + typeProduit +
                ", modePaiement=" + modePaiement +
                ", banque=" + banque +
                ", quittancesDetail=" + quittancesDetail +
                ", acteQuittance=" + acteQuittance +
                ", caissier=" + caissier +
                '}';
    }
}
