package nat;

/**
 * The TDo class represents the class for tasks to be done w/o due dates.
 */
public class ToDo extends Task {
    /**
     * Constructor for TDo class
     * @param taskName Name of the task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Override and return the parent class Task save format
     */
    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book"
        return super.toSaveFormat();
    }

    /**
     * Override and return the TDo task type
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
