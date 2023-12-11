package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {
    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;
    private Produit produit1;
    private Produit produit2;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB, 15);
        prixSolde = new Prix(TypePromo.SOLDE, 10);
        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);
        prixPub.definirPrix(3, 15);

        produit1 = new Produit("Prod1", "OralB", "Rayon 1");
        produit2 = new Produit("Prod2", "Eco+", "Rayon 2");
        produit1.ajouterPrix(LocalDate.of(2023, 10, 15), prixAucune);
        produit1.ajouterPrix(LocalDate.of(2023, 10, 3), prixPub);
        produit1.ajouterPrix(LocalDate.of(2023, 10, 25), prixSolde);
    }

    @DisplayName("Test du constructeur")
    @Test
    void testConstructeur() {
        // Paramètre null
        assertThrows(IllegalArgumentException.class, () -> new
                Produit(null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new
                Produit("Test", null, null));
        assertThrows(IllegalArgumentException.class, () -> new
                Produit(null, "Test", null));
        assertThrows(IllegalArgumentException.class, () -> new
                Produit(null, null, "Test"));

        // Paramètre vide
        assertThrows(IllegalArgumentException.class, () -> new
                Produit("", "", ""));
        assertThrows(IllegalArgumentException.class, () -> new
                Produit("Test", "", ""));
        assertThrows(IllegalArgumentException.class, () -> new
                Produit("", "Test", ""));
        assertThrows(IllegalArgumentException.class, () -> new
                Produit("", "", "Test"));
    }

    @DisplayName("Test getMarque()")
    @Test
    void getMarque() {
        assertAll(
                () -> assertEquals("OralB", produit1.getMarque()),
                () -> assertEquals("Eco+", produit2.getMarque())
        );
    }

    @DisplayName("Test getNom()")
    @Test
    void getNom() {
        assertAll(
                () -> assertEquals("Prod1", produit1.getNom()),
                () -> assertEquals("Prod2", produit2.getNom())
        );
    }

    @DisplayName("Test getRayon()")
    @Test
    void getRayon() {
        assertAll(
                () -> assertEquals("Rayon 1", produit1.getRayon()),
                () -> assertEquals("Rayon 2", produit2.getRayon())
        );
    }

    @DisplayName("Test de la méthode : ajouterPrix1 (si un des paramètres est null)")
    @Test
    void ajouterPrix1() {
        assertThrows(IllegalArgumentException.class, () ->
                produit1.ajouterPrix(LocalDate.now(), null));
        assertThrows(IllegalArgumentException.class, () ->
                produit1.ajouterPrix(null, prixAucune));
    }

    @DisplayName("Test de la méthode : ajouterPrix2 (si la date est déjà présente)")
    @Test
    void ajouterPrix2() {
        assertThrows(DateDejaPresenteException.class, () ->
                produit1.ajouterPrix(LocalDate.of(2023, 10, 15), prixAucune));
    }

    @DisplayName("Test de la méthode : ajouterPrix3 (un prix pour une date donnée et vérifier ce prix avec le getPrix")
    @Test
    void ajouterPrix3() {
        produit1.ajouterPrix(LocalDate.of(2023, 10, 30), prixPub);
        assertEquals(15, produit1.getPrix(LocalDate.of(2023, 10, 30)).getPrix(3));
    }

    @DisplayName("Test de la méthode : getPrix1 (si date antérieure)")
    @Test
    void getPrix1() {
        assertThrows(PrixNonDisponibleException.class, () ->
                produit1.getPrix(LocalDate.of(2022, 8, 2)));
    }

    @DisplayName("Test de la méthode : getPrix2 (si un prix pour un produit qui n’en n’a pas)")
    @Test
    void getPrix2() {
        assertThrows(PrixNonDisponibleException.class, () ->
                produit2.getPrix(LocalDate.now()));
    }

    @DisplayName("Test de la méthode : getPrix3 (si un prix pour une date comprise entre deux dates pour lesquelles le prix a été défini)")
    @Test
    void getPrix3() {
        assertEquals(prixAucune, produit1.getPrix(LocalDate.of(2023, 10, 15)));
    }

    @DisplayName("Test de la méthode : testEquals1 (2 instances de Produit différentes mais qui ont les même marque, nom et rayon)")
    @Test
    void testEquals1() {
        Produit produitTest = new Produit("Prod1", "OralB", "Rayon 1");
        assertEquals(produit1, produitTest);
    }

    @DisplayName("Test de la méthode : testEquals2 (deux produits ayant la même marque et le même rayon mais ayant des noms différents)")
    @Test
    void testEquals2() {
        Produit produitTest = new Produit("Test1", "OralB", "Rayon 1");
        assertNotEquals(produit1, produitTest);
    }

    @DisplayName("Test de la méthode : testEquals3 ( deux produits ayant le même nom et le même rayon mais ayant des marques différentes.)")
    @Test
    void testEquals3() {
        Produit produitTest = new Produit("Prod1", "OralC", "Rayon 1");
        assertNotEquals(produit1, produitTest);
    }

    @DisplayName("Test de la méthode : testEquals4 (deux produits ayant le même nom et la même marque mais ayant des rayons différents)")
    @Test
    void testEquals4() {
        Produit produitTest = new Produit("Prod1", "OralB", "Rayon Brrrr");
        assertNotEquals(produit1, produitTest);
    }

    @DisplayName("Test de la méthode : testHashCode (renvoie bien la même valeur pour 2 instances de Produit différentes mais qui ont les même marque, nom et rayon)")
    @Test
    void testHashCode() {
        Produit produitTest = new Produit("Prod1", "OralB", "Rayon 1");
        assertEquals(produit1, produitTest);
    }
}