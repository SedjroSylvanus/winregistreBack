package com.dgi.dsi.winregistre.parent.entites;


import com.dgi.dsi.winregistre.entites.AvoirStatut;
import com.dgi.dsi.winregistre.entites.BordereauActe;
import com.dgi.dsi.winregistre.entites.BordereauCaisse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@MappedSuperclass
public abstract class Personne extends EntityBaseBean implements Serializable {

    private static final long serialVersionUID = 1L;





    private String nom;
    private String prenoms;
    private Boolean actif;




    @Column(unique=true)
    private String username;
    private String password;

    @Column(unique=true, length = 32, nullable = false)
    @Email(message = "Cette adresse n'est pas une adresse email valide.")
    private String email;
    @Column(unique=true)
    private long tel;


    @OneToMany(mappedBy="agent")
    private List<AvoirStatut> avoirstatuts = new ArrayList<>();

    @OneToMany(mappedBy="agent")
    private List<BordereauActe> borderauActes = new ArrayList<>();

    @OneToMany(mappedBy="agent")
    private List<BordereauCaisse> bordereauCaisses = new ArrayList<>();


    public List<BordereauCaisse> getBordereauCaisses() {
        return bordereauCaisses;
    }

    @JsonIgnore
    public void setBordereauCaisses(List<BordereauCaisse> bordereauCaisses) {
        this.bordereauCaisses = bordereauCaisses;
    }

    public List<BordereauActe> getBorderauActes() {
        return borderauActes;
    }

    @JsonIgnore
    public void setBorderauActes(List<BordereauActe> borderauActes) {
        this.borderauActes = borderauActes;
    }

    public List<AvoirStatut> getAvoirstatuts() {
        return avoirstatuts;
    }

    @JsonIgnore
    public void setAvoirstatuts(List<AvoirStatut> avoirstatuts) {
        this.avoirstatuts = avoirstatuts;
    }




    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

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

    public Personne() {
    }


    public Personne(String nom, String prenoms, String username, String password, String email, long tel) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenoms='" + prenoms + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", tel=" + tel +
                '}';
    }
}
