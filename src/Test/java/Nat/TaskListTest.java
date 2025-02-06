package Nat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testPerformToDoCommand_validTask() {
        taskList.performToDoCommand("Read a book");
        assertEquals(1, taskList.getTaskList().size());
        assertEquals("Read a book", taskList.getTaskList().get(0).getTaskName());
    }

    @Test
    public void testPerformToDoCommand_emptyTask() {
        taskList.performToDoCommand("");
        assertEquals(0, taskList.getTaskList().size(), "Task list should remain empty if command has no description.");
    }

    @Test
    public void testPerformDeadlineCommand_validTask() {
        taskList.performDeadlineCommand("Submit project /by 02/03/2025 1600");
        assertEquals(1, taskList.getTaskList().size());
        assertTrue(taskList.getTaskList().get(0) instanceof Deadline);
        assertEquals("Submit project", taskList.getTaskList().get(0).getTaskName());
    }

    @Test
    public void testPerformEventCommand_validTask() {
        taskList.performEventCommand("Team meeting /from 03/01/2025 1700 /to 03/03/2025 1900");
        assertEquals(1, taskList.getTaskList().size());
        assertTrue(taskList.getTaskList().get(0) instanceof Event);
        assertEquals("Team meeting", taskList.getTaskList().get(0).getTaskName());
    }

    @Test
    public void testPerformMarkCommand() {
        ToDo task = new ToDo("Complete homework");
        taskList.performToDoCommand(task.getTaskName());

        String[] markCommand = {"mark", "1"};
        taskList.performMarkCommand(markCommand);
        assertTrue(taskList.getTaskList().get(0).isDone(), "Task should be marked as done.");
    }

    @Test
    public void testPerformUnmarkCommand() {
        ToDo task = new ToDo("Complete homework");
        taskList.performToDoCommand(task.getTaskName());

        String[] markCommand = {"mark", "1"};
        taskList.performMarkCommand(markCommand);

        String[] unmarkCommand = {"unmark", "1"};
        taskList.performUnmarkCommand(unmarkCommand);
        assertFalse(taskList.getTaskList().get(0).isDone(), "Task should be marked as not done.");
    }

    @Test
    public void testPerformDeleteCommand() {
        taskList.performToDoCommand("Go for a walk");
        assertEquals(1, taskList.getTaskList().size());

        String[] deleteCommand = {"delete", "1"};
        taskList.performDeleteCommand(deleteCommand);
        assertEquals(0, taskList.getTaskList().size(), "Task list should be empty after deleting the only task.");
    }

    @Test
    public void testPerformListCommand() {
        taskList.performToDoCommand("Task 1");
        taskList.performToDoCommand("Task 2");

        assertEquals(2, taskList.getTaskList().size());
        assertEquals("Task 1", taskList.getTaskList().get(0).getTaskName());
        assertEquals("Task 2", taskList.getTaskList().get(1).getTaskName());
    }
}
