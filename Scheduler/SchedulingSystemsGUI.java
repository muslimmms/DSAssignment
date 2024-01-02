/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author Darwi
 */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SchedulingSystemsGUI {

    // Define the structures for scheduling systems
    private final MyQueue<Task> queueSystem;
    private final MyLinkedList<Task> linkedListSystem;
    private final MyStack<Task> stackSystem;
    private MyQueue<Task> originalQueue;

    // Constructor to initialize the systems
    public SchedulingSystemsGUI() {
        queueSystem = new MyQueue<>(22);
        originalQueue = new MyQueue<>(22);
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
                
                // Populate the original queue
                originalQueue.enqueue(new Task(methodName, inputType, input));

            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    // Method to execute tasks using Queue scheduling system
    public Object[][] executeQueueSystem() {
        
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
        
        List<Object[]> dataList = new ArrayList<>();

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
            
             // Create an array to hold the data for the current task
            Object[] rowData = new Object[]{
                    taskCount,
                    currentTask,
                    currentTime,
                    responseTime + " microseconds",
                    (currentTime + executionTime) + " microseconds",
                    turnaroundTime + " microseconds",
                    executionTime + " microseconds"
            };
            
            // Add the data for the current task to the list
            dataList.add(rowData);
            
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
        
         // Convert the list to a 2D array
        return dataList.toArray(new Object[0][]);
    } 
    public Object[][] executeQueueSystemPriority() { // Save the original queue
        
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
        
        List<Object[]> dataList = new ArrayList<>();
        
        while (!originalQueue.isEmpty()) {
            Task currentTask = originalQueue.dequeue();
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
            
            // Create an array to hold the data for the current task
            Object[] rowData = new Object[]{
                    taskCount,
                    currentTask,
                    currentTime,
                    responseTime + " microseconds",
                    (currentTime + executionTime) + " microseconds",
                    turnaroundTime + " microseconds",
                    executionTime + " microseconds"
            };
            
            // Add the data for the current task to the list
            dataList.add(rowData);
            
            // Update total response and turnaround times
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;

            // Update the current time
            currentTime += executionTime;
            taskCount++;
        }

        // Calculate and print average response and turnaround times
        long avgResponseTime = totalResponseTime / taskCount;
        long avgTurnaroundTime = totalTurnaroundTime / taskCount;
        System.out.println("Average Response Time: " + avgResponseTime + " microseconds");
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime + " microseconds"); 
        
         // Convert the list to a 2D array
        return dataList.toArray(new Object[0][]);
        
    }
    private void sortTasksByPriority() {
        int n = originalQueue.getSize();
        MyQueue<Task> tempQueue = new MyQueue<>(n);

        // Copy tasks to a temporary queue
        while (!originalQueue.isEmpty()) {
            tempQueue.enqueue(originalQueue.dequeue());
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
            originalQueue.enqueue(minTask);
        }

        // Enqueue the remaining tasks back to the original queue
        while (!tempQueue.isEmpty()) {
            originalQueue.enqueue(tempQueue.dequeue());
        }
    }
    // Method to execute tasks using LinkedList scheduling system
    public Object[][] executeLinkedListSystem() {
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
        
         List<Object[]> dataList = new ArrayList<>();
        
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
            
            // Create an array to hold the data for the current task
            Object[] rowData = new Object[]{
                    taskCount,
                    currentTask,
                    currentTime,
                    responseTime + " microseconds",
                    (currentTime + executionTime) + " microseconds",
                    turnaroundTime + " microseconds",
                    executionTime + " microseconds"
            };
            
            // Add the data for the current task to the list
            dataList.add(rowData);
            
            // Update total response and turnaround times
            totalResponseTime += responseTime;
            totalTurnaroundTime += turnaroundTime;

            // Update the current time
            currentTime += executionTime;
            taskCount++;
        }
        // Calculate and print average response and turnaround times
        long avgResponseTime = totalResponseTime / taskCount;
        long avgTurnaroundTime = totalTurnaroundTime / taskCount;
        System.out.println("Average Response Time: " + avgResponseTime + " microseconds");
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime + " microseconds"); 
        
        // Convert the list to a 2D array
        return dataList.toArray(new Object[0][]);
    }
    // Method to execute tasks using Stack scheduling system
    public Object[][]  executeStackSystem() {
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
         
         List<Object[]> dataList = new ArrayList<>();
        
         while(!stackSystem.isEmpty()){
                Task currentTask = stackSystem.pop();
                long executionTime = Task.calculateExecutionTime(currentTask);
                // Calculate and print the response and turnaround times for the current task
                long responseTime = currentTime / 1000; // Convert nanoseconds to microseconds
                long turnaroundTime = (currentTime + executionTime) / 1000; // Convert nanoseconds to microseconds

                StringBuilder output = new StringBuilder();
                output.append(String.format("%-5d %-80s %-20s %-20s %-30s %-20s %-20s%n",
                taskCount, currentTask, currentTime, responseTime + " microseconds",
                    (currentTime + executionTime) + " microseconds", turnaroundTime + " microseconds",
                    executionTime + " microseconds"));

                    System.out.print(output);
                    
                // Create an array to hold the data for the current task
            Object[] rowData = new Object[]{
                    taskCount,
                    currentTask,
                    currentTime,
                    responseTime + " microseconds",
                    (currentTime + executionTime) + " microseconds",
                    turnaroundTime + " microseconds",
                    executionTime + " microseconds"
            };

            // Add the data for the current task to the list
            dataList.add(rowData);

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
        
         // Convert the list to a 2D array
        return dataList.toArray(new Object[0][]);
    }
    // Method to get column names
    public String[] getColumnNames() {
        // Replace these column names with the actual column names you want to display
        return new String[]{"Count", "Task", "Started Time", "Response Time", "Completed Time", "Turnaround Time", "Execution Time"};
    }
     // New method to get the results of the Queue system
    public Object[][] getQueueResults() {
        return executeQueueSystem();
    }

    // New method to get the results of the Priority Queue system
    public Object[][] getQueuePriorityResults() {
        return executeQueueSystemPriority();
    }

    // New method to get the results of the Linked List system
    public Object[][] getLinkedListResults() {
        return executeLinkedListSystem();
    }

    // New method to get the results of the Stack system
    public Object[][] getStackResults() {
        return executeStackSystem();
    }
    
 public long getAverageResponseTime(Object[][] data) {
        if (data.length == 0) {
            return 0;
        }

        long totalResponseTime = 0;

        for (Object[] rowData : data) {
            try {
                String responseTimeStr = (String) rowData[3];
                long responseTime = Long.parseLong(responseTimeStr.split(" ")[0]); // Extract the numeric part
                totalResponseTime += responseTime;
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails (you can print an error message, log it, or ignore it)
                System.err.println("Error parsing response time: " + e.getMessage());
            }
        }

        return totalResponseTime / data.length;
    }

    public long getAverageTurnaroundTime(Object[][] data) {
        if (data.length == 0) {
            return 0;
        }

        long totalTurnaroundTime = 0;

        for (Object[] rowData : data) {
            try {
                String turnaroundTimeStr = (String) rowData[5];
                long turnaroundTime = Long.parseLong(turnaroundTimeStr.split(" ")[0]); // Extract the numeric part
                totalTurnaroundTime += turnaroundTime;
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails (you can print an error message, log it, or ignore it)
                System.err.println("Error parsing turnaround time: " + e.getMessage());
            }
        }

        return totalTurnaroundTime / data.length;
    }
}
