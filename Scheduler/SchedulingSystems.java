/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SchedulingSystems {

    // Define the structures for scheduling systems
    private final MyQueue<Task> queueSystem;
    private final MyLinkedList<Task> linkedListSystem;
    private final MyStack<Task> stackSystem;

    // Constructor to initialize the systems
    public SchedulingSystems() {
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
        System.out.printf("%-5s %-40s %-30s %-20s %-30s %-20s %-20s%n",
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
            System.out.printf("%-5d %-40s %-30s %-20s %-30s %-20s %-20s%n",
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
    }
    
    public void executeQueueSystemPriority() {
        int taskCount = 1; // Initialize the task count
        long currentTime = 0; // Keep track of the current time in nanoseconds
        long totalResponseTime = 0; // Total response time for all tasks
        long totalTurnaroundTime = 0; // Total turnaround time for all tasks

        // Print header for the table
        System.out.printf("%-5s %-40s %-30s %-20s %-30s %-20s %-20s%n",
                "Count",
                "Task",
                "Started Time",
                "Response Time",
                "Completed Time",
                "Turnaround Time",
                "Execution Time");

        // Sort tasks by priority (you need to implement a method to sort tasks by priority)
        sortTasksByPriority();

        while (!queueSystem.isEmpty()) {
            Task currentTask = queueSystem.dequeue();
            long executionTime = Task.calculateExecutionTime(currentTask);

            // Calculate and print the response and turnaround times for the current task
            long responseTime = currentTime / 1000; // Convert nanoseconds to microseconds
            long turnaroundTime = (currentTime + executionTime) / 1000; // Convert nanoseconds to microseconds
            System.out.printf("%-5d %-40s %-30s %-20s %-30s %-20s %-20s%n",
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
        long averageResponseTime = totalResponseTime / queueSystem.getSize();
        long averageTurnaroundTime = totalTurnaroundTime / queueSystem.getSize();
        System.out.printf("Average Response Time: %-20s%n", averageResponseTime + " microseconds");
        System.out.printf("Average Turnaround Time: %-20s%n", averageTurnaroundTime + " microseconds");
    }
    private void sortTasksByPriority() {
        int n = queueSystem.getSize();
        MyQueue<Task> tempQueue = new MyQueue<>(n);

        // Copy tasks to a temporary queue
        while (!queueSystem.isEmpty()) {
            tempQueue.enqueue(queueSystem.dequeue());
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
                    // Print statements to check the sorting process
            System.out.println("Selected Task: " + minTask);
            System.out.println("Current Queue: " + tempQueue);
            // Enqueue the sorted task into the original queue
            queueSystem.enqueue(minTask);
        }

        // Enqueue the remaining tasks back to the original queue
        while (!tempQueue.isEmpty()) {
            queueSystem.enqueue(tempQueue.dequeue());
        }
    }
    // Method to execute tasks using LinkedList scheduling system
    public void executeLinkedListSystem() {
        int taskCount = 1; // Initialize the task count
        long currentTime = 0; // Keep track of the current time in nanoseconds
        long totalResponseTime = 0; // Total response time for all tasks
        long totalTurnaroundTime = 0; // Total turnaround time for all tasks
        
         // Print header for the table
        System.out.printf("%-5s %-40s %-30s %-20s %-30s %-20s %-20s%n",
                "Count",
                "Task",
                "Started Time",
                "Response Time",
                "Completed Time",
                "Turnaround Time",
                "Execution Time");
        
        while(linkedListSystem.getSize() > 0){
            Task currentTask = linkedListSystem.removeFirst();
            long executionTime = calculateExecutionTime(currentTask);

            // Calculate and print the response and turnaround times for the current task
            long responseTime = currentTime / 1000; // Convert nanoseconds to microseconds
            long turnaroundTime = (currentTime + executionTime) / 1000; // Convert nanoseconds to microseconds
            System.out.printf("%-5d %-40s %-30s %-20s %-30s %-20s %-20s%n",
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

    // Method to execute tasks using Stack scheduling system
    public void executeStackSystem() {
        // Implement the execution logic using Stack (LIFO)
    }
    
}


