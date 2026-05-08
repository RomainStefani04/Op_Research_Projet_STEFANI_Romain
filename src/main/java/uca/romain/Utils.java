package uca.romain;

import uca.romain.donnees.Arc;
import uca.romain.donnees.Reseau;
import uca.romain.donnees.Sommet;

import java.util.*;

public class Utils {

    public static int trouverMinCapacite(Reseau reseau, Map<Sommet, Arc> arcsPrecedents) {
        int delta = Integer.MAX_VALUE;
        Sommet actuel = reseau.getPuits();
        while (actuel != reseau.getSource()) {
            Arc arcPrecedent = arcsPrecedents.get(actuel);
            delta = Math.min(delta, arcPrecedent.capaciteResiduelle());
            actuel = arcPrecedent.getSource();
        }
        return delta;
    }

    public static int coutDuChemin(Reseau reseau, Map<Sommet, Arc> arcsPrecedents) {
        int cout = 0;
        Sommet actuel = reseau.getPuits();
        while (actuel != reseau.getSource()) {
            Arc arcPrecedent = arcsPrecedents.get(actuel);
            cout += arcPrecedent.getCout();
            actuel = arcPrecedent.getSource();
        }
        return cout;
    }

    public static List<Arc> extraireChemin(Reseau reseau, Map<Sommet, Arc> arcsPrecedents) {
        List<Arc> chemin = new ArrayList<>();
        Sommet actuel = reseau.getPuits();
        while (actuel != reseau.getSource()) {
            Arc arcPrecedent = arcsPrecedents.get(actuel);
            chemin.add(arcPrecedent);
            actuel = arcPrecedent.getSource();
        }
        Collections.reverse(chemin);
        return chemin;
    }

    public static void ajouterFlotSurChemin(List<Arc> chemin, int delta) {
        for (Arc arc : chemin) {
            arc.ajouterFlot(delta);
        }
    }

    public static Set<Sommet> atteindreDepuisSource(Reseau reseau) {
        Set<Sommet> visites = new HashSet<>();
        Queue<Sommet> file = new ArrayDeque<>();
        file.add(reseau.getSource());
        visites.add(reseau.getSource());

        while (!file.isEmpty()) {
            Sommet u = file.poll();
            for (Arc arc : u.getArcsSortants()) {
                Sommet v = arc.getDestination();
                if (!visites.contains(v) && arc.capaciteResiduelle() > 0) {
                    visites.add(v);
                    file.add(v);
                }
            }
        }
        return visites;
    }

    public static List<Arc> calculerCoupe(Reseau reseau, Set<Sommet> sommetsAtteints) {
        List<Arc> coupe = new ArrayList<>();
        for (Arc arc : reseau.getArcsOriginaux()) {
            if (sommetsAtteints.contains(arc.getSource()) && !sommetsAtteints.contains(arc.getDestination())) {
                coupe.add(arc);
            }
        }
        return coupe;
    }
}