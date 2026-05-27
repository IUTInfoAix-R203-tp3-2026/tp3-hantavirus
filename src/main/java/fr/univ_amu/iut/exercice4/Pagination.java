package fr.univ_amu.iut.exercice4;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/// Kata 4 - Pagination.
///
/// Kata algorithmique avec beaucoup de cas limites. Idéal pour pratiquer la
/// **discipline TDD** : on active les tests dans l'ordre (du plus simple au plus
/// complexe) et on résiste à la tentation d'anticiper.
public class Pagination {

  private final int courant;
  private final int total;

  public Pagination(int courant, int total) {
    this.courant = courant;
    this.total = total;
  }

  /// Retourne la représentation textuelle de la barre de pagination.
  ///
  /// Format : pages séparées par des espaces, page courante entre parenthèses,
  /// `...` pour combler les trous quand il y a plus de 7 pages au total.
  ///
  public String afficher() {
    int[] pages = pagesAAfficher();
    return IntStream.range(0, pages.length)
        .mapToObj(
            i -> (i == 0 ? "" : separateurEntre(pages[i - 1], pages[i])) + formatPage(pages[i]))
        .collect(Collectors.joining());
  }

  private int[] pagesAAfficher() {
    if (total <= 7) return IntStream.rangeClosed(1, total).toArray();
    return IntStream.of(1, courant - 1, courant, courant + 1, total)
        .filter(p -> p >= 1 && p <= total)
        .sorted()
        .distinct()
        .toArray();
  }

  private String separateurEntre(int precedent, int actuel) {
    return (actuel - precedent > 1) ? " ... " : " ";
  }

  private String formatPage(int page) {
    return page == courant ? "(" + page + ")" : String.valueOf(page);
  }
}
