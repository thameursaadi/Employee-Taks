package com.example.thamersaadi.miniprojet;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * Created by thamersaadi on 16/04/2018.
 */

public class Tache {
    public String Id;
    public String idEmploye;
    public String description;
    public boolean valide;
    public String date;

    public Tache(String id, String idEmploye, String description, boolean valide, String date) {
        Id = id;
        this.idEmploye = idEmploye;
        this.description = description;
        this.valide = valide;
        this.date = date;
    }

    public Tache() {
    }
}
