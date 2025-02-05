public class Event extends Task {
    private String startDate;
    private String dueDate;

    public Event(String taskName, String startDate, String dueDate) {
        super(taskName);
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book | 5pm | 9pm"
        return super.toSaveFormat() + " | " + this.startDate + " | " + this.dueDate;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to: " + this.dueDate + ")";
    }
}
