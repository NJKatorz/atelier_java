import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pizza implements Iterable<Ingredient>{
    private final static  double PRIX_BASE = 5;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.ingredients = new ArrayList<Ingredient>();
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);
       // ArrayList<Ingredient> newArray = new ArrayList<Ingredient>();
        for (Ingredient i: ingredients) {
            if (this.ingredients.contains(i))
                throw new IllegalArgumentException();
            else
                this.ingredients.add(i);
        }
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient){
        if (ingredients.contains(ingredient))
            return false;
        return ingredients.add(ingredient);
    }

    public boolean supprimer(Ingredient ingredient){
        if (!ingredients.contains(ingredient))
            return false;
        return ingredients.remove(ingredient);
    }

    public double calculerPrix(){
        double prixTotal = PRIX_BASE;
        for (Ingredient i : ingredients) {
            prixTotal += i.getPrix();
        }
        return prixTotal;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
