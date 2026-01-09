# Task Tracker CLI

A simple command-line task tracker built in Java.
This application allows users to manage tasks and track their progress using a local JSON file.

# Features

Add tasks
Update tasks
Delete tasks
Mark tasks as todo, in-progress, or done
List all tasks
List tasks by status
Persistent storage using a JSON file

# Requirements

Java JDK 8 or higher
Command Line (CMD, PowerShell, or Terminal)

# How to Run the Project

# Clone the Repository

git clone https://github.com/fongne03/task-tracker-cli.git

cd task-tracker-cli

# Compile the Project

javac *.java

# Run the Application

java TaskCli

# Usage Examples

# Add a Task

java TaskCli add "Buy groceries"

# List All Tasks

java TaskCli list

# List Tasks by Status

java TaskCli list todo
java TaskCli list in-progress
java TaskCli list done

# Mark a Task as In Progress

java TaskCli mark-in-progress 1

# Mark a Task as Done

java TaskCli mark-done 1

# Update a Task

java TaskCli update 1 "Buy groceries and cook dinner"

# Delete a Task

java TaskCli delete 1

# Data Storage

Tasks are stored in a file named tasks.json in the project directory.
The file is automatically created if it does not exist.

Each task includes:
ID
Description
Status
Created timestamp
Updated timestamp

# Project Page

GitHub Repository:
https://github.com/fongne03/task-tracker-cli

# Notes

No external libraries or frameworks are used
All file operations use Java’s native filesystem APIs
This project is intended for learning and practice
