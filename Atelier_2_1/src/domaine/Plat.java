package domaine;

import java.time.Duration;
import java.util.*;

import util.Util;

import static java.util.Collections.unmodifiableList;

public class Plat {

    public enum Difficulte{
        X, XX, XXX, XXXX, XXXXX;

        @Override
        public String toString() {
            return super.toString().replace("X", "*");
        }
    }

    public enum Cout{
        $, $$, $$$, $$$$, $$$$$;

        @Override
        public String toString() {
            return super.toString().replace("$", "€");
        }
    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    Duration dureeEnMinutes;
    private List<Instruction> recette = new ArrayList<>();
    private Set<IngredientQuantifie> ingredients = new HashSet<>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        Util.checkString(nom);
        Util.checkStrictlyPositive(nbPersonnes);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void insererInstruction(int position, Instruction instruction){
        if (position<=0 || position> recette.size())
            throw new IllegalArgumentException("Position non identifiable");
        recette.add(position-1, instruction);
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    public void ajouterInstruction(Instruction instruction){
        recette.add(instruction);
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    public Instruction remplacerInstruction (int position, Instruction instruction){
        if (position<=0 || position> recette.size())
            throw new IllegalArgumentException("Position non identifiable");
        Instruction instruRemplacee = recette.get(position-1);
        recette.set(position-1, instruction);
        this.dureeEnMinutes = this.dureeEnMinutes.minus(instruRemplacee.getDureeEnMinutes());
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return instruRemplacee;
    }

    public Instruction supprimerInstruction (int position){
        if (position<=0 || position> recette.size())
            throw new IllegalArgumentException("Position non identifiable");
        Instruction instruSupp = recette.remove(position-1);
        this.dureeEnMinutes = this.dureeEnMinutes.minus(instruSupp.getDureeEnMinutes());
        return instruSupp;
    }

    public Iterator<Instruction> instructions(){
        return unmodifiableList(recette).iterator();
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        IngredientQuantifie iq = new IngredientQuantifie(ingredient, quantite, unite);
        if (ingredients.contains(iq))
            return false;
        return ingredients.add(iq);
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        return this.ajouterIngredient(ingredient, quantite, Unite.NEANT);
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        IngredientQuantifie iq = trouverIngredientQuantifie(ingredient);
        if (!ingredients.contains(iq))
            return false;
        iq.setQuantite(quantite);
        iq.setUnite(unite);
        return true;
    }

    public boolean supprimerIngredient(Ingredient ingredient){
        IngredientQuantifie iq = trouverIngredientQuantifie(ingredient);
        if (!ingredients.contains(iq))
            return false;
        return ingredients.remove(iq);
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        for (IngredientQuantifie iq: ingredients) {
            if (iq.getIngredient().equals(ingredient))
                return iq;
        }
        return null;
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }
}
