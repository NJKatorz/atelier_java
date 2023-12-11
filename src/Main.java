import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[ ] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Somme des éléments du flux
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        if (sum.isPresent()) {
            System.out.println("Somme : " + sum.get());
        }

// Produit des éléments du flux avec une valeur d'identité
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Produit : " + product);


    }
}