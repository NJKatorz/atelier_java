package compteur;

import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnable implements Runnable {

    private String nom;
    private int max;

    //TODO: ajouter un attribut de classe qui retient l'ordre d'arrivée.
    // private static int ordreArrivee = 0;
    private static AtomicInteger ordreArrivee = new AtomicInteger();

    /**
     * @Override public void run() {
     * //TODO: 1. Compter jusqu'à max
     * //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
     * //      2. Quand le compte est terminé, afficher que le compteur a finit de compter
     * //         et indiquer son ordre d'arrivée.
     * for (int i = 1; i <= this.max; i++) {
     * try {
     * Thread.sleep(10);
     * } catch (InterruptedException e) {
     * e.printStackTrace();
     * }
     * System.out.println(this.nom + " : " + i);
     * }
     * ordreArrivee++;
     * System.out.println(getNom() + " a fini de compter en position " + this.ordreArrivee);
     * <p>
     * }
     */

    @Override
    public void run() {
        //TODO: 1. Compter jusqu'à max
        //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        //      2. Quand le compte est terminé, afficher que le compteur a finit de compter
        //         et indiquer son ordre d'arrivée.
        for (int i = 1; i <= this.max; ++i) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.nom + " : " + i);
        }
        System.out.println(getNom() + " a fini de compter en position " + this.ordreArrivee.incrementAndGet());

    }

    public CompteurRunnable(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

}