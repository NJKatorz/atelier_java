package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


class MoniteurImplTest {
    private Sport sport;
    private Stage stage;
    private Moniteur moniteur;

    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("Donald");
        sport = Mockito.mock(Sport.class);
        Mockito.when(sport.contientMoniteur(moniteur)).thenReturn(true);
        stage = Mockito.mock(Stage.class);
        Mockito.when(stage.getMoniteur()).thenReturn(null);
        Mockito.when(stage.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(stage.getSport()).thenReturn(sport);
    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            Stage newStage = Mockito.mock(Stage.class);
            Mockito.when(newStage.getMoniteur()).thenReturn(null);
            Mockito.when(newStage.getNumeroDeSemaine()).thenReturn(i);
            Mockito.when(newStage.getSport()).thenReturn(sport);
            moniteur.ajouterStage(newStage);

        }
    }

    @Test
    void testMoniteurTC1() {
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> assertEquals(1, moniteur.nombreDeStages()),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC2() {
        amenerALEtat(1, moniteur);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertEquals(2, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC3() {
        amenerALEtat(2, moniteur);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertEquals(3, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC4() {
        amenerALEtat(3, moniteur);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stage)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stage)),
                () -> Mockito.verify(stage).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC5() {
        amenerALEtat(3, moniteur);
        moniteur.ajouterStage(stage);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stage)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                // () -> assertTrue(moniteur.contientStage(stage)),
                () -> Mockito.verify(stage, Mockito.atLeastOnce()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC6() {
        amenerALEtat(4, moniteur);
        Stage stageEnSemaine = Mockito.mock(Stage.class);
        Mockito.when(stageEnSemaine.getNumeroDeSemaine()).thenReturn(1);
        Mockito.when(stageEnSemaine.getMoniteur()).thenReturn(null);
        Mockito.when(stageEnSemaine.getSport()).thenReturn(sport);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(stageEnSemaine)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> assertFalse(moniteur.contientStage(stageEnSemaine)),
                () -> Mockito.verify(stageEnSemaine, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC7() {
        amenerALEtat(4, moniteur);
        Moniteur autreMoniteur = Mockito.mock(Moniteur.class);
        Mockito.when(autreMoniteur.getNom()).thenReturn("Roger");
        Stage autreStage = Mockito.mock(Stage.class);
        Mockito.when(autreStage.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(autreStage.getMoniteur()).thenReturn(autreMoniteur);
        Mockito.when(autreStage.getSport()).thenReturn(sport);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(autreStage)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> assertFalse(moniteur.contientStage(autreStage)),
                () -> Mockito.verify(autreStage, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC8() {
        amenerALEtat(4, moniteur);
        Stage stageNonPresent = Mockito.mock(Stage.class);
        Mockito.when(stageNonPresent.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(stageNonPresent.getMoniteur()).thenReturn(moniteur);
        Mockito.when(stageNonPresent.getSport()).thenReturn(sport);
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageNonPresent)),
                () -> assertEquals(5, moniteur.nombreDeStages()),
                () -> assertTrue(moniteur.contientStage(stageNonPresent)),
                () -> Mockito.verify(stageNonPresent, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }

    @Test
    void testMoniteurTC9() {
        amenerALEtat(4, moniteur);
        Sport sportHorsCompet = Mockito.mock(Sport.class);
        Mockito.when(sportHorsCompet.contientMoniteur(moniteur)).thenReturn(false);
        Stage autreStage = Mockito.mock(Stage.class);
        Mockito.when(autreStage.getNumeroDeSemaine()).thenReturn(8);
        Mockito.when(autreStage.getMoniteur()).thenReturn(moniteur);
        Mockito.when(autreStage.getSport()).thenReturn(sportHorsCompet);
        assertAll(
                () -> assertFalse(moniteur.ajouterStage(autreStage)),
                () -> assertEquals(4, moniteur.nombreDeStages()),
                () -> assertFalse(moniteur.contientStage(autreStage)),
                () -> Mockito.verify(autreStage, Mockito.never()).enregistrerMoniteur(moniteur)
        );
    }
}