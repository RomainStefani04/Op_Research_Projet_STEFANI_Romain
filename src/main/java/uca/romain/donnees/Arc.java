package uca.romain.donnees;

public class Arc {

    private final Sommet source;
    private final Sommet destination;
    private final int capacite;
    private final int cout;
    private int flot;
    private Arc inverse;
    private final boolean estInverse;

    public Arc(Sommet source, Sommet destination, int capacite, int cout, boolean estInverse) {
        this.source = source;
        this.destination = destination;
        this.capacite = capacite;
        this.cout = cout;
        this.flot = 0;
        this.estInverse = estInverse;
    }

    public Sommet getSource() {
        return source;
    }
    public Sommet getDestination() {
        return destination;
    }
    public int getCapacite() {
        return capacite;
    }
    public int getCout() {
        return cout;
    }

    void setInverse(Arc inverse) {
        this.inverse = inverse;
    }

    public int capaciteResiduelle() {
        return capacite - flot;
    }

    public void ajouterFlot(int valeur) {
        this.flot += valeur;
        this.inverse.flot -= valeur;
    }

    public boolean estInverse() {
        return estInverse;
    }
}