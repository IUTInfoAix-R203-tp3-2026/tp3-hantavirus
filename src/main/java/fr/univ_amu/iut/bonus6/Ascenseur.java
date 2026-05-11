package fr.univ_amu.iut.bonus6;

import java.util.ArrayList;
import java.util.List;

/// Kata 6 (bonus) - Ascenseur.
///
/// Simulation d'un ascenseur unique à une échelle de temps discrète : chaque appel à [#tick()]
/// fait avancer le système d'une unité de temps. À chaque tick, l'ascenseur doit :
///
/// 1. refermer ses portes si elles étaient ouvertes ;
/// 2. sinon, s'il est à l'étage d'une demande, ouvrir ses portes et consommer la demande ;
/// 3. sinon, se déplacer d'un étage vers la demande la plus proche.
///
/// C'est une machine à états plus riche que le tennis du kata 2. Écrivez vos tests un par un et
/// résistez à la tentation de tout implémenter d'un coup.
public class Ascenseur {

  private int etage;
  private final List<Integer> demandes = new ArrayList<>();
  private boolean portesOuvertes;

  public Ascenseur(int etageInitial) {
    this.etage = etageInitial;
  }

  /// Étage courant de l'ascenseur.
  public int getEtage() {
    return etage;
  }

  /// `true` si les portes sont actuellement ouvertes.
  public boolean portesOuvertes() {
    return portesOuvertes;
  }

  /// Vue non modifiable des demandes en attente (dans leur ordre d'arrivée).
  public List<Integer> getDemandes() {
    return List.copyOf(demandes);
  }

  /// Enregistre une nouvelle demande pour l'étage donné (sans duplication).
  public void demander(int etageDemande) {
    // TODO kata 6 : ajouter l'étage à la liste des demandes, seulement s'il n'y est pas déjà.
  }

  /// Fait avancer le système d'une unité de temps.
  ///
  /// Priorités (dans l'ordre) :
  ///
  /// 1. si les portes sont ouvertes, on les referme (et on s'arrête là pour ce tick) ;
  /// 2. sinon, s'il reste une demande à l'étage courant, on ouvre les portes et on consomme la
  ///    demande ;
  /// 3. sinon, s'il reste une demande ailleurs, on avance d'un étage vers la demande la plus
  ///    proche ;
  /// 4. sinon, on ne fait rien.
  public void tick() {
    // TODO kata 6 : implémenter la logique décrite dans la Javadoc, test après test.
  }
}
