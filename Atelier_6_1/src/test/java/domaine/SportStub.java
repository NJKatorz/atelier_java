package domaine;

import java.util.Set;

public class SportStub implements Sport {

    private boolean contientMoniteur;

    public SportStub(boolean contientMoniteur) {
        this.contientMoniteur = contientMoniteur;
    }

    /**
     * renvoie l'intitulé du sport
     *
     * @return L'intitulé du sport
     */
    @Override
    public String getIntitule() {
        return null;
    }

    /**
     * ajoute un moniteur à l'ensemble des moniteurs compétents dans le sport
     *
     * @param moniteur Le moniteur à ajouter à ceux compétents dans le sport.
     * @return true si le moniteur a été ajouté
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    @Override
    public boolean ajouterMoniteur(Moniteur moniteur) {
        return false;
    }

    /**
     * supprime le moniteur de l'ensemble des moniteurs compétents dans ce sport
     *
     * @param moniteur le moniteur à supprimer de la liste des compétents dans ce sport.
     * @return true si le moniteur a bien été supprimé
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    @Override
    public boolean supprimerMoniteur(Moniteur moniteur) {
        return false;
    }

    /**
     * vérifie si le moniteur est compétents dans ce sport.
     *
     * @param moniteur le moniteur à tester.
     * @return true si le moniteur est compétent.
     * @throws IllegalArgumentException Exception lancée si l'un des paramètres
     *                                  n'est pas spécifié ou vide.
     */
    @Override
    public boolean contientMoniteur(Moniteur moniteur) {
        return this.contientMoniteur;
    }

    /**
     * renvoie les moniteurs compétents dans le sport
     *
     * @return Les moniteurs compétents dans le sport
     */
    @Override
    public Set<Moniteur> moniteurs() {
        return null;
    }
}
