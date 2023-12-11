package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoniteurImplTest {

    private Sport sport;
    private Moniteur moniteurCompetent;
    private Stage stage;

    @BeforeEach
    void setUp() {
        sport = new SportStub(true);
        moniteurCompetent = new MoniteurImpl("Donald");
        stage = new StageStub(8, sport, null);
    }

    private void amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            moniteur.ajouterStage(new StageStub(i, sport, null));
        }
    }

    @Test
    void testMoniteurTC1() {
        assertAll(
                () -> assertTrue(moniteurCompetent.ajouterStage(stage)),
                () -> assertTrue(moniteurCompetent.contientStage(stage)),
                () -> assertEquals(1, moniteurCompetent.nombreDeStages())

        );
    }

    @Test
    void testMoniteurTC2() {
        amenerALEtat(1, moniteurCompetent);
        assertAll(
                () -> assertTrue(moniteurCompetent.ajouterStage(stage)),
                () -> assertEquals(2, moniteurCompetent.nombreDeStages()),
                () -> assertTrue(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC3() {
        amenerALEtat(2, moniteurCompetent);
        assertAll(
                () -> assertTrue(moniteurCompetent.ajouterStage(stage)),
                () -> assertEquals(3, moniteurCompetent.nombreDeStages()),
                () -> assertTrue(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC4() {
        amenerALEtat(3, moniteurCompetent);
        assertAll(
                () -> assertTrue(moniteurCompetent.ajouterStage(stage)),
                () -> assertEquals(4, moniteurCompetent.nombreDeStages()),
                () -> assertTrue(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC5() {
        amenerALEtat(3, moniteurCompetent);
        moniteurCompetent.ajouterStage(stage);
        assertAll(
                () -> assertTrue(moniteurCompetent.supprimerStage(stage)),
                () -> assertEquals(3, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC6() {
        amenerALEtat(2, moniteurCompetent);
        moniteurCompetent.ajouterStage(stage);
        assertAll(
                () -> assertTrue(moniteurCompetent.supprimerStage(stage)),
                () -> assertEquals(2, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC7() {
        amenerALEtat(1, moniteurCompetent);
        moniteurCompetent.ajouterStage(stage);
        assertAll(
                () -> assertTrue(moniteurCompetent.supprimerStage(stage)),
                () -> assertEquals(1, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC8() {
        moniteurCompetent.ajouterStage(stage);
        assertAll(
                () -> assertTrue(moniteurCompetent.supprimerStage(stage)),
                () -> assertEquals(0, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC9() {
        amenerALEtat(3, moniteurCompetent);
        moniteurCompetent.ajouterStage(stage);
        assertAll(
                () -> assertFalse(moniteurCompetent.ajouterStage(stage)),
                () -> assertEquals(4, moniteurCompetent.nombreDeStages())
               // () -> assertTrue(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC10() {
        amenerALEtat(4, moniteurCompetent);
        Stage stageEnSemaine = new StageStub(1, sport, null);
        assertAll(
                () -> assertFalse(moniteurCompetent.ajouterStage(stageEnSemaine)),
                () -> assertEquals(4, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(stageEnSemaine))
        );
    }

    @Test
    void testMoniteurTC11() {
        amenerALEtat(4, moniteurCompetent);
        assertAll(
                () -> assertFalse(moniteurCompetent.supprimerStage(stage)),
                () -> assertEquals(4, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(stage))
        );
    }

    @Test
    void testMoniteurTC12() {
        amenerALEtat(4, moniteurCompetent);
        Moniteur autreMoniteur = new MoniteurImpl("Roger");
        Stage autreStage = new StageStub(8, sport, autreMoniteur);
        assertAll(
                () -> assertFalse(moniteurCompetent.ajouterStage(autreStage)),
                () -> assertEquals(4, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(autreStage))
        );
    }

    @Test
    void testMoniteurTC13() {
        amenerALEtat(4, moniteurCompetent);
        Stage stageNonPresent = new StageStub(8, sport, moniteurCompetent);
        assertAll(
                () -> assertTrue(moniteurCompetent.ajouterStage(stageNonPresent)),
                () -> assertEquals(5, moniteurCompetent.nombreDeStages()),
                () -> assertTrue(moniteurCompetent.contientStage(stageNonPresent))
        );
    }

    @Test
    void testMoniteurTC14() {
        amenerALEtat(4, moniteurCompetent);
        Sport sportHorsCompet = new SportStub(false);
        Stage autreStage = new StageStub(8, sportHorsCompet, null);
        assertAll(
                () -> assertFalse(moniteurCompetent.ajouterStage(autreStage)),
                () -> assertEquals(4, moniteurCompetent.nombreDeStages()),
                () -> assertFalse(moniteurCompetent.contientStage(autreStage))
        );
    }
}