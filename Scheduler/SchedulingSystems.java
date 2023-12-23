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
    private MyQueue<Task> queueSystem;
    private MyLinkedList<Task> linkedListSystem;
    private MyStack<Task> stackSystem;

    // Constructor to initialize the systems
    public SchedulingSystems() {
        queueSystem = new MyQueue<>(30);
        linkedListSystem = new MyLinkedList<>();
        stackSystem = new MyStack<>(30);
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

        // Calculate and print average response and turnaround times
        long averageResponseTime = totalResponseTime / queueSystem.getSize();
        long averageTurnaroundTime = totalTurnaroundTime / queueSystem.getSize();
        System.out.printf("Average Response Time: %-20s%n", averageResponseTime + " microseconds");
        System.out.printf("Average Turnaround Time: %-20s%n", averageTurnaroundTime + " microseconds");
    }


    // Method to execute tasks using LinkedList scheduling system
    public void executeLinkedListSystem() {
        // Implement the execution logic using LinkedList
    }

    // Method to execute tasks using Stack scheduling system
    public void executeStackSystem() {
        // Implement the execution logic using Stack (LIFO)
    }

    // Method to calculate average response time and turnaround time for each system
    public void calculateMetrics() {
        // Implement the logic to calculate and print metrics
    }
    private long calculateExecutionTime(Task currentTask) {
        long startTime = System.nanoTime();

        // Execute the task (replace this with the actual execution logic)
        executeTask(currentTask);

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000; // Return execution time
    }
    // Replace this method with the actual execution logic
    private void executeTask(Task task) {
        // You may call the respective method from StarterPack.java based on the task
        // For example:
        if ("isPrime".equals(task.methodName)) {
            long inputValue = Long.parseLong(task.input.toString());
            boolean result = StarterPack.isPrime(inputValue);
        } else if ("fib".equals(task.methodName)) {
            int inputValue = Integer.parseInt(task.input.toString());
            int result = StarterPack.fib(inputValue);
        } else if ("getNthUglyNo".equals(task.methodName)) {
            int inputValue = Integer.parseInt(task.input.toString());
            int result = StarterPack.getNthUglyNo(inputValue);
        } else if ("longestPalSubstr".equals(task.methodName)) {
            String result = StarterPack.longestPalSubstr(task.input.toString());
        } else if ("sumOfDigitsFrom1ToN".equals(task.methodName)) {
            int inputValue = Integer.parseInt(task.input.toString());
            int result = StarterPack.sumOfDigitsFrom1ToN(inputValue);
        }
        // Add more cases if needed based on the actual methods in StarterPack.java
    }
    // Task class to represent tasks with method name, input type, and input
    private static class Task<T extends Object> {
        String methodName;
        String inputType;
        T input;

        public Task(String methodName, String inputType, T input) {
            this.methodName = methodName;
            this.inputType = inputType;
            this.input = input;
        }
        @Override
        public String toString() {
            return methodName + " " + inputType + " " + input;
        }
    }
    private static class StarterPack {

        public static int fib(int n) {
            if (n <= 1)
                return n;
            return fib(n-1) + fib(n-2);
        }

        public static boolean isPrime(long n) {
            if (n <= 1)
                return false;

            for (int i = 2; i < n; i++) {
                if (n % i == 0)
                    return false;
            }

            return true;
        }

        public static String longestPalSubstr(String str) {
            int n = str.length();

            boolean table[][] = new boolean[n][n];

            int maxLength = 1;
            for (int i = 0; i < n; ++i)
                table[i][i] = true;

            int start = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (str.charAt(i) == str.charAt(i + 1)) {
                    table[i][i + 1] = true;
                    start = i;
                    maxLength = 2;
                }
            }

            for (int k = 3; k <= n; ++k) {
                for (int i = 0; i < n - k + 1; ++i)
                {
                    int j = i + k - 1;

                    if (table[i + 1][j - 1] && str.charAt(i) ==
                            str.charAt(j)) {
                        table[i][j] = true;

                        if (k > maxLength) {
                            start = i;
                            maxLength = k;
                        }
                    }
                }
            }
            return str.substring(start, start + maxLength);
        }

        public static int sumOfDigitsFrom1ToN(int n) {
            int result = 0;

            for (int x = 1; x <= n; x++)
                result += sumOfDigits(x);

            return result;
        }

        public static int sumOfDigits(int x) {
            int sum = 0;
            while (x != 0)
            {
                sum += x % 10;
                x   = x / 10;
            }
            return sum;
        }

        public static int maxDivide(int a, int b)
        {
            while(a % b == 0)
                a = a/b;
            return a;
        }

        public static int isUgly(int no)
        {
            no = maxDivide(no, 2);
            no = maxDivide(no, 3);
            no = maxDivide(no, 5);

            return (no == 1)? 1 : 0;
        }


        public static int getNthUglyNo(int n)
        {
            int i = 1;
            int count = 1;

            while(n > count)
            {
                i++;
                if(isUgly(i) == 1)
                    count++;
            }
            return i;
        }

    }

    
    public static void main(String[] args) {
        SchedulingSystems schedulingSystems = new SchedulingSystems();
        schedulingSystems.loadTasks("C:\\Users\\muslim\\Desktop\\SEM1YEAR2\\WIA1002\\Scheduler\\src\\scheduler\\tasks.txt");
        schedulingSystems.executeQueueSystem();
        schedulingSystems.executeLinkedListSystem();
        schedulingSystems.executeStackSystem();
        schedulingSystems.calculateMetrics();
    }
}


