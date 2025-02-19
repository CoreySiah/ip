package nat; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToDoCreation() {
        ToDo task = new ToDo("Read a book");
        assertEquals("Read a book", task.getTaskName());
    }

    @Test
    public void testMarkAsDone() {
        ToDo task = new ToDo("Complete homework");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testToString() {
        ToDo task = new ToDo("Attend meeting");
        String expectedOutput = "[T][ ] Attend meeting";
        assertEquals(expectedOutput, task.toString());
    }
}
