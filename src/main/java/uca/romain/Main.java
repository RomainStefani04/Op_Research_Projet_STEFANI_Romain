package uca.romain;

import uca.romain.algos.DetectionCycleNegatif;
import uca.romain.algos.FordFulkerson;
import uca.romain.algos.MinCostFlowBellman;
import uca.romain.algos.MinCostFlowDijkstra;
import uca.romain.donnees.Arc;
import uca.romain.donnees.Reseau;
import uca.romain.donnees.ResultatFlotMax;
import uca.romain.donnees.Sommet;

import java.util.List;

/** Romain STEFANI **/

public class Main {

    public static void main(String[] args) {
        System.out.println("Exercice 1 : Ford fulkerson");
        fordFulkerson();
        System.out.println("Exercice 2.1 : Min cost flow avec Bellman");
        minCostFlowBellman();
        System.out.println("Exercice 2.2 : Min cost flow avec Dijkstra");
        minCostFlowDijkstra();
        System.out.println("Exercice 3 : Detection de cycle negatif");
        detectionCycleNegatif();
    }

    private static void fordFulkerson() {
        Reseau r = construireReseauSansCout();
        ResultatFlotMax res = FordFulkerson.trouverFlotMax(r);
        System.out.println(res);
    }

    private static void minCostFlowBellman() {
        Reseau r = construireReseauAvecCouts();
        ResultatFlotMax res = MinCostFlowBellman.trouverFlotMinCout(r);
        System.out.println(res);
    }

    private static void minCostFlowDijkstra() {
        Reseau r = construireReseauAvecCouts();
        ResultatFlotMax res = MinCostFlowDijkstra.trouverFlotMinCout(r);
        System.out.println(res);
    }

    private static void detectionCycleNegatif() {
        Reseau r = construireReseauAvecCouts();
        MinCostFlowBellman.trouverFlotMinCout(r);

        List<Arc> cycle = DetectionCycleNegatif.detecter(r);
        if (cycle == null) {
            System.out.println("Pas de cycle negatif");
        } else {
            System.out.println("Cycle negatif :");
            for (Arc a : cycle) {
                System.out.println("  " + a.getSource().getNom() + " -> " + a.getDestination().getNom() + " (cout " + a.getCout() + ")");
            }
        }
    }

    private static Reseau construireReseauSansCout() {
        Reseau r = new Reseau();
        Sommet s  = r.ajouterSommet();
        Sommet v1 = r.ajouterSommet();
        Sommet v2 = r.ajouterSommet();
        Sommet v3 = r.ajouterSommet();
        Sommet v4 = r.ajouterSommet();
        Sommet t  = r.ajouterSommet();
        r.setSource(s);
        r.setPuits(t);

        r.ajouterArc(s,  v1, 16);
        r.ajouterArc(s,  v2, 13);
        r.ajouterArc(v1, v3, 12);
        r.ajouterArc(v2, v1, 4);
        r.ajouterArc(v2, v4, 14);
        r.ajouterArc(v3, v2, 9);
        r.ajouterArc(v3, t,  20);
        r.ajouterArc(v4, v3, 7);
        r.ajouterArc(v4, t,  4);

        return r;
    }

    private static Reseau construireReseauAvecCouts() {
        Reseau r = new Reseau();
        Sommet s = r.ajouterSommet();
        Sommet a = r.ajouterSommet();
        Sommet b = r.ajouterSommet();
        Sommet t = r.ajouterSommet();
        r.setSource(s);
        r.setPuits(t);

        r.ajouterArc(s, a, 3, 1);
        r.ajouterArc(s, b, 2, 2);
        r.ajouterArc(a, b, 2, 1);
        r.ajouterArc(a, t, 2, 5);
        r.ajouterArc(b, t, 3, 1);

        return r;
    }
}