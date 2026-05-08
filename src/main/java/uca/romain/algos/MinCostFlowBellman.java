package uca.romain.algos;

import uca.romain.donnees.ResultatFlotMax;
import uca.romain.Utils;
import uca.romain.donnees.Arc;
import uca.romain.donnees.Reseau;
import uca.romain.donnees.Sommet;

import java.util.*;

public class MinCostFlowBellman {

    public static ResultatFlotMax trouverFlotMinCout(Reseau reseau) {
        int flotTotal = 0;
        int coutTotal = 0;
        List<List<Arc>> chemins = new ArrayList<>();
        Map<Sommet, Arc> arcsPrecedents;

        while ((arcsPrecedents = bellmanFord(reseau)) != null) {
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

    private static Map<Sommet, Arc> bellmanFord(Reseau reseau) {
        Map<Sommet, Integer> distances = new HashMap<>();
        Map<Sommet, Arc> arcsPrecedents = new HashMap<>();

        for (Sommet s : reseau.getSommets()) {
            distances.put(s, Integer.MAX_VALUE);
        }
        distances.put(reseau.getSource(), 0);

        int nbSommets = reseau.getSommets().size();
        for (int i = 0; i < nbSommets - 1; i++) {
            for (Arc arc : reseau.getArcs()) {
                if (arc.capaciteResiduelle() <= 0) {
                    continue;
                }
                int distU = distances.get(arc.getSource());
                if (distU == Integer.MAX_VALUE) {
                    continue;
                }
                int nouvelleDistV = distU + arc.getCout();
                if (nouvelleDistV < distances.get(arc.getDestination())) {
                    distances.put(arc.getDestination(), nouvelleDistV);
                    arcsPrecedents.put(arc.getDestination(), arc);
                }
            }
        }

        if (distances.get(reseau.getPuits()) == Integer.MAX_VALUE) {
            return null;
        }
        return arcsPrecedents;
    }
}