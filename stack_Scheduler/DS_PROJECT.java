/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.stack_scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Stack_scheduler {
    private  MyStack<Task> SystemC;
    private StarterPack sp;
    private MyStack resulttt;
    private int currentTime; // Track current time for response and turnaround time calculations
    private int totalResponseTime; // Total response time for calculating average
    private int totalTurnaroundTime; // Total turnaround time for calculating average    
    
    Stack_scheduler() {
        SystemC = new MyStack<>(30);
        resulttt = new MyStack(30);
        sp = new StarterPack();
        currentTime = 0;
        totalResponseTime = 0;
        totalTurnaroundTime = 0;        
    } 
     void readText(String txt){
        try {
            Scanner scanner = new Scanner(new File(txt));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                
            String[] parts = line.split(" ");

            // Add a check to ensure the array has the expected number of elements
            if (parts.length == 3) {
                String method = parts[0];
                String variable = parts[1];
                String value = parts[2];
                Task task = new Task(method, variable, value);

                // Calculate response time and update current time
                int responseTime = currentTime;
                currentTime++;
                task.setResponseTime(responseTime);

                SystemC.push(task);
            } else {
                // Log an error or handle the case where the line does not have the expected format
                System.err.println("Invalid line format: " + line);
            }
        }
        scanner.close();
        System.out.println(SystemC.toString());
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Stack_scheduler.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     
     void executeStackSystem(){
        int taskCount = 1; // Initialize the task count
        long currentTime = 0; // Keep track of the current time in nanoseconds
        long totalResponseTime = 0; // Total response time for all tasks
        long totalTurnaroundTime = 0; // Total turnaround time for all tasks 
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
        
         while(!SystemC.isEmpty()){
             Task current = SystemC.pop();
             long executionTime = calculateExecutionTime(current);
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
         System.out.println(resulttt.toString());
         
     }
    public long calculateExecutionTime(Task task) {
        long startTime = System.nanoTime();
        // Execute the task 
        method(task);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000; // Return execution time in microseconds
    } 
    
    void method(Task task){
         if(task.getMethod().equals("isPrime")){
             int integer = Integer.parseInt(task.getValue());
             boolean result = sp.isPrime(integer);
             resulttt.push(result);
         }
         else if(task.getMethod().equals("fib")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.fib(integer);
             resulttt.push(result);
         }
         else if(task.getMethod().equals("longestPalSubstr")){
             String str = task.getValue();
             String result = sp.longestPalSubstr(str);
             resulttt.push(result);
         }
         else if(task.getMethod().equals("sumOfDigitsFrom1ToN")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.sumOfDigitsFrom1ToN(integer);
             resulttt.push(result);
         }
         else if(task.getMethod().equals("sumOfDigits")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.sumOfDigits(integer);  
             resulttt.push(result);
         }
         else if(task.getMethod().equals("isUgly")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.isUgly(integer);         
            resulttt.push(result);
         }
         else if(task.getMethod().equals("getNthUglyNo")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.getNthUglyNo(integer);      
             resulttt.push(result);
         }         
     }
     
    public static void main(String[] args) {
        Stack_scheduler dp= new Stack_scheduler();
        dp.readText("C://Users//User//Downloads//scheduler project//tasks.txt");
        dp.executeStackSystem();
    }
}
