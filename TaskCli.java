import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public class TaskCli {

    static final String FILE_NAME = "tasks.json";

    public static void main(String[] args) {
        ensureFileExists();

        if (args.length == 0) {
            printHelp();
            return;
        }

        String command = args[0];

        switch (command) {
            case "add" -> addTask(args);
            case "update" -> updateTask(args);
            case "delete" -> deleteTask(args);
            case "mark-in-progress" -> markTask(args, "in-progress");
            case "mark-done" -> markTask(args, "done");
            case "list" -> listTasks(args);
            default -> printHelp();
        }
    }

    /**
     * Reads tasks from tasks.json and converts them into Task objects.
     *
     * @return a list of tasks loaded from the file
     */
    
    static List<Task> loadTask() {
        List<Task> tasks = new ArrayList<>();

        try {
            String content = Files.readString(Path.of(FILE_NAME)).trim();

            if (content.equals("[]")) {
                return tasks;
            }

            content = content.substring(1, content.length() - 1);

            String[] objects = content.split("\\}, \\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "");
                String[] fields = obj.split(",");

                Task task = new Task();

                for (String field : fields) {
                    String[] kv = field.split(":", 2);
                    String key = kv[0].replace("\"", "").trim();
                    String value = kv[1].replace("\"", "").trim();

                    switch (key) {
                        case "id" -> task.id = Integer.parseInt(value);
                        case "description" -> task.description = value;
                        case "status" -> task.status = value;
                        case "createdAt" -> task.createdAt = value;
                        case "updatedAt" -> task.updatedAt = value;
                    }
                }
                tasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error reading tasks file.");
        }

        return tasks;
    }

    /**
     * Writes the given list of tasks to tasks.json.
     *
     * @param tasks the list of tasks to persist
     */
    static void saveTasks(List<Task> tasks) {
        try {
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                json.append("{")
                    .append("\"id\":").append(t.id).append(",")
                    .append("\"description\":\"").append(t.description).append("\",")
                    .append("\"status\":\"").append(t.status).append("\",")
                    .append("\"createdAt\":\"").append(t.createdAt).append("\",")
                    .append("\"updatedAt\":\"").append(t.updatedAt).append("\"")
                    .append("}");
                
                if (i < tasks.size() - 1) json.append(",");
            }
            json.append("]");
            Files.writeString(Path.of(FILE_NAME), json.toString());
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    /**
     * Adds a new task with status "todo".
     */
    
    static void addTask(String[] args) {
        if (args.length < 2) {
            System.out.println("Task description required.");
        return;
        }

        List<Task> tasks = loadTask();

        int nextId = tasks.stream()
                        .mapToInt(t -> t.id)
                        .max()
                        .orElse(0) + 1;

        Task task = new Task();
        task.id = nextId;
        task.description = args[1];
        task.status = "todo";
        task.createdAt = LocalDateTime.now().toString();
        task.updatedAt = task.createdAt;

        tasks.add(task);
        saveTasks(tasks);

        System.out.println("Task added successfully (ID: " + nextId + ")");
    }
    /**
     * Lists tasks, optionally filtered by status.
     */
    static void listTasks(String[] args) {
        List<Task> tasks = loadTask();
        String filter = args.length > 1 ? args[1] : null;

        for (Task t : tasks) {
            if (filter == null || t.status.equals(filter)) {
                System.out.println("[" + t.id + "] " + t.description + " (" + t.status + ")");
            }
        }
    }

     /**
     * Updates the description of an existing task.
     */
    static void updateTask(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: update <id> <description");
            return;
        }

        int id = Integer.parseInt(args[1]);
        List<Task> tasks = loadTask();

        for (Task t : tasks) {
            if (t.id == id) {
                t.description = args[2];
                t.updatedAt = LocalDateTime.now().toString();
                saveTasks(tasks);
                System.out.println("Task updated!");
                return;
            }
        }

        System.out.println("Task not found");
    }

    /**
     * Deletes a task by ID.
     */
    static void deleteTask(String[] args) {
        if (args.length < 2) return;

        int id = Integer.parseInt(args[1]);
        List<Task> tasks = loadTask();

        tasks.removeIf(t -> t.id == id);
        saveTasks(tasks);

        System.out.println("Task deleted.");
    }

    
    /**
     * Updates the status of a task.
     */
    static void markTask(String[] args, String status) {
        if (args.length < 2) return;

        int id = Integer.parseInt(args[1]);
        List<Task> tasks = loadTask();

        for (Task t : tasks) {
            if (t.id == id) {
                t.status = status;
                t.updatedAt = LocalDateTime.now().toString();
                saveTasks(tasks);
                System.out.println("Task status updated");
                return;
            }
        }

        System.out.println("Task not found");
    }

    /** 
     * CHECK IF tasks.json EXISTS
     */


    static void ensureFileExists() {
         try {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            Files.write(file.toPath(), "[]".getBytes());
        }
    } catch (IOException e) {
        System.out.println("Error initialising storage file");
        System.exit(1);
        }
    }

    /* ================= HELPER METHODS ================= */

    static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  add <description>");
        System.out.println("  update <id> <description>");
        System.out.println("  delete <id>");
        System.out.println("  mark-in-progress <id>");
        System.out.println("  mark-done <id>");
        System.out.println("  list [todo|done|in-progress]");
    }
}