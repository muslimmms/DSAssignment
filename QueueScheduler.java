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

public class QueueScheduler {

    private Queue<Task> queue = new LinkedList<>();
    private int time = 0;

    public void execute(Task task) {
        queue.add(task);
        time += task.getExecutionTime();
    }

    public void printReport() {
        int averageResponseTime = time / queue.size();
        int averageTurnaroundTime = time / queue.size();
        System.out.println("QueueScheduler average response time: " + averageResponseTime);
        System.out.println("QueueScheduler average turnaround time: " + averageTurnaroundTime);
    }
}
