package uca.romain.algos;

import uca.romain.donnees.Arc;
import uca.romain.donnees.Reseau;
import uca.romain.donnees.Sommet;

import java.util.*;

public class DetectionCycleNegatif {

    public static List<Arc> detecter(Reseau reseau) {
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
                int nouvelleDist = distU + arc.getCout();
                if (nouvelleDist < distances.get(arc.getDestination())) {
                    distances.put(arc.getDestination(), nouvelleDist);
                    arcsPrecedents.put(arc.getDestination(), arc);
                }
            }
        }

        Sommet sommetDuCycle = null;
        for (Arc arc : reseau.getArcs()) {
            if (arc.capaciteResiduelle() <= 0) {
                continue;
            }
            int distU = distances.get(arc.getSource());
            if (distU == Integer.MAX_VALUE) {
                continue;
            }
            int nouvelleDist = distU + arc.getCout();
            if (nouvelleDist < distances.get(arc.getDestination())) {
                sommetDuCycle = arc.getDestination();
                break;
            }
        }

        if (sommetDuCycle == null) {
            return null;
        }

        for (int i = 0; i < nbSommets; i++) {
            sommetDuCycle = arcsPrecedents.get(sommetDuCycle).getSource();
        }

        List<Arc> cycle = new ArrayList<>();
        Sommet actuel = sommetDuCycle;
        do {
            Arc arc = arcsPrecedents.get(actuel);
            cycle.add(arc);
            actuel = arc.getSource();
        } while (actuel != sommetDuCycle);

        Collections.reverse(cycle);
        return cycle;
    }
}