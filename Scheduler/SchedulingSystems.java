package Scheduler;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SchedulingSystems {

    // Define the structures for scheduling systems
    private final MyQueue<Task> queueSystem;
    private final MyQueue<Task> priorityQueueSystem;
    private final MyLinkedList<Task> linkedListSystem;
    private final MyStack<Task> stackSystem;
    private long totalQueueSystemTime;
    private long totalPriorityQueueSystemTime;
    private long totalLinkedListSystemTime;
    private long totalStackSystemTime;

    // Constructor to initialize the systems
    public SchedulingSystems() {
        priorityQueueSystem = new MyQueue<>(22);
        queueSystem = new MyQueue<>(22);
        linkedListSystem = new MyLinkedList<>();
        stackSystem = new MyStack<>(22);
    }


    // Method to read tasks from tasks.txt and populate scheduling systems
    public void loadTasks(String filePath) {
        try {
            // Implement reading tasks from the file and populating the systems
            Scanner in = new Scanner(new FileInputStream(filePath));
            while(in.hasNextLine()){
                String line = in.nextLine();
                String[] parts = line.split(" ");

                String methodName = parts[0];
                String inputType = parts[1];
                String input = parts[2];

                Task task = new Task(methodName, inputType, input);

                queueSystem.enqueue(task);
                priorityQueueSystem.enqueue(task);
                linkedListSystem.addLast(task);
                stackSystem.push(task);

            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }


    // Method to execute tasks using Queue scheduling system
    public void executeQueueSystem() {
        int taskCount = 1; // Initialize the task count
        long currentTime = 0; // Keep track of the current time in nanoseconds
        long totalResponseTime = 0; // Total response time for all tasks
        long totalTurnaroundTime = 0; // Total turnaround time for all tasks

        // Print header for the table
        System.out.println();
        System.out.println("Queue Scheduler System");
        System.out.printf("%-5s %-80s %-20s %-20s %-30s %-20s %-20s%n",
                "Count",
                "Task",
                "Started Time",
                "Response Time",
                "Completed Time",
                "Turnaround Time",
                "Execution Time");

        while (!queueSystem.isEmpty()) {
            Task currentTask = queueSystem.dequeue();
            long executionTime = Task.calculateExecutionTime(currentTask);

            // Calculate and print the response and turnaround times for the current task
            long responseTime = currentTime / 1000; // Convert nanoseconds to microseconds
            long turnaroundTime = (currentTime + executionTime) / 1000; // Convert nanoseconds to microseconds
            System.out.printf("%-5s %-80s %-20s %-20s %-30s %-20s %-20s%n",
                    taskCount,
                    currentTask,
                    currentTime,
                    responseTime + " microseconds",
                    (currentTime + executionTime)+ " microseconds",
                    turnaroundTime + " microseconds",
                    executionTime + " microseconds");

            // Update total response and turnaround times
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;

            // Update the current time
            currentTime += executionTime;
            taskCount++;
        }

        // Calculate and print average response and turnaround times
        long averageResponseTime = totalResponseTime / queueSystem.getSize();
        long averageTurnaroundTime = totalTurnaroundTime / queueSystem.getSize();
        System.out.printf("Average Response Time: %-20s%n", averageResponseTime + " microseconds");
        System.out.printf("Average Turnaround Time: %-20s%n", averageTurnaroundTime + " microseconds");
        totalQueueSystemTime += currentTime;
    }


    public void executeQueueSystemPriority() {
        int taskCount = 1; // Initialize the task count
        long currentTime = 0; // Keep track of the current time in nanoseconds
        long totalResponseTime = 0; // Total response time for all tasks
        long totalTurnaroundTime = 0; // Total turnaround time for all tasks
        // Print header for the table
        System.out.println();
        System.out.println("Priority Queue Scheduler System");
        System.out.printf("%-5s %-80s %-20s %-20s %-30s %-20s %-20s%n",
                "Count",
                "Task",
                "Started Time",
                "Response Time",
                "Completed Time",
                "Turnaround Time",
                "Execution Time");

        // Sort tasks by priority (you need to implement a method to sort tasks by priority)
        sortTasksByPriority();

        while (!priorityQueueSystem.isEmpty()) {
            Task currentTask = priorityQueueSystem.dequeue();
            long executionTime = Task.calculateExecutionTime(currentTask);

            // Calculate and print the response and turnaround times for the current task
            long responseTime = currentTime / 1000; // Convert nanoseconds to microseconds
            long turnaroundTime = (currentTime + executionTime) / 1000; // Convert nanoseconds to microseconds
            System.out.printf("%-5s %-80s %-20s %-20s %-30s %-20s %-20s%n",
                    taskCount,
                    currentTask,
                    currentTime,
                    responseTime + " microseconds",
                    (currentTime + executionTime) + " microseconds",
                    turnaroundTime + " microseconds",
                    executionTime + " microseconds");

            // Update total response and turnaround times
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;

            // Update the current time
            currentTime += executionTime;
            taskCount++;

        }

        // Calculate and print average response and turnaround times
        long averageResponseTime = totalResponseTime / priorityQueueSystem.getSize();
        long averageTurnaroundTime = totalTurnaroundTime / priorityQueueSystem.getSize();
        System.out.printf("Average Response Time: %-20s%n", averageResponseTime + " microseconds");
        System.out.printf("Average Turnaround Time: %-20s%n", averageTurnaroundTime + " microseconds");
        // Update total time for the priority queue system
        totalPriorityQueueSystemTime += currentTime;
    }


    private void sortTasksByPriority() {
        int n = priorityQueueSystem.getSize();
        MyQueue<Task> tempQueue = new MyQueue<>(n);

        // Copy tasks to a temporary queue
        while (!priorityQueueSystem.isEmpty()) {
            tempQueue.enqueue(priorityQueueSystem.dequeue());
        }

        // Selection Sort
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            Task minTask = null;

            // Dequeue tasks from the temporary queue and find the task with the minimum priority
            for (int j = i; j < n; j++) {
                Task currentTask = tempQueue.dequeue();
                if (minTask == null || Task.compareByExecutionTime(currentTask, minTask) < 0) {
                    if (minTask != null) {
                        tempQueue.enqueue(minTask);
                    }
                    minTask = currentTask;
                    minIndex = j;
                } else {
                    tempQueue.enqueue(currentTask);
                }
            }
            // Enqueue the sorted task into the original queue
            priorityQueueSystem.enqueue(minTask);
        }

        // Enqueue the remaining tasks back to the original queue
        while (!tempQueue.isEmpty()) {
            priorityQueueSystem.enqueue(tempQueue.dequeue());
        }
    }


    // Method to execute tasks using LinkedList scheduling system
    public void executeLinkedListSystem() {
        int taskCount = 1; // Initialize the task count
        long currentTime = 0; // Keep track of the current time in nanoseconds
        long totalResponseTime = 0; // Total response time for all tasks
        long totalTurnaroundTime = 0; // Total turnaround time for all tasks

        // Print header for the tables
        System.out.println();
        System.out.println("Linked List Scheduler System");
        System.out.printf("%-5s %-80s %-20s %-20s %-30s %-20s %-20s%n",
                "Count",
                "Task",
                "Started Time",
                "Response Time",
                "Completed Time",
                "Turnaround Time",
                "Execution Time");

        while(linkedListSystem.getSize() > 0){
            Task currentTask = linkedListSystem.removeFirst();
            long executionTime = Task.calculateExecutionTime(currentTask);

            // Calculate and print the response and turnaround times for the current task
            long responseTime = currentTime / 1000; // Convert nanoseconds to microseconds
            long turnaroundTime = (currentTime + executionTime) / 1000; // Convert nanoseconds to microseconds
            System.out.printf("%-5s %-80s %-20s %-20s %-30s %-20s %-20s%n",
                    taskCount,
                    currentTask,
                    currentTime,
                    responseTime + " microseconds",
                    (currentTime + executionTime)+ " microseconds",
                    turnaroundTime + " microseconds",
                    executionTime + " microseconds");

            // Update total response and turnaround times
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;

            // Update the current time
            currentTime += executionTime;
            taskCount++;
        }
        // Print average response and turnaround times
        long avgResponseTime = totalResponseTime / taskCount;
        long avgTurnaroundTime = totalTurnaroundTime / taskCount;
        System.out.println("Average Response Time: " + avgResponseTime + " microseconds");
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime + " microseconds");
        // Update total time for the linked list system
        totalLinkedListSystemTime += currentTime;
    }


    // Method to execute tasks using Stack scheduling system
    public void executeStackSystem() {
        int taskCount = 1; // Initialize the task count
        long currentTime = 0; // Keep track of the current time in nanoseconds
        long totalResponseTime = 0; // Total response time for all tasks
        long totalTurnaroundTime = 0; // Total turnaround time for all tasks
        //Print header for the titles
        System.out.println();
        System.out.println("Stack Scheduler System");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-80s %-20s %-20s %-30s %-20s %-20s%n",
                "Count",
                "Task",
                "Started Time",
                "Response Time",
                "Completed Time",
                "Turnaround Time",
                "Execution Time"));
        System.out.print(sb);

        while(!stackSystem.isEmpty()){
            Task current = stackSystem.pop();
            long executionTime = Task.calculateExecutionTime(current);
            // Calculate and print the response and turnaround times for the current task
            long responseTime = currentTime / 1000; // Convert nanoseconds to microseconds
            long turnaroundTime = (currentTime + executionTime) / 1000; // Convert nanoseconds to microseconds

            StringBuilder output = new StringBuilder();
            output.append(String.format("%-5d %-80s %-20s %-20s %-30s %-20s %-20s%n",
                    taskCount, current, currentTime, responseTime + " microseconds",
                    (currentTime + executionTime) + " microseconds", turnaroundTime + " microseconds",
                    executionTime + " microseconds"));

            System.out.print(output);

            // Update total response and turnaround times
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;

            // Update the current time
            currentTime += executionTime;
            taskCount++;
        }
        // Print average response and turnaround times
        long avgResponseTime = totalResponseTime / taskCount;
        long avgTurnaroundTime = totalTurnaroundTime / taskCount;
        System.out.println("Average Response Time: " + avgResponseTime + " microseconds");
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime + " microseconds");
        // Update total time for the stack system
        totalStackSystemTime += currentTime;
    }

    // Method to display the total time taken for each system and compare which is the fastest
    public void displayTotalTimes() {
        System.out.println("\nTotal Time Taken for Each Scheduling System:");
        System.out.println("Queue System Total Time: " + totalQueueSystemTime + " microseconds");
        System.out.println("Priority Queue System Total Time: " + totalPriorityQueueSystemTime + " microseconds");
        System.out.println("Linked List System Total Time: " + totalLinkedListSystemTime + " microseconds");
        System.out.println("Stack System Total Time: " + totalStackSystemTime + " microseconds");

        // Find the fastest system
        String fastestSystem = findFastestSystem();
        System.out.println("\nFastest Scheduling System: " + fastestSystem);
    }

    // Helper method to find the fastest scheduling system
    private String findFastestSystem() {
        long minTime = Math.min(totalQueueSystemTime,
                Math.min(totalPriorityQueueSystemTime,
                        Math.min(totalLinkedListSystemTime, totalStackSystemTime)));

        if (minTime == totalQueueSystemTime) {
            return "Queue System";
        } else if (minTime == totalPriorityQueueSystemTime) {
            return "Priority Queue System";
        } else if (minTime == totalLinkedListSystemTime) {
            return "Linked List System";
        } else {
            return "Stack System";
        }
    }



}
