package fr.univ_amu.iut.bonus6;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Kata 6 - Ascenseur.
 *
 * <p>Progression TDD pensée pour du ping-pong. Chaque test ajoute un petit élément de comportement
 * ; commencez par l'état initial et avancez pas à pas vers la machine à états complète.
 *
 * <ul>
 *   <li>Tests 1 à 3 : état initial de l'ascenseur
 *   <li>Tests 4 à 6 : tick sans demande et avec demande (montée / descente)
 *   <li>Tests 7 à 10 : arrivée à l'étage, portes qui s'ouvrent, demande consommée, refermeture
 *   <li>Tests 11 à 14 : plusieurs demandes, demande sur place, dédoublonnage
 * </ul>
 */
class AscenseurTest {

  // ========= État initial =========

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_nouvellement_cree_est_a_l_etage_initial() {
    Ascenseur a = new Ascenseur(0);
    assertThat(a.getEtage()).isZero();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_nouvellement_cree_a_les_portes_fermees() {
    Ascenseur a = new Ascenseur(3);
    assertThat(a.portesOuvertes()).isFalse();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_nouvellement_cree_n_a_aucune_demande() {
    Ascenseur a = new Ascenseur(0);
    assertThat(a.getDemandes()).isEmpty();
  }

  // ========= Déplacement =========

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_au_tick_sans_demande_reste_sur_place() {
    Ascenseur a = new Ascenseur(2);
    a.tick();
    assertThat(a.getEtage()).isEqualTo(2);
    assertThat(a.portesOuvertes()).isFalse();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_avec_une_demande_plus_haute_monte_d_un_etage_par_tick() {
    Ascenseur a = new Ascenseur(0);
    a.demander(3);

    a.tick();
    assertThat(a.getEtage()).isEqualTo(1);

    a.tick();
    assertThat(a.getEtage()).isEqualTo(2);
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_avec_une_demande_plus_basse_descend_d_un_etage_par_tick() {
    Ascenseur a = new Ascenseur(5);
    a.demander(2);

    a.tick();
    assertThat(a.getEtage()).isEqualTo(4);

    a.tick();
    assertThat(a.getEtage()).isEqualTo(3);
  }

  // ========= Arrivée, portes, consommation =========

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_a_son_arrivee_a_l_etage_demande_ouvre_les_portes() {
    Ascenseur a = new Ascenseur(0);
    a.demander(1);

    a.tick(); // monte à 1
    a.tick(); // ouvre les portes

    assertThat(a.getEtage()).isEqualTo(1);
    assertThat(a.portesOuvertes()).isTrue();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_a_son_arrivee_a_l_etage_demande_retire_la_demande() {
    Ascenseur a = new Ascenseur(0);
    a.demander(1);

    a.tick(); // monte à 1
    a.tick(); // ouvre les portes, consomme la demande

    assertThat(a.getDemandes()).isEmpty();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_au_tick_suivant_l_ouverture_referme_les_portes() {
    Ascenseur a = new Ascenseur(0);
    a.demander(1);
    a.tick(); // étage 1
    a.tick(); // portes ouvertes

    a.tick(); // portes se referment

    assertThat(a.portesOuvertes()).isFalse();
    assertThat(a.getEtage()).isEqualTo(1);
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_avec_les_portes_ouvertes_reste_sur_place() {
    Ascenseur a = new Ascenseur(0);
    a.demander(1);
    a.tick(); // étage 1
    a.tick(); // portes ouvertes, demande consommée
    a.demander(5); // nouvelle demande pendant que les portes sont ouvertes

    a.tick(); // doit refermer les portes, sans se déplacer

    assertThat(a.getEtage()).isEqualTo(1);
    assertThat(a.portesOuvertes()).isFalse();
  }

  // ========= Demandes multiples =========

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_avec_plusieurs_demandes_sert_la_plus_proche_d_abord() {
    Ascenseur a = new Ascenseur(2);
    a.demander(5);
    a.demander(3);

    a.tick(); // monte à 3 (3 est à distance 1, 5 à distance 3)

    assertThat(a.getEtage()).isEqualTo(3);
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_avec_une_demande_a_l_etage_courant_ouvre_les_portes_sans_bouger() {
    Ascenseur a = new Ascenseur(4);
    a.demander(4);

    a.tick();

    assertThat(a.getEtage()).isEqualTo(4);
    assertThat(a.portesOuvertes()).isTrue();
    assertThat(a.getDemandes()).isEmpty();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_avec_la_meme_demande_envoyee_plusieurs_fois_la_compte_une_seule_fois() {
    Ascenseur a = new Ascenseur(0);
    a.demander(3);
    a.demander(3);
    a.demander(3);

    assertThat(a.getDemandes()).containsExactly(3);
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void l_ascenseur_apres_avoir_servi_toutes_les_demandes_reste_sur_place() {
    Ascenseur a = new Ascenseur(0);
    a.demander(2);
    a.tick(); // 1
    a.tick(); // 2
    a.tick(); // portes ouvertes
    a.tick(); // portes fermées

    a.tick();
    a.tick();

    assertThat(a.getEtage()).isEqualTo(2);
    assertThat(a.getDemandes()).isEmpty();
    assertThat(a.portesOuvertes()).isFalse();
  }
}
