package fr.univ_amu.iut;

import fr.univ_amu.iut.bonus6.Ascenseur;
import fr.univ_amu.iut.exercice1.AnneesBissextiles;
import fr.univ_amu.iut.exercice2.JeuDeTennis;
import fr.univ_amu.iut.exercice3.Employe;
import fr.univ_amu.iut.exercice3.GestionnaireEmployes;
import fr.univ_amu.iut.exercice4.Pagination;
import fr.univ_amu.iut.exercice5.Yahtzee;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Lanceur console du TP3 - Kata et pair programming.
 *
 * <p>Affiche la liste des kata disponibles et permet d'en lancer un en lisant un numéro sur
 * l'entrée standard.
 */
public class App {

  public static void main(String[] args) {
    Map<String, Runnable> exercices = construireMenu();
    afficherMenu(exercices);

    if (exercices.isEmpty()) {
      return;
    }

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Votre choix : ");
      if (!scanner.hasNextInt()) {
        System.out.println("Entrée invalide.");
        return;
      }
      lancerExercice(exercices, scanner.nextInt());
    }
  }

  static Map<String, Runnable> construireMenu() {
    Map<String, Runnable> exercices = new LinkedHashMap<>();
    exercices.put("Kata 1 - Années bissextiles", App::demoBissextiles);
    exercices.put("Kata 2 - Jeu de tennis", App::demoTennis);
    exercices.put("Kata 3 - Gestionnaire d'employés", App::demoEmployes);
    exercices.put("Kata 4 - Pagination", App::demoPagination);
    exercices.put("Kata 5 - Yahtzee", App::demoYahtzee);
    exercices.put("Kata 6 - Ascenseur (bonus)", App::demoAscenseur);
    return exercices;
  }

  static void afficherMenu(Map<String, Runnable> exercices) {
    System.out.println("=== TP3 - Kata et pair programming - IUT Aix-Marseille ===");
    System.out.println();
    if (exercices.isEmpty()) {
      System.out.println("Aucun exercice disponible.");
      return;
    }
    int i = 1;
    for (String titre : exercices.keySet()) {
      System.out.printf("  %d. %s%n", i++, titre);
    }
  }

  static void lancerExercice(Map<String, Runnable> exercices, int choix) {
    if (choix < 1 || choix > exercices.size()) {
      System.out.println("Choix hors des bornes.");
      return;
    }
    String titre = exercices.keySet().toArray(new String[0])[choix - 1];
    System.out.println();
    System.out.println("--- Lancement : " + titre + " ---");
    exercices.get(titre).run();
  }

  // =========================== Démos ===========================

  private static void demoBissextiles() {
    for (int annee : new int[] {2020, 2021, 1900, 2000}) {
      System.out.printf(
          "%d : %s%n", annee, AnneesBissextiles.estBissextile(annee) ? "bissextile" : "ordinaire");
    }
  }

  private static void demoTennis() {
    JeuDeTennis jeu = new JeuDeTennis("Alice", "Bob");
    String[] commandes = {"Alice", "Bob", "Alice", "Alice", "Bob", "Alice"};
    for (String joueur : commandes) {
      jeu.marquerPoint(joueur);
      System.out.printf("Point pour %-6s -> %s%n", joueur, jeu.getScore());
    }
  }

  private static void demoEmployes() {
    GestionnaireEmployes g =
        new GestionnaireEmployes(
            List.of(
                new Employe("Alice", 25),
                new Employe("Bob", 15),
                new Employe("Charlie", 40),
                new Employe("Dana", 17)));
    System.out.println("Majeurs : " + g.getMajeurs());
    System.out.printf("Âge moyen des majeurs : %.1f%n", g.ageMoyenDesMajeurs());
    System.out.println("Par ordre alphabétique : " + g.parOrdreAlphabetique());
    System.out.println("Par âge croissant : " + g.parAgeCroissant());
  }

  private static void demoPagination() {
    int total = 15;
    for (int courant : new int[] {1, 3, 7, 13, 15}) {
      System.out.printf(
          "courant=%2d / total=%d : %s%n",
          courant, total, new Pagination(courant, total).afficher());
    }
  }

  private static void demoAscenseur() {
    Ascenseur a = new Ascenseur(0);
    a.demander(3);
    a.demander(1);
    System.out.println("État initial : étage " + a.getEtage() + ", demandes " + a.getDemandes());
    for (int i = 1; i <= 8; i++) {
      a.tick();
      System.out.printf(
          "Tick %d : étage %d, portes %s, demandes %s%n",
          i, a.getEtage(), a.portesOuvertes() ? "ouvertes" : "fermées", a.getDemandes());
    }
  }

  private static void demoYahtzee() {
    int[] des = {2, 2, 2, 6, 6};
    System.out.printf("Dés : %s%n", java.util.Arrays.toString(des));
    System.out.printf(
        "  chance         = %d%n", Yahtzee.chance(des[0], des[1], des[2], des[3], des[4]));
    System.out.printf(
        "  paire          = %d%n", Yahtzee.paire(des[0], des[1], des[2], des[3], des[4]));
    System.out.printf(
        "  brelan         = %d%n", Yahtzee.brelan(des[0], des[1], des[2], des[3], des[4]));
    System.out.printf(
        "  full           = %d%n", Yahtzee.full(des[0], des[1], des[2], des[3], des[4]));
    System.out.printf(
        "  yahtzee        = %d%n", Yahtzee.yahtzee(des[0], des[1], des[2], des[3], des[4]));
  }
}
