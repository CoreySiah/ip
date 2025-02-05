import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = this.parseDateTime(dueDate);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, inputFormat);
    }

    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book | 21 Mar 25"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return super.toSaveFormat() + " | " + this.dueDate.format(format);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + this.dueDate.format(outputFormat) + ")";
    }
}
