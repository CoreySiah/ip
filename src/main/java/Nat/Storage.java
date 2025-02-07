package Nat;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

/**
 * The Storage class represents the component which handles loading and saving the task list
 * into a separate .txt file.
 */
public class Storage {
    private String fileName;
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";

    public Storage(String fileName) {
        this.fileName = fileName; // fileName = "src/main/data/data.txt"
    }

    /**
     * Load the saved task list from a .txt file for usage.
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
                            String dueDate = taskParts[3];  // Due date for the deadline
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
                            String startDate = taskParts[3];  // Start date for the event
                            String dueDate = taskParts[4];  // Due date for the event
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
     * Save the cached task list into a .txt file for non-volatile storage.
     */
    public void save(ArrayList<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName))) {
            for (Task task : taskList) {
                if (task != null) {
                    writer.write(task.toSaveFormat());
                    writer.newLine();
                }
            }
            System.out.println(SPACER + " Yay! Tasks successfully saved to tasks.txt");
        } catch (IOException e) {
            System.out.println(SPACER + " Nay! An error occurred while saving tasks: " + e.getMessage());
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
