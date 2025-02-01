public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String toSaveFormat() {
        // Format: "T | 1 | Read a book"
        String status = this.isDone ? "1" : "0";
        return getTaskType() + " | " + status + " | " + this.taskName;
    }

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
