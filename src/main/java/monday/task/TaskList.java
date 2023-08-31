package monday.task;

import java.io.IOException;
import java.util.ArrayList;

import monday.storage.Storage;

/**
 * The TaskList class is responsible for storing and managing the tasks in the task list.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;

    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.list = storage.load();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private void save() {
        try {
            storage.save(list);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addToTask(Task task) {
        list.add(task);
        save();
        System.out.println("Got it. I've added this task:\n    " + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     */
    public void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void mark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        taskToEdit.markAsDone();
        save();
        System.out.println("Nice! I've marked this task as done:\n" + taskToEdit);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void unMark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        taskToEdit.unMark();
        save();
        System.out.println("OK, I've marked this task as not done yet:\n" + taskToEdit);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void delete(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        list.remove(index - 1);
        save();
        System.out.println("Noted. I've removed this task:\n" + taskToEdit);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Finds and prints tasks containing the specified keyword.
     *
     * @param keyword the keyword to search for in the tasks
     */
    public void find(String keyword) {
        if (list.isEmpty()) {
            System.out.println("Your list is empty.");
        } else {
            int matchingTaskCount = 1;
            System.out.println("Here are the matching tasks in your list:");
            for (Task curr : list) {
                if (curr.toString().contains(keyword)) {
                    System.out.println(matchingTaskCount + "." + curr);
                    matchingTaskCount++;
                }
            }
        }
    }
}
