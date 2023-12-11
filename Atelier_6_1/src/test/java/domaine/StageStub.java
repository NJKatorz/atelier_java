package domaine;

import java.util.Set;

public class StageStub implements Stage {

    private int getNumeroDeSemaine;
    private Sport getSport;
    private Moniteur getMoniteur;

    public StageStub(int getNumeroDeSemaine, Sport getSport, Moniteur getMoniteur) {
        this.getNumeroDeSemaine = getNumeroDeSemaine;
        this.getSport = getSport;
        this.getMoniteur = getMoniteur;
    }

    /**
     * Renvoie l'intitulé du stage
     *
     * @return L'intitulé du stage
     */
    @Override
    public String getIntitule() {
        return null;
    }

    /**
     * Renvoie le lieu du stage
     *
     * @return Le lieu du stage
     */
    @Override
    public String getLieu() {
        return null;
    }

    /**
     * Renvoie le numéro de la semaine durant laquelle le stage a lieu
     *
     * @return le numéro de la semaine durant laquelle le stage a lieu
     */
    @Override
    public int getNumeroDeSemaine() {
        return getNumeroDeSemaine;
    }

    /**
     * Renvoie le sport concerné par le stage
     *
     * @return Le sport concerné par le stage
     */
    @Override
    public Sport getSport() {
        return getSport;
    }

    /**
     * Enregistre le moniteur qui assure le stage s'il n'y a pas encore de moniteur
     * spécifié Il faut que le moniteur soit libre durant cette semaine-là (pas
     * d'autre stage).Il faut aussi que le moiteur soit compétent dans ce sport.
     * Attention, il faut s'assurer que le moniteur ajoute le stage.
     *
     * @param moniteur le moniteur qui va assurer le stage.
     * @return true si le moniteur a bien été enregistré
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    @Override
    public boolean enregistrerMoniteur(Moniteur moniteur) {
        return false;
    }

    /**
     * Supprime le moniteur qui assure le stage.
     * <p>
     * Attention il faut s'assurer que le moniteur supprime également le stage
     * de sa liste des stages.
     *
     * @return true si le moniteur a pu être supprimé
     */
    @Override
    public boolean supprimerMoniteur() {
        return false;
    }

    /**
     * Renvoie le moniteur qui assure le stage
     *
     * @return moniteur qui assure le stage
     */
    @Override
    public Moniteur getMoniteur() {
        return getMoniteur;
    }

    /**
     * Ajoute l'inscription d'un enfant au stage. L'enfant est ajouté seulement s'il
     * n'était pas déjà inscrit au stage.
     *
     * @param enfant l'enfant qui veut s'inscrire au stage.
     * @return true si l'enfant a pu être inscrit au stage.
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    @Override
    public boolean ajouterEnfant(Enfant enfant) {
        return false;
    }

    /**
     * Supprime l'inscription d'un enfant au stage. L'inscription de l'enfant est
     * supprimée seulement s'il était inscrit au stage.
     *
     * @param enfant l'enfant qui veut se desinscrire au stage.
     * @return true si l'enfant a pu être desinscrit au stage.
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    @Override
    public boolean supprimerEnfant(Enfant enfant) {
        return false;
    }

    /**
     * Vérifie si un enfant est inscrit au stage.
     *
     * @param enfant l'enfant qu'il faut tester.
     * @return true si l'enfant est inscrit au stage.
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    @Override
    public boolean contientEnfant(Enfant enfant) {
        return false;
    }

    /**
     * Renvoie le nombe d'enfants inscrits au stage.
     *
     * @return le nombre d'enfants inscrits au stage.
     */
    @Override
    public int nombreDEnfants() {
        return 0;
    }

    /**
     * Renvoie l'ensemble des enfants inscrits au stage.
     *
     * @return le Set des enfants inscrits au stage.
     */
    @Override
    public Set<Enfant> enfants() {
        return null;
    }
}
