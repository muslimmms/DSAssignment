/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
import java.util.*;

public class StackScheduler {

    private Stack<Task> stack = new Stack<>();
    private int time = 0;

    public void execute(Task task) {
        stack.push(task);
        time += task.getExecutionTime();
    }

    public void printReport() {
        int averageResponseTime = time / stack.size();
        int averageTurnaroundTime = time / stack.size();
        System.out.println("StackScheduler average response time: " + averageResponseTime);
        System.out.println("StackScheduler average turnaround time: " + averageTurnaroundTime);
    }
}
