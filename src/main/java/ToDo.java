public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book"
        return super.toSaveFormat();
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
