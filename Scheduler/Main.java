/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
public class Main {
        public static void main(String[] args) {
            SchedulingSystems schedulingSystems = new SchedulingSystems();
            schedulingSystems.loadTasks("C:\\Users\\muslim\\Desktop\\SEM1YEAR2\\WIA1002\\Scheduler\\src\\scheduler\\tasks.txt");
            schedulingSystems.executeQueueSystemPriority();
            schedulingSystems.executeLinkedListSystem();
            schedulingSystems.executeStackSystem();
        }
}
