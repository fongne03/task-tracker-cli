# Task Tracker CLI

A simple command-line task tracker built in Java. This application allows users to manage tasks and track their progress using a local JSON file.

## Features

- Add tasks
- Update tasks
- Delete tasks
- Mark tasks as `todo`, `in-progress`, or `done`
- List all tasks
- List tasks by status
- Persistent storage using a JSON file

## Requirements

- Java JDK 8 or higher
- Command Line (CMD, PowerShell, or Terminal)

## How to Run the Project

### 1. Clone the Repository

```bash
git clone [https://github.com/fongne03/task-tracker-cli.git](https://github.com/fongne03/task-tracker-cli.git)
cd task-tracker-cli
```

### 2. Compile the Project

```bash
javac *.java
```

### 3. Run the Application

```bash
java TaskCli
```

## Usage Examples

### Add a Task
```bash
java TaskCli add "Buy groceries"
```

### List All Tasks
```bash
java TaskCli list
```

### List Tasks by Status
```bash
java TaskCli list todo
java TaskCli list in-progress
java TaskCli list done
```

### Mark a Task as In Progress
```bash
java TaskCli mark-in-progress 1
```

### Mark a Task as Done
```bash
java TaskCli mark-done 1
```

### Update a Task
```bash
java TaskCli update 1 "Buy groceries and cook dinner"
```

### Delete a Task
```bash
java TaskCli delete 1
```

## Data Storage

Tasks are stored in a file named `tasks.json` in the project directory. The file is automatically created if it does not exist.

**Example JSON structure:**

```json
[
  {
    "id": 1,
    "description": "Buy groceries",
    "status": "todo",
    "createdAt": "2024-01-09T10:00:00",
    "updatedAt": "2024-01-09T10:05:00"
  }
]
```

Each task includes:
- **ID**: Unique identifier
- **Description**: Details of the task
- **Status**: Current state of the task
- **Created timestamp**: Date and time of creation
- **Updated timestamp**: Date and time of last update

## Project Page

**GitHub Repository:**
[https://github.com/fongne03/task-tracker-cli](https://github.com/fongne03/task-tracker-cli)

## Notes

- No external libraries or frameworks are used.
- All file operations use Java’s native filesystem APIs.
- This project is intended for learning and practice.
