package uca.romain.donnees;

import java.util.List;

public class ResultatFlotMax {

    private final int valeurFlot;
    private final int coutTotal;
    private final List<Arc> coupeMinimale;
    private final List<List<Arc>> chemins;

    public ResultatFlotMax(int valeurFlot, int coutTotal, List<Arc> coupeMinimale, List<List<Arc>> chemins) {
        this.valeurFlot = valeurFlot;
        this.coutTotal = coutTotal;
        this.coupeMinimale = coupeMinimale;
        this.chemins = chemins;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flot : ").append(valeurFlot).append("\n");
        sb.append("Cout : ").append(coutTotal).append("\n");

        sb.append("Chemins :\n");
        for (List<Arc> chemin : chemins) {
            sb.append("  ").append(chemin.getFirst().getSource().getNom());
            for (Arc a : chemin) {
                sb.append(" -> ").append(a.getDestination().getNom());
            }
            sb.append("\n");
        }

        sb.append("Coupe minimale :\n");
        for (Arc a : coupeMinimale) {
            sb.append("  ").append(a.getSource().getNom())
                    .append(" -> ").append(a.getDestination().getNom())
                    .append(" (capacite ").append(a.getCapacite()).append(")\n");
        }
        return sb.toString();
    }
}