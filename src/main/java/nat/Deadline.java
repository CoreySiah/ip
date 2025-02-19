package nat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents the class for tasks to be done w/ a due date.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructor for Deadline class
     * @param taskName Name of the task
     * @param dueDate Task deadline date
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
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
        // Format: "T | 1 | Read a book | 21 Mar 25"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return super.toSaveFormat() + " | " + this.dueDate.format(format);
    }

    /**
     * Override and return the Deadline task type
     */
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
