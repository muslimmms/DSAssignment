package Scheduler;

public class Main {
    public static void main(String[] args) {
        SchedulingSystems schedulingSystems = new SchedulingSystems();
        schedulingSystems.loadTasks("tasks.txt");

        // Execute regular queue system
        schedulingSystems.executeQueueSystem();

        // Execute priority queue system
        schedulingSystems.executeQueueSystemPriority();

        // Execute linked list system
        schedulingSystems.executeLinkedListSystem();

        // Execute stack system
        schedulingSystems.executeStackSystem();

        // Display total times for each system
        schedulingSystems.displayTotalTimes();
    }
}
