package nat;

/**
 * Represents a to-do task without a due date.
 * Inherits from the {@link Task} class.
 */
public class ToDo extends Task {
    /**
     * Creates a new to-do task.
     *
     * @param taskName The name or description of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the formatted string representation of the task for storage.
     * Inherits the format from the parent class {@link Task}.
     *
     * @return A formatted string representation of the task for saving.
     */
    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book"
        return super.toSaveFormat();
    }

    /**
     * Returns the task type identifier for to-do tasks.
     *
     * @return The letter "T", representing a to-do task.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
