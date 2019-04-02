package com.dgi.dsi.winregistre.entites;

import com.dgi.dsi.winregistre.parent.entites.EntityBaseBean;
import com.dgi.dsi.winregistre.parent.entites.Personne;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "acte")

//		, uniqueConstraints = { @UniqueConstraint(name = "nom_prenom", columnNames = { "nom", "prenoms" }),
//		@UniqueConstraint(name = "email", columnNames = { "email" }) })
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)


public class Acte extends EntityBaseBean implements Serializable {

    private static final long serialVersionUID = 1L;


    private String numero;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateActe = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnregistrement = new Date();

    private String reference;
    private Double provision;
    private Double droitSimple;

    private Double penalite;
    private Double amende;
    private String etat;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategorieActe categorie;

    //    `TA_Code` VARCHAR(20)  NOT NULL ,
    private String code;



    private Integer numeroOrdre;
    private String tarif;
    private Double taux;
    private Double montantFixe;
    private Double montantMinimum;
    private Double montantMaximum;
    private Double totalApayer;
    private String timbre;
    private Integer nombrePageTimbre;
    private Double montantTotalTimbre;
    private Double montantActe;
//			`COM_CODe` VARCHAR(100)  NOT NULL ,

    private Integer nombreDroitEnregistrement;
    private String designationMarche;
    private Double totalDroitEnregistrement;
//			`DepotSousMinute` TINYINT  NOT NULL  DEFAULT 0,

    private Double montantPaye;
//			`NbreEnfant_Succ` INTEGER  NOT NULL  DEFAULT 0,

    private String statutCourant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStatut;


    @ManyToOne(fetch = FetchType.EAGER)
    private Agent agentLiquidateur;
//    `SOM_Vol` VARCHAR(20)  NOT NULL ,
//    `Som_Folio` VARCHAR(50)  NOT NULL ,
//    `Som_case` VARCHAR(50)  NOT NULL ,
//    `SOM_DateEnr` DATE  NOT NULL ,
//			`CONT_NUM_BEN` VARCHAR(13)  NOT NULL ,


    private String beneficiaireMarche;
    private Double redevance;
//			`tauxRed` REAL  NOT NULL  DEFAULT 0,
//			`Gratis` TINYINT  NOT NULL  DEFAULT 0,
//			`Quittancer` TINYINT  NOT NULL  DEFAULT 0,
//			`HeureStatu` TIME  NOT NULL ,
//			`ManqueGain` NUMERIC(24,6)  NOT NULL  DEFAULT 0,
//			`ManqueGainPen` NUMERIC(24,6)  NOT NULL  DEFAULT 0,
//			`SEL_TRANS` TINYINT  NOT NULL  DEFAULT 0,
//			`SEL_VAL` TINYINT  NOT NULL  DEFAULT 0,
//			`SEL_SOM` TINYINT  NOT NULL  DEFAULT 0,

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebutBail;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinBail;

    @ManyToOne(fetch = FetchType.EAGER)
    private Agent agentCaisse;
    @ManyToOne(fetch = FetchType.EAGER)
    private Agent agentValidation;
    @ManyToOne(fetch = FetchType.EAGER)
    private Agent agentSommier;
    @ManyToOne(fetch = FetchType.EAGER)
    private Agent agentTransfert;


    private String numeroTransfert;
    private String nomParties;
    private String nomContribuableSansIfu;
    private String nomBeneficiaireSansIfu;
    private String financementMarche;
    private Boolean contratRenouvelable;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private Contribuable numeroContribuable;


//    @ManyToOne(fetch = FetchType.EAGER)
//    private Quittance numeroQuittance;

//			`Com_Beneficiaire_CODE` VARCHAR(100)  NOT NULL ,
//    `DGS_Num` INTEGER  NOT NULL ,
//			`BOR_Num` VARCHAR(20)  NOT NULL ,
//    `INS_Code` VARCHAR(20)  NOT NULL ,
//    `MOR_Code` VARCHAR(20)  NOT NULL ,
//    `Act_Date` DATE  NOT NULL ,
//			`Act_Etat` VARCHAR(1)  NOT NULL ,
//
//    `Act_NbreRole` INTEGER  NOT NULL  DEFAULT 0,
//			`Act_NbreLigneNul` INTEGER  NOT NULL  DEFAULT 0,
//			`Act_NbreMotNul` INTEGER  NOT NULL  DEFAULT 0,
//			`Act_NbreChiffreNul` INTEGER  NOT NULL  DEFAULT 0,


}
