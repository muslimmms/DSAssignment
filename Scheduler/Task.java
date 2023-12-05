/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
public class Task {

    private String name;
    private String priority;
    private int executionTime;

    public Task(String name, String priority, int executionTime) {
        this.name = name;
        this.priority = priority;
        this.executionTime = executionTime;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public int getExecutionTime() {
        return executionTime;
    }
}
