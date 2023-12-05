/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Task> tasks = readTasks();
        executeTasks(tasks);
    }

    private static List<Task> readTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\muslim\\Desktop\\SEM1YEAR2\\WIA1002\\Scheduler\\src\\scheduler\\tasks.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                tasks.add(new Task(parts[0], parts[1], Integer.parseInt(parts[2])));
            }
        }
        return tasks;
    }

    private static void executeTasks(List<Task> tasks) {
        QueueScheduler schedulerA = new QueueScheduler();
        LinkedListScheduler schedulerB = new LinkedListScheduler();
        StackScheduler schedulerC = new StackScheduler();

        for (Task task : tasks) {
            schedulerA.execute(task);
            schedulerB.execute(task);
            schedulerC.execute(task);
        }

        schedulerA.printReport();
        schedulerB.printReport();
        schedulerC.printReport();
    }
}
