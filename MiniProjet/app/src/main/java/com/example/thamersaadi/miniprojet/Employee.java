package com.example.thamersaadi.miniprojet;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by thamersaadi on 29/03/2018.
 */

public class Employee {
    public String Id;
    public int numeroTelephone;
    public String nom;
    public String sexe;
    public String prenom;
    public String email;

    public String dateDebut;
    public int cin;
    public  String motDePasse;
    public String departement;

    public Employee(String id, int numeroTelephone, String nom, String sexe, String prenom, String email, String dateDebut, int cin, String motDePasse, String departement) {
        Id = id;
        this.numeroTelephone = numeroTelephone;
        this.nom = nom;
        this.sexe = sexe;
        this.prenom = prenom;
        this.email = email;

        this.dateDebut = dateDebut;
        this.cin = cin;
        this.motDePasse = motDePasse;
        this.departement = departement;
    }

    public Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "Id='" + Id + '\'' +
                ", numeroTelephone=" + numeroTelephone +
                ", nom='" + nom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +

                ", dateDebut='" + dateDebut + '\'' +
                ", cin=" + cin +
                ", motDePasse='" + motDePasse + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }
}
