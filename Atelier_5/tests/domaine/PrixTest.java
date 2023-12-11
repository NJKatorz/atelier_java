package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {
    private Prix prixAucune;
    private Prix prixPub;
    private Prix prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(TypePromo.PUB, 15);
        prixSolde = new Prix(TypePromo.SOLDE, 10);
        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);
        prixPub.definirPrix(3, 15);
    }


    @DisplayName("Test du constructeur 1")
    @Test
    void testConstructeur1() {
        assertThrows(IllegalArgumentException.class, () -> new
                Prix(null, 10));
    }

    @DisplayName("Test du constructeur 2")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void testConstructeur2(int valeur) {
        assertThrows(IllegalArgumentException.class, () -> new
                Prix(TypePromo.SOLDE, valeur));
    }

    @DisplayName("Test du getter : getTypePromo()")
    @Test
    void getTypePromo() {
        assertNull(prixAucune.getTypePromo());
        assertEquals(prixPub.getTypePromo(), TypePromo.PUB);
        assertEquals(prixSolde.getTypePromo(), TypePromo.SOLDE);
    }

    @DisplayName("Test du getter : getValeurPromo")
    @Test
    void getValeurPromo() {
        assertAll(
                () -> assertEquals(prixAucune.getValeurPromo(), 0),
                () -> assertEquals(prixPub.getValeurPromo(), 15),
                () -> assertEquals(prixSolde.getValeurPromo(), 10)
        );

    }

    @DisplayName("Test de la méthode : definirPrix1 (si le paramètre quantité est 0 ou négatif)")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void definirPrix1(int quantiteMin) {
        assertThrows(IllegalArgumentException.class, () ->
                        prixSolde.definirPrix(quantiteMin, 15));
    }

    @DisplayName("Test de la méthode : definirPrix1 (si le paramètre prix unitaire est 0 ou négatif)")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void definirPrix2(int prixUnitaire) {
        assertThrows(IllegalArgumentException.class, () ->
                prixSolde.definirPrix(2, prixUnitaire));
    }

    @DisplayName("Test de la méthode : definirPrix3 (vérifiez que l’ancien prix a été remplacé.")
    @Test
    void definirPrix3() {
        prixAucune.definirPrix(10, 6);
        assertEquals(6, prixAucune.getPrix(10));
    }

    @DisplayName("Test de la méthode : getPrix1 (si le paramètre quantité est 0 ou négatif)")
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void getPrix1(int quantiteMin) {
        assertThrows(IllegalArgumentException.class, () ->
                prixSolde.getPrix(quantiteMin));
    }

    @DisplayName("Test de la méthode : getPrix2 (à partir d’une unité le prix unitaire est de 20 euros)")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9})
    void getPrix2(int quantiteMin) {
        assertEquals(20, prixAucune.getPrix(quantiteMin));
    }

    @DisplayName("Test de la méthode : getPrix3 (à partir de 10 unité le prix unitaire est de 10 euros)")
    @ParameterizedTest
    @ValueSource(ints = {10, 15, 20, 25})
    void getPrix3(int quantiteMin) {
        assertEquals(10, prixAucune.getPrix(quantiteMin));
    }

    @DisplayName("Test de la méthode : getPrix4 (si le paramètre quantité est à 2 pour l’attribut prixPub)")
    @Test
    void getPrix4() {
        assertThrows(QuantiteNonAutoriseeException.class, () ->
                prixPub.getPrix(2));
    }

    @DisplayName("Test de la méthode : getPrix5 (si le paramètre quantité est à 2 pour l’attribut prixSolde)")
    @Test
    void getPrix5() {
        assertThrows(QuantiteNonAutoriseeException.class, () ->
                prixSolde.getPrix(1));
    }

}