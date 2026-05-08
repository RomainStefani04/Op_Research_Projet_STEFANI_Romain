package uca.romain.donnees;

import java.util.ArrayList;
import java.util.List;

public class Reseau {

    private final List<Sommet> sommets;
    private final List<Arc> arcs;     // tous les arcs : originaux + inverses
    private Sommet source;
    private Sommet puits;

    public Reseau() {
        this.sommets = new ArrayList<>();
        this.arcs = new ArrayList<>();
    }

    public Sommet ajouterSommet() {
        Sommet s = new Sommet("Sommet " + sommets.size());
        sommets.add(s);
        return s;
    }

    public void ajouterArc(Sommet u, Sommet v, int capacite, int cout) {
        Arc original = new Arc(u, v, capacite, cout, false);
        Arc inverse  = new Arc(v, u, 0, -cout, true);
        original.setInverse(inverse);
        inverse.setInverse(original);

        u.ajouterArcSortant(original);
        v.ajouterArcSortant(inverse);
        arcs.add(original);
        arcs.add(inverse);

    }

    public void ajouterArc(Sommet u, Sommet v, int capacite) {
        ajouterArc(u, v, capacite, 0);
    }

    public List<Sommet> getSommets() {
        return sommets;
    }
    public List<Arc> getArcs() {
        return arcs;
    }
    public Sommet getSource() {
        return source; }
    public Sommet getPuits() {
        return puits;
    }

    public void setSource(Sommet s) {
        this.source = s;
    }
    public void setPuits(Sommet t) {
        this.puits  = t;
    }

    public List<Arc> getArcsOriginaux() {
        return arcs.stream()
                .filter(a -> !a.estInverse())
                .toList();
    }
}
