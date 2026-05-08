package uca.romain.donnees;

import java.util.ArrayList;
import java.util.List;

public class Sommet {

    private final String nom;
    private final List<Arc> arcsSortants;

    public Sommet(String nom) {
        this.nom = nom;
        this.arcsSortants = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }
    public List<Arc> getArcsSortants() {
        return arcsSortants;
    }

    void ajouterArcSortant(Arc arc) {
        arcsSortants.add(arc);
    }
}
