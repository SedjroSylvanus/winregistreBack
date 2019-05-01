package com.dgi.dsi.winregistre.entitiesIfri;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Client c1 = new Client(10, "Ostian", 9876);
        Client c2 = new Client(13, "toto", 1234);
        Client c3 = new Client(14, "tata", 8976);
        Date d = new Date();
        System.out.println(c1);
        Facture f1 = new Facture(01, d, c1);
        Facture f2 = new Facture(02, d, c2);
        Facture f3 = new Facture(03, d, c3);
        Facture f4 = new Facture(04, d, c1);
        Facture f5 = new Facture(05, d, c2);
        c1.getFactures().add(f1);
        c1.getFactures().add(f4);

        System.out.println(c1.getFactures());
        Produit p1 = new Produit("B", "bic", 200);
        Produit p2 = new Produit("C", "CAHIER", 300);
        Produit P3 = new Produit("E", "enveloppe", 500);
        Produit P4 = new Produit("youu", "yaourt", 1000);
        Produit P5 = new Produit("para", "paracetamol", 300);
        Produit P6 = new Produit("qu", "quinine", 450);

        Facturer_PK vi1 = new Facturer_PK();
        vi1.setCode("B");
        vi1.setNumfact(01);
        Facturer_PK vi2 = new Facturer_PK(03, "E");
        Facturer_PK vi3 = new Facturer_PK(03, "qu");

        Facturer v1 = new Facturer(vi1, 3);
        Facturer v2 = new Facturer(vi2, 8);
        Facturer v3 = new Facturer(vi3, 10);
        System.out.println(v1.getPk().getNumfact());


    }
}
