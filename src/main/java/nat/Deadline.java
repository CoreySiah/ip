package nat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * This class extends {@link Task} and includes a {@link LocalDateTime} field
 * to store the due date of the task.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Creates a new Deadline task with the given name and due date.
     *
     * @param taskName The name of the task.
     * @param dueDate  The deadline of the task in {@code "d/M/yyyy HHmm"} format (e.g., "2/12/2019 1800").
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = this.parseDateTime(dueDate);
    }

    /**
     * Parses a date-time string and converts it into a {@link LocalDateTime} object.
     * The expected input format is {@code "d/M/yyyy HHmm"} (e.g., "2/12/2019 1800").
     *
     * @param dateTime The date-time string to be parsed.
     * @return A {@link LocalDateTime} representation of the input.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, inputFormat);
    }

    /**
     * Converts the Deadline task into a formatted string for storage.
     * The format follows: {@code "D | <Status> | <TaskName> | <DueDate>"}.
     * - Task type is {@code "D"} (Deadline).
     * - Status is {@code 1} if done, {@code 0} otherwise.
     * - Due date is formatted as {@code "d/M/yyyy HHmm"} (e.g., "2/12/2019 1800").
     *
     * @return A formatted string representing the Deadline task in storage format.
     */
    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book | 21 Mar 25"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return super.toSaveFormat() + " | " + this.dueDate.format(format);
    }

    /**
     * Returns the task type identifier for Deadline tasks.
     * Overrides {@link Task#getTaskType()}.
     *
     * @return The character {@code "D"} representing a Deadline task.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns a string representation of the Deadline task,
     * including its completion status, task name, and formatted due date.
     * The due date is displayed in {@code "MMM dd yyyy, h:mm a"} format (e.g., "Dec 02 2019, 6:00 PM").
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + this.dueDate.format(outputFormat) + ")";
    }
}
