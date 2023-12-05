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

public class LinkedListScheduler {

    private List<Task> list = new LinkedList<>();
    private int time = 0;

    public void execute(Task task) {
        list.add(task);
        time += task.getExecutionTime();
    }

    public void printReport() {
        int averageResponseTime = time / list.size();
        int averageTurnaroundTime = time / list.size();
        System.out.println("LinkedListScheduler average response time: " + averageResponseTime);
        System.out.println("LinkedListScheduler average turnaround time: " + averageTurnaroundTime);
    }
}
