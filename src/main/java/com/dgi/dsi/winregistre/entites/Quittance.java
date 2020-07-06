package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="quittance", schema = "winregist")
@Audited
public class Quittance extends EntityBaseBean implements Serializable {


    private String numeroQuittance;
    private LocalDate dateQuittance;
    private LocalDate dateCheque;
//    private Double netAPaye = 0.;
    private Double montantPaye = 0.;
    private Double montantAutrePaiement = 0.;
//    private Double montantDu = 0.;
    private String referenceCheque;
    private String observationQuittance;
    private String account;
//    private String numtransaction;
    private Boolean validation = Boolean.FALSE;

    @ManyToOne
    @NotAudited
    private BordereauCaisse bordereauCaisse;


    @ManyToOne
    @NotAudited
    private TypeProduit typeProduit;

    @ManyToOne
    @NotAudited
    private ModePaiement modePaiement;

    @ManyToOne
    @NotAudited
    private Banque banque;


    @OneToMany(mappedBy = "quittance")
    @NotAudited
    private List<DetailQuittance> quittancesDetail = new ArrayList<>();


    @ManyToOne//(fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JoinColumn(name = "acte_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Acte acteQuittance;

    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private Agent caissier;

    @ManyToOne//(fetch = FetchType.LAZY)
    @NotAudited
    private NatureImpot natureImpot;

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

    public String getObservationQuittance() {
        return observationQuittance;
    }

    public void setObservationQuittance(String observationQuittance) {
        this.observationQuittance = observationQuittance;
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
                ", observationQuittance='" + observationQuittance + '\'' +
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

    public NatureImpot getNatureImpot() {
        return natureImpot;
    }

    public void setNatureImpot(NatureImpot natureImpot) {
        this.natureImpot = natureImpot;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

//    public String getNumtransaction() {
//        return numtransaction;
//    }
//
//    public void setNumtransaction(String numtransaction) {
//        this.numtransaction = numtransaction;
//    }
}
