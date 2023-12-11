import java.util.ArrayList;

public class PizzaComposee extends Pizza{
    private final static int REMISE = 15;
    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);
    }

    @Override
    public boolean ajouter(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    @Override
    public boolean supprimer(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    @Override
    public double calculerPrix() {
        double reduction = 100-REMISE;
        return Math.ceil(super.calculerPrix()*(reduction/100));
    }
}
