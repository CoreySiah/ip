public class Deadline extends Task {
    private String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book | 21 Mar 25"
        return super.toSaveFormat() + " | " + this.dueDate;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
