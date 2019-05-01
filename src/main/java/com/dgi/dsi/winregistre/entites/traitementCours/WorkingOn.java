package com.dgi.dsi.winregistre.entites.traitementCours;


import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.Agent;

import java.util.Date;
import java.util.Objects;

public class WorkingOn {

    private Acte acte;
    private Date date;
    private  String duree;
    private Agent user;

    public WorkingOn(Acte acte, Date date, Agent user) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
