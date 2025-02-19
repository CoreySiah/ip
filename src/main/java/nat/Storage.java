package nat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks from/to a text file.
 * <p>
 * This class is responsible for persisting the task list in a non-volatile storage
 * by reading from and writing to a file.
 * </p>
 */
public class Storage {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";
    private String fileName;

    /**
     * Creates a Storage instance with the specified file path.
     *
     * @param fileName The path to the data file (e.g., "src/main/data/data.txt").
     */
    public Storage(String fileName) {
        this.fileName = fileName; // fileName = "src/main/data/data.txt"
        ensureFileExists();
    }

    /**
     * Loads tasks from the storage file.
     * <p>     * Reads tasks from the specified file and reconstructs them into an {@code ArrayList<Task>}.
     * Invalid task formats are skipped with warnings.
     * </p>
     *
     * @return A list of tasks loaded from the file. Returns an empty list if an error occurs.
     */
    public ArrayList<Task> load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            String line = reader.readLine();
            ArrayList<Task> taskList = new ArrayList<>();
            int numOfItems = 0;

            while (line != null) {
                String[] taskParts = line.split(" \\| ");
                if (taskParts.length < 3) {
                    System.out.println(SPACER + " Oops! Warning: Invalid task format in data file. Skipping...");
                    line = reader.readLine();
                    continue;
                }

                String taskType = taskParts[0];
                boolean isDone = taskParts[1].equals("1");
                String taskName = taskParts[2];

                switch (taskType) {
                case "T":
                    taskList.add(new ToDo(taskName));
                    numOfItems++;
                    break;
                case "D":
                    if (taskParts.length == 4) {
                        String dueDate = taskParts[3]; // Due date for the deadline
                        taskList.add(new Deadline(taskName, dueDate));
                        numOfItems++;
                    } else {
                        System.out.println(SPACER + " Oops! Warning: Invalid deadline task format. Skipping...");
                        line = reader.readLine();
                        continue;
                    }
                    break;
                case "E":
                    if (taskParts.length == 5) {
                        String startDate = taskParts[3]; // Start date for the event
                        String dueDate = taskParts[4]; // Due date for the event
                        taskList.add(new Event(taskName, startDate, dueDate));
                        numOfItems++;
                    } else {
                        System.out.println(SPACER + " Oops! Warning: Invalid event task format. Skipping...");
                        System.out.println(HORIZONTAL_LINE);
                        line = reader.readLine();
                        continue;
                    }
                    break;
                default:
                    continue;
                }

                // Mark the new task as done according to it's boolean
                if (isDone) {
                    taskList.get(numOfItems - 1).markAsDone();
                }

                // Read the next line
                line = reader.readLine();
            }

            // Return the loaded taskList
            return taskList;
        } catch (IOException e) {
            System.out.println(SPACER + " Nay! An error occurred while loading tasks: " + e.getMessage());
        }

        // Return a new empty taskList
        return new ArrayList<>();
    }

    /**
     * Saves the given task list to the storage file.
     * <p>
     * Iterates through the list and writes each task in a structured format to ensure
     * proper retrieval during loading.
     * </p>
     *
     * @param taskList The list of tasks to save.
     */
    public void save(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName))) {
            for (Task task : taskList) {
                if (task != null) {
                    writer.write(task.toSaveFormat());
                    writer.newLine();
                }
            }
            System.out.println("Yay! Tasks successfully saved to tasks.txt");
        } catch (IOException e) {
            System.out.println("Nay! An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Ensures the storage file exists. If it does not, the method creates it along
     * with any missing directories.
     */
    private void ensureFileExists() {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                if (file.createNewFile()) {
                    System.out.println("Storage file created: " + fileName);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to create storage file at " + fileName + " - " + e.getMessage());
        }
    }
}
