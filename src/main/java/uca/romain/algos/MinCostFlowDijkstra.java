package uca.romain.algos;

import uca.romain.donnees.ResultatFlotMax;
import uca.romain.Utils;
import uca.romain.donnees.Arc;
import uca.romain.donnees.Reseau;
import uca.romain.donnees.Sommet;

import java.util.*;

public class MinCostFlowDijkstra {

    public static ResultatFlotMax trouverFlotMinCout(Reseau reseau) {
        int flotTotal = 0;
        int coutTotal = 0;
        List<List<Arc>> chemins = new ArrayList<>();

        Map<Sommet, Integer> potentiels = new HashMap<>();
        for (Sommet s : reseau.getSommets()) {
            potentiels.put(s, 0);
        }

        while (true) {
            Map<Sommet, Integer> distances = new HashMap<>();
            Map<Sommet, Arc> arcsPrecedents = dijkstra(reseau, potentiels, distances);
            if (arcsPrecedents == null) {
                break;
            }

            for (Sommet s : reseau.getSommets()) {
                if (distances.get(s) != Integer.MAX_VALUE) {
                    potentiels.put(s, potentiels.get(s) + distances.get(s));
                }
            }

            int delta = Utils.trouverMinCapacite(reseau, arcsPrecedents);
            int coutUnitaire = Utils.coutDuChemin(reseau, arcsPrecedents);
            List<Arc> chemin = Utils.extraireChemin(reseau, arcsPrecedents);
            Utils.ajouterFlotSurChemin(chemin, delta);

            chemins.add(chemin);
            flotTotal += delta;
            coutTotal += delta * coutUnitaire;
        }

        Set<Sommet> sommetsAtteints = Utils.atteindreDepuisSource(reseau);
        List<Arc> coupe = Utils.calculerCoupe(reseau, sommetsAtteints);
        return new ResultatFlotMax(flotTotal, coutTotal, coupe, chemins);
    }

    private static Map<Sommet, Arc> dijkstra(Reseau reseau, Map<Sommet, Integer> potentiels, Map<Sommet, Integer> distances) {
        Map<Sommet, Arc> arcsPrecedents = new HashMap<>();
        Set<Sommet> traites = new HashSet<>();

        for (Sommet s : reseau.getSommets()) {
            distances.put(s, Integer.MAX_VALUE);
        }
        distances.put(reseau.getSource(), 0);

        PriorityQueue<Sommet> file = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        file.add(reseau.getSource());

        while (!file.isEmpty()) {
            Sommet u = file.poll();
            if (traites.contains(u)) {
                continue;
            }
            traites.add(u);

            for (Arc arc : u.getArcsSortants()) {
                if (arc.capaciteResiduelle() <= 0) {
                    continue;
                }
                Sommet v = arc.getDestination();
                if (traites.contains(v)) {
                    continue;
                }

                int coutReduit = arc.getCout() + potentiels.get(u) - potentiels.get(v);
                int nouvelleDist = distances.get(u) + coutReduit;

                if (nouvelleDist < distances.get(v)) {
                    distances.put(v, nouvelleDist);
                    arcsPrecedents.put(v, arc);
                    file.add(v);
                }
            }
        }

        if (distances.get(reseau.getPuits()) == Integer.MAX_VALUE) {
            return null;
        }
        return arcsPrecedents;
    }
}