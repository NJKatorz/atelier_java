package domaine;

import util.Util;

import java.util.*;

public class Livre {
    Map<Plat.Type, SortedSet<Plat>> plats;

    public Livre() {
        plats = new HashMap<>();
    }


    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     *
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {
        SortedSet<Plat> platsExisants = getPlatsParType(plat.getType());
        if (platsExisants == null){
            platsExisants = new TreeSet<>(new ComparatorDifficulte());
           /** platsExisants = new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    return o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());
                }
            });**/
            plats.put(plat.getType(), platsExisants);
        }
        platsExisants.add(plat);
        return true;
    }

    /**
     * Supprime un plat du livre, s'il est dedans.
     * Si le plat supprimé est le dernier de ce type de plat, il faut supprimer
     * ce type de plat de la Map.
     *
     * @param plat le plat à supprimer
     * @return true si le plat a été supprimé, false sinon.
     */
    public boolean supprimerPlat(Plat plat) {
        SortedSet<Plat> platsExisants = getPlatsParType(plat.getType());
        if (platsExisants != null)
            return false;
        boolean deleted = platsExisants.remove(plat);
        if (deleted && platsExisants.isEmpty())
            plats.remove(plat.getType());
        return deleted;
    }

    /**
     * Renvoie un ensemble contenant tous les plats d'un certain type.
     * L'ensemble n'est pas modifable.
     * @param type le type de plats souhaité
     * @return l'ensemble des plats
     */
    public SortedSet<Plat> getPlatsParType(Plat.Type type) {
        // L'ensemble renvoyé ne doit pas être modifiable !
        return  plats.get(type);
    }

    /**
     * Renvoie true si le livre contient le plat passé en paramètre. False
     sinon.
     * Pour cette recherche, un plat est identique à un autre si son type, son
     niveau de
     * difficulté et son nom sont identiques.
     * @param plat le plat à rechercher
     * @return true si le livre contient le plat, false sinon.
     */
    public boolean contient(Plat plat) {
        // Ne pas utiliser 2 fois la méthode get() de la map, et ne pas déclarer de variable locale !

        return plats.get(plat.getType()).contains(plat);
    }
    /**
     * Renvoie un ensemble contenant tous les plats du livre. Ils ne doivent pas être triés.
     * @return l'ensemble de tous les plats du livre.
     */
    public Set<Plat> tousLesPlats() {
        // Ne pas utiliser la méthode keySet() ou entrySet() ici !
        Set<Plat> tousLesPlats = new HashSet<>();
        for (Set<Plat> p: plats.values()) {
            tousLesPlats.addAll(p);
        }

        return tousLesPlats;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Plat.Type, SortedSet<Plat>> entry : this.plats.entrySet()) {
            str.append(entry.getKey().toString() + "\n=====\n");
            for (Plat plat : entry.getValue()) {
                str.append(plat.getNom() + "\n");
            }
        }
        return str.toString();
    }

/**
    @Override
    public String toString() {
        String infos = "";
        for (Map.Entry<Plat.Type, SortedSet<Plat>> entry : plats.entrySet()) {
            infos += entry.getKey().toString() + "\n=====\n" + entry.getValue().toString();
        }
        return infos;
    }**/
}
