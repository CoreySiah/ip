package nat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start date and an end date.
 * This class extends {@link Task} and includes two {@link LocalDateTime} fields
 * to store the start time and due date of the task.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    /**
     * Creates a new Event task with the given name, start date, and due date.
     *
     * @param taskName  The name of the task.
     * @param startDate The start date of the event in {@code "d/M/yyyy HHmm"} format (e.g., "2/12/2019 1400").
     * @param dueDate   The due date of the event in {@code "d/M/yyyy HHmm"} format (e.g., "2/12/2019 1800").
     */
    public Event(String taskName, String startDate, String dueDate) {
        super(taskName);
        this.startDate = this.parseDateTime(startDate);
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
     * Converts the Event task into a formatted string for storage.
     * The format follows: {@code "E | <Status> | <TaskName> | <StartDate> | <DueDate>"}.
     * - Task type is {@code "E"} (Event).
     * - Status is {@code 1} if done, {@code 0} otherwise.
     * - Start and due dates are formatted as {@code "d/M/yyyy HHmm"} (e.g., "2/12/2019 1400").
     *
     * @return A formatted string representing the Event task in storage format.
     */
    @Override
    public String toSaveFormat() {
        // Format: "T | 1 | Read a book | 5pm | 9pm"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return super.toSaveFormat() + " | " + this.startDate.format(format) + " | " + this.dueDate.format(format);
    }

    /**
     * Returns the task type identifier for Event tasks.
     * Overrides {@link Task#getTaskType()}.
     *
     * @return The character {@code "E"} representing an Event task.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a string representation of the Event task,
     * including its completion status, task name, and formatted start and due dates.
     * The dates are displayed in {@code "MMM dd yyyy, h:mm a"} format (e.g., "Dec 02 2019, 2:00 PM").
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + this.startDate.format(outputFormat) + " to: "
                + this.dueDate.format(outputFormat) + ")";
    }
}
