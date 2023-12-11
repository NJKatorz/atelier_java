import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

    private Task task1;

    @BeforeEach
    void setUp() {
        task1 = new Task("task 1", "description 1");
    }

    @Test
    void createTask() {
        assertAll(() -> assertEquals("task 1", task1.getTitle()),
                () -> assertEquals("description 1", task1.getDescription()));
    }

    @Test
    void createNullFieldsTask() {

        assertAll(() -> assertThrows(IllegalArgumentException.class,
                        () -> new Task(null, "description1"))
                , () -> assertThrows(IllegalArgumentException.class,
                        () -> new Task("title1", null)
                ));
    }

    @Test
    void createEmptyTitleTask() {
        assertThrows(IllegalArgumentException.class,
                () -> new Task(" ", "description1"));
    }

    @Test
    void completeTask() {
        assertTrue(task1.finishTask());
    }

    @Test
    void completeAlreadyCompletedTask() {
        task1.finishTask();
        assertFalse(task1.finishTask());
    }

    @Test
    void updateTitle() {
        assertAll(() -> assertTrue(task1.setTitle("title one")),
                () -> assertEquals("title one", task1.getTitle()));
    }

    @Test
    void updateTitleWhenCompletedTask() {
        task1.finishTask();
        assertAll(() -> assertFalse(task1.setTitle("title one")),
                () -> assertEquals("task 1", task1.getTitle()));
    }

    @Test
    void updateTitleToEmptyOrNullString() {
        assertAll(() -> assertFalse(task1.setTitle("")),
                () -> assertFalse(task1.setTitle(null)));
    }

    @Test
    void updateDescription() {
        assertAll(() -> assertTrue(task1.setDescription("description one")),
                () -> assertEquals("description one", task1.getDescription()));
    }

    @Test
    void updateDescriptionWhenCompletedTask() {
        task1.finishTask();
        assertAll(() -> assertFalse(task1.setDescription("title one")),
                () -> assertEquals("description 1", task1.getDescription()));
    }

    @Test
    void updateDescriptionToNull() {
        assertFalse(task1.setDescription(null));
    }


}