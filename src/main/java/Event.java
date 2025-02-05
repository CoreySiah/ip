import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    public Event(String taskName, String startDate, String dueDate) {
        super(taskName);
        this.startDate = this.parseDateTime(startDate);
        this.dueDate = this.parseDateTime(dueDate);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, inputFormat);
    }

    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book | 5pm | 9pm"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return super.toSaveFormat() + " | " + this.startDate.format(format) + " | " + this.dueDate.format(format);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + this.startDate.format(outputFormat) + " to: "
                + this.dueDate.format(outputFormat) + ")";
    }
}
