package com.dgi.dsi.winregistre.entites.traitementCours;


import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.Agent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class WorkingOn implements Serializable {

    private Acte acte;
    private LocalDate date;
    private  String duree;
    private Agent user;

    public WorkingOn(Acte acte, LocalDate date, Agent user) {
        this.acte = acte;
        this.date = date;

        this.user = user;
    }

    public Acte getActe() {
        return acte;
    }

    public void setActe(Acte acte) {
        this.acte = acte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Agent getUser() {
        return user;
    }

    public void setUser(Agent user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkingOn)) return false;
        WorkingOn workingOn = (WorkingOn) o;
        return Objects.equals(acte, workingOn.acte)  ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(acte, date, duree, user);
    }
}
