package com.dgi.dsi.winregistre.api;


import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.entites.Acte;
import com.dgi.dsi.winregistre.entites.traitementCours.WorkingOn;

import java.util.ArrayList;
import java.util.List;

class WorkingOnSingleton {
    // static variable single_instance of type Singleton
    private static WorkingOnSingleton single_instance = null;
    private List<WorkingOn> workingOns = new ArrayList<>();

    // private constructor restricted to this class itself
    private WorkingOnSingleton() {

    }

    // static method to create instance of Singleton class
    public static WorkingOnSingleton getInstance() {
        if (single_instance == null)
            single_instance = new WorkingOnSingleton();
        return single_instance;
    }


    public List<WorkingOn> getWorkingOns() {
        return workingOns;
    }

    public void setWorkingOns(List<WorkingOn> workingOns) {
        this.workingOns = workingOns;
    }

    public boolean addAWorkingOn(WorkingOn workingOn) {
        this.workingOns.add(workingOn);

        return true;
    }


    public WorkingOn getWorkingOnFromAUser(Agent user) {
        final List<WorkingOn>   workingOnSearch = new ArrayList<>();
//        final WorkingOn[] workingOnSearch = new WorkingOn[1];
        workingOns.forEach(workingOn -> {
            if (workingOn.getUser().equals(user)) {
                workingOnSearch.add(workingOn);
//                workingOnSearch[0] = workingOn;
            }
        });


        return workingOnSearch.get(0);

    }

    public List<Acte> getAllContribuableOnTreatemnt() {

        List<Acte> actes = new ArrayList<>();
        workingOns.forEach(workingOn -> {
            actes.add(workingOn.getActe());
        });
        return actes;
    }


    public boolean removeAWorkingOn(Agent user) {
        WorkingOn workingOn = getWorkingOnFromAUser(user);
        workingOns.remove(workingOn);

        return true;
    }

    public boolean deleteAWorkingOn(WorkingOn workingOn) {


        return workingOns.remove(workingOn);
    }

    public boolean deleteAllSelectedWorkingOn(List<WorkingOn> workingOns) {


        return workingOns.removeAll(workingOns);
    }


    public boolean deleteAll( ) {
        workingOns = null;


        return true;
    }


}