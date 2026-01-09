# task-tracker-cli

Task Tracker CLI is a simple command-line application built in Java that allows users to track and manage their tasks. The application supports adding, updating, deleting, and listing tasks, as well as marking tasks as todo, in progress, or done. All tasks are stored locally in a JSON file, making the data persistent between program runs. This project is intended to practice command-line interfaces, file system operations, and basic data persistence without using external libraries.

# How to Run the Project

Make sure you have Java JDK 8 or higher installed on your system. You can verify this by running java -version in your command line.

# Clone the project repository from GitHub using the following command:
git clone https://github.com/fongne03/task-tracker-cli.git

# Navigate into the project directory:
cd task-tracker-cli

# Compile the Java source files:
**javac *.java**

This will generate the required .class files.

# Run the application:
**java TaskCli**

**Usage Examples
**
- To add a new task:
**java TaskCli add "Buy groceries"**

- To list all tasks:
**java TaskCli list**

- To list tasks by status:
**java TaskCli list todo
java TaskCli list in-progress
java TaskCli list done**

To mark a task as in progress:
**java TaskCli mark-in-progress 1**

To mark a task as done:
**java TaskCli mark-done 1**

To update a task:
**java TaskCli update 1 "Buy groceries and cook dinner"**

To delete a task:
**java TaskCli delete 1**

# Data Storage

All tasks are stored in a local file named tasks.json located in the project directory. The file is automatically created if it does not already exist. Task data includes an ID, description, status, creation timestamp, and last updated timestamp.

# Project Page URL

GitHub Repository:
https://github.com/fongne03/task-tracker-cli

# Notes

This project does not use any external libraries or frameworks. All file operations are handled using Java’s native file system APIs. The project is designed as a learning exercise to strengthen understanding of Java, command-line interfaces, and basic persistence.
