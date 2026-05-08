# Projet Operations Research

Implémentation en Java d'algorithmes de flots dans les graphes :
- Algorithme de Ford-Fulkerson (flot maximum et coupe minimale)
- Algorithme de min cost flow par chemins augmentants successifs (variantes Bellman-Ford et Dijkstra avec renormalisation)
- Algorithme de détection de cycles négatifs

## Auteur

STEFANI Romain

## Prérequis

- Java 22 (testé avec OpenJDK 22.0.2)
- Apache Maven 3.9 ou supérieur

## Compilation et exécution

À la racine du projet :

```bash
mvn compile
mvn exec:java
```

Ou directement depuis IntelliJ : ouvrir le projet et lancer la classe `Main`.

## Structure du projet

```
src/main/java/uca/romain/
├── Main.java
├── Utils.java
├── algos/
│   ├── FordFulkerson.java
│   ├── MinCostFlowBellman.java
│   ├── MinCostFlowDijkstra.java
│   └── DetectionCycleNegatif.java
└── donnees/
├── Sommet.java
├── Arc.java
├── Reseau.java
└── ResultatFlotMax.java
```

Le package `donnees` contient les structures représentant le réseau de flot. 
Le package `algos` contient les implémentations des quatre algorithmes du sujet. 
La classe `Utils` regroupe les opérations communes aux algorithmes (extraction de chemin, ajout de flot le long d'un chemin, calcul de la coupe).

## Documentation

Le fichier `rapport.pdf` détaille le contenu du projet.