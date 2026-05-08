package uca.romain.algos;

import uca.romain.donnees.ResultatFlotMax;
import uca.romain.Utils;
import uca.romain.donnees.Arc;
import uca.romain.donnees.Reseau;
import uca.romain.donnees.Sommet;

import java.util.*;

public class FordFulkerson {

    public static ResultatFlotMax trouverFlotMax(Reseau reseau) {
        int flotTotal = 0;
        List<List<Arc>> chemins = new ArrayList<>();
        Map<Sommet, Arc> arcsPrecedents;

        while ((arcsPrecedents = bfs(reseau)) != null) {
            int delta = Utils.trouverMinCapacite(reseau, arcsPrecedents);
            List<Arc> chemin = Utils.extraireChemin(reseau, arcsPrecedents);
            Utils.ajouterFlotSurChemin(chemin, delta);

            chemins.add(chemin);
            flotTotal += delta;
        }

        Set<Sommet> sommetsAtteints = Utils.atteindreDepuisSource(reseau);
        List<Arc> coupe = Utils.calculerCoupe(reseau, sommetsAtteints);
        return new ResultatFlotMax(flotTotal, 0, coupe, chemins);
    }

    private static Map<Sommet, Arc> bfs(Reseau reseau) {
        Map<Sommet, Arc> arcsPrecedents = new HashMap<>();
        Set<Sommet> visites = new HashSet<>();
        Queue<Sommet> file = new ArrayDeque<>();

        file.add(reseau.getSource());
        visites.add(reseau.getSource());

        while (!file.isEmpty()) {
            Sommet u = file.poll();
            if (u == reseau.getPuits()) {
                return arcsPrecedents;
            }
            for (Arc arc : u.getArcsSortants()) {
                Sommet v = arc.getDestination();
                if (!visites.contains(v) && arc.capaciteResiduelle() > 0) {
                    visites.add(v);
                    arcsPrecedents.put(v, arc);
                    file.add(v);
                }
            }
        }
        return null;
    }
}