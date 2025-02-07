package Nat;

/**
 * The Task class represents the class for general tasks.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Return the task name; primarily used in JUnit testing
     * @return taskName
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Return the task completeness; primarily used in JUnit testing
     * @return isDone
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Return the task completeness as a status icon string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Save format for Storage
     */
    public String toSaveFormat() {
        // Format: "U | 1 | Read a book"
        String status = this.isDone ? "1" : "0";
        return getTaskType() + " | " + status + " | " + this.taskName;
    }

    /**
     * Return the placeholder task type
     */
    public String getTaskType() {
        // Unknown task type
        return "U";
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.taskName;
    }
}
