import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande>{
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> ligneDeCommandes;

    public Commande(Client client) {
        this.client = client;
        ligneDeCommandes = new ArrayList<LigneDeCommande>();
        if (!client.enregistrer(this))
           throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        this.numero = numeroSuivant;
        numeroSuivant++;
        this.date = LocalDateTime.now();

    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite){
        if (client.getCommandeEnCours() == null)
        // if (client.getCommandeEnCours() != this)
            return false;
        for (LigneDeCommande l: ligneDeCommandes) {
            // if (l.getPizza() == pizza){
            if (l.getPizza().equals(pizza)){
                l.setQuantite(l.getQuantite() + quantite);
                return true;
            }
        }
        LigneDeCommande newLigne = new LigneDeCommande(pizza, quantite);
        ligneDeCommandes.add(newLigne);
        return true;
    }

    public boolean ajouter(Pizza pizza){
        return this.ajouter(pizza, 1);
    }


    public double calculerMontantTotal(){
        double montantTotal = 0;
        for (LigneDeCommande l: ligneDeCommandes) {
            montantTotal += l.calculerPrixTotal();
        }
        return montantTotal;
    }

    public String detailler(){
        String infos = "";
        for (LigneDeCommande l: ligneDeCommandes) {
            infos += l.toString() + "\n";
        }

        return infos;
    }

    @Override
    public Iterator<LigneDeCommande> iterator() {
        return ligneDeCommandes.iterator();
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }
}
