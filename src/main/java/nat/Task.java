package nat;

/**
 * Represents a general task in the task manager.
 * A task has a name and a completion status.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Creates a new task with the given name.
     * The task is initially marked as not done.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     * Used primarily for unit testing.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Checks if the task is completed.
     * Used primarily for unit testing.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the task's completion status as a status icon.
     * The icon is "X" if the task is done, and " " (empty) otherwise.
     *
     * @return A status icon representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Converts the task into a formatted string for storage.
     * The format follows: {@code <TaskType> | <Status> | <TaskName>}.
     * - Task type is represented as a single character.
     * - Status is {@code 1} if done, {@code 0} otherwise.
     *
     * @return A string representation of the task in storage format.
     */
    public String toSaveFormat() {
        // Format: "U | 1 | Read a book"
        String status = this.isDone ? "1" : "0";
        return getTaskType() + " | " + status + " | " + this.taskName;
    }

    /**
     * Returns the type of the task.
     * Default implementation returns "U" (Unknown).
     * Subclasses should override this method to return the appropriate type.
     *
     * @return The task type as a single-character string.
     */
    public String getTaskType() {
        // Unknown task type
        return "U";
    }

    /**
     * Returns a string representation of the task.
     * Includes the completion status and task name.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.taskName;
    }
}
