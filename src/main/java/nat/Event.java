package nat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents the class for tasks to be done w/ a start and due date.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    /**
     * Constructor for Event class
     * @param taskName Name of the task
     * @param startDate Task start date
     * @param dueDate Task deadline date
     */
    public Event(String taskName, String startDate, String dueDate) {
        super(taskName);
        this.startDate = this.parseDateTime(startDate);
        this.dueDate = this.parseDateTime(dueDate);
    }

    /**
     * Transform the date input from String into LocalDateTime
     */
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, inputFormat);
    }

    /**
     * Override and return the parent class Task save format
     */
    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book | 5pm | 9pm"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return super.toSaveFormat() + " | " + this.startDate.format(format) + " | " + this.dueDate.format(format);
    }

    /**
     * Override and return the Event task type
     */
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
