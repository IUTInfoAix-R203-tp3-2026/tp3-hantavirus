package fr.univ_amu.iut.exercice2;

/// Kata 2 - Jeu de tennis.
///
/// Classe d'état qui gère le scoring d'un jeu de tennis entre deux joueurs.
/// Pratique idéale pour découvrir comment structurer une **machine à états** et
/// comment un test bien nommé devient une spécification lisible.
public class JeuDeTennis {

  private final String joueur1;
  private final String joueur2;
  private int points1;
  private int points2;

  public JeuDeTennis(String joueur1, String joueur2) {
    this.joueur1 = joueur1;
    this.joueur2 = joueur2;
  }

  /// Enregistre un point pour le joueur dont le nom est passé en argument.
  public void marquerPoint(String joueur) {
    // TODO kata 2 : incrémenter le compteur du joueur qui vient de marquer.
    if (joueur.equals(this.joueur1)) {
      points1++;
    } else if (joueur.equals(this.joueur2)) {

      points2++;
    }
  }

  /// Retourne la représentation textuelle du score courant.
  public String getScore() {
    // TODO kata 2 : construire la chaîne du score en gérant les cas :
    // - <4 points chacun et pas d'égalité à 40 : "X-Y" (ex "15-30")
    // - égalité >=3 : "Égalité"
    // - après Égalité, un joueur mène d'un point : "Avantage <nom>"
    // - un joueur a 4 points et 2 d'avance : "Jeu pour <nom>"
    if ((points1 >= 4 || points2 >= 4) && Math.abs(points1 - points2) >= 2) {
      return "Jeu pour " + (points1 > points2 ? joueur1 : joueur2);
    }

    // Cas 3 : Après Égalité, un joueur mène d'un point (Avantage)
    if (points1 >= 3 && points2 >= 3 && Math.abs(points1 - points2) == 1) {
      return "Avantage " + (points1 > points2 ? joueur1 : joueur2);
    }

    // Cas 2 : Égalité >= 3
    if (points1 >= 3 && points1 == points2) {
      return "Égalité";
    }

    // Cas 1 : < 4 points chacun et pas d'égalité à 40
    String[] libelles = {"0", "15", "30", "40"};
    return libelles[points1] + "-" + libelles[points2];
  }
}
