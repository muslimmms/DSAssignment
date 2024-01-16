/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scheduler;

/**
 *
 * @author muslim
 */
public class Task<T extends Object> {
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

    // Static method to compare tasks based on execution time
    public static <T extends Object> int compareByExecutionTime(Task<T> t1, Task<T> t2) {
        long executionTime1 = calculateExecutionTime(t1);
        long executionTime2 = calculateExecutionTime(t2);
        return Long.compare(executionTime1, executionTime2);
    }

    // Static method to calculate execution time for a task
    public static <T extends Object> long calculateExecutionTime(Task<T> task) {
        long startTime = System.nanoTime();
        // Execute the task (replace this with the actual execution logic)
        executeTask(task);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000; // Return execution time in microseconds
    }

    // Static method to execute the task
    public static <T extends Object> void executeTask(Task<T> task) {
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
    }
}
