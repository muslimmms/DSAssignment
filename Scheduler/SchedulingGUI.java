/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author Darwi
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SchedulingGUI extends JFrame {
    private final SchedulingSystemsGUI schedulingSystems;

    private JButton loadTasksButton;
    private JButton executeQueueButton;
    private JButton executeLinkedListButton;
    private JButton executeStackButton;
    private JButton executeQueuePriorityButton;
    private JButton showGraphButton;  // New button for showing the graph
    private JButton showTotalTimesButton; // New button

    
     // Add a flag to check if systems have been executed
    private boolean systemsExecuted = false;
    private boolean queueExecuted = false;
    private boolean linkedListExecuted = false;
    private boolean stackExecuted = false;
    private boolean queuePriorityExecuted = false;
    
    public SchedulingGUI() {
        schedulingSystems = new SchedulingSystemsGUI();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scheduling Systems");
        setLayout(new FlowLayout());

        loadTasksButton = new JButton("Load Tasks");
        executeQueueButton = new JButton("Execute Queue System");
        executeLinkedListButton = new JButton("Execute Linked List System");
        executeStackButton = new JButton("Execute Stack System");
        executeQueuePriorityButton = new JButton("Execute Priority Queue System");
        showGraphButton = new JButton("Show Graph");
        showTotalTimesButton = new JButton("Show Total Times"); // New button

        add(loadTasksButton);
        add(executeQueueButton);
        add(executeLinkedListButton);
        add(executeStackButton);
        add(executeQueuePriorityButton);
        add(showGraphButton);
        add(showTotalTimesButton); // Adding new button

        loadTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTasks();
            }
        });

       executeQueueButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
       
            queueExecuted = true;
            executeQueueButton.setEnabled(false);
            Object[][] queueResults = schedulingSystems.getQueueResults();
            long avgResponseTime = schedulingSystems.getAverageResponseTime(queueResults);
            long avgTurnaroundTime = schedulingSystems.getAverageTurnaroundTime(queueResults);
            displayResults("Queue System", queueResults, avgResponseTime, avgTurnaroundTime);
//            systemsExecuted = true;
//        }
    }
});

        executeLinkedListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(!systemsExecuted){
                // Execute the queue system and display results
                linkedListExecuted = true;
                executeLinkedListButton.setEnabled(false);    
                Object[][] LLResults = schedulingSystems.getLinkedListResults();
                long avgResponseTime = schedulingSystems.getAverageResponseTime(LLResults);
                long avgTurnaroundTime = schedulingSystems.getAverageTurnaroundTime(LLResults);
                displayResults("Linked List System", LLResults, avgResponseTime, avgTurnaroundTime);
//                systemsExecuted = true;
//            }
            }
        });

        executeStackButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            if(!systemsExecuted){
            stackExecuted = true;
            executeStackButton.setEnabled(false);
            Object[][] stackResults = schedulingSystems.getStackResults();
            long avgResponseTime = schedulingSystems.getAverageResponseTime(stackResults);
            long avgTurnaroundTime = schedulingSystems.getAverageTurnaroundTime(stackResults);
            displayResults("Stack System", stackResults, avgResponseTime, avgTurnaroundTime);
//            systemsExecuted = true;
//            }
            }
        });

        executeQueuePriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(!systemsExecuted){
                queuePriorityExecuted = true;
                executeQueuePriorityButton.setEnabled(false);
                Object[][] PQResults = schedulingSystems.getQueuePriorityResults();
                long avgResponseTime = schedulingSystems.getAverageResponseTime(PQResults);
                long avgTurnaroundTime = schedulingSystems.getAverageTurnaroundTime(PQResults);
                displayResults("Priority Queue System", PQResults, avgResponseTime, avgTurnaroundTime);
//                systemsExecuted = true;
//            }
            }
        });
        

        showGraphButton.addActionListener(e -> showGraph());
        showTotalTimesButton.addActionListener(e -> showTotalTimes()); // Action for new button

        pack();
        setLocationRelativeTo(null);
    }
    
  private void showGraph() {
    // Ensure all systems have been executed before showing the graph
    if (!(queueExecuted && linkedListExecuted && stackExecuted && queuePriorityExecuted)) {
        JOptionPane.showMessageDialog(this, "Please execute all systems before showing the graph.");
        return;
    }

    // Create and display the graph
    CategoryDataset dataset = createDataset();
    JFreeChart chart = ChartFactory.createBarChart(
        "Total Execution Times",
        "Scheduling Systems",
        "Time (ms or relative)", // Update label as appropriate
        dataset,
        PlotOrientation.VERTICAL,
        true, true, false);

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(800, 600));
    JFrame chartFrame = new JFrame("Execution Times Comparison");
    chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    chartFrame.add(chartPanel);
    chartFrame.pack();
    chartFrame.setLocationRelativeTo(null); // Center the frame
    chartFrame.setVisible(true);
}
   
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Normalize data (e.g., relative to the largest value)
        double maxTime = Math.max(
            Math.max(schedulingSystems.getTotalQueueSystemTime(), schedulingSystems.getTotalPriorityQueueSystemTime()),
            Math.max(schedulingSystems.getTotalLinkedListSystemTime(), schedulingSystems.getTotalStackSystemTime())
        );

        dataset.addValue(schedulingSystems.getTotalQueueSystemTime() / maxTime, "Queue", "Relative Execution Time");
        dataset.addValue(schedulingSystems.getTotalPriorityQueueSystemTime() / maxTime, "Priority Queue", "Relative Execution Time");
        dataset.addValue(schedulingSystems.getTotalLinkedListSystemTime() / maxTime, "Linked List", "Relative Execution Time");
        dataset.addValue(schedulingSystems.getTotalStackSystemTime() / maxTime, "Stack", "Relative Execution Time");

        return dataset;
}
    
   private void showTotalTimes() {
    // Ensure all systems have been executed before showing total times
    if (!(queueExecuted && linkedListExecuted && stackExecuted && queuePriorityExecuted)) {
        JOptionPane.showMessageDialog(this, "Please execute all systems before showing total times.");
        return;
    }

    JFrame totalTimesFrame = new JFrame("Total Times and Fastest System");
    totalTimesFrame.setLayout(new BorderLayout());
    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);

    // Retrieve total times and fastest system from schedulingSystems
    String text = schedulingSystems.getTotalTimesAndFastestSystem();

    textArea.setText(text);
    totalTimesFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
    totalTimesFrame.pack();
    totalTimesFrame.setLocationRelativeTo(null); // Center the frame
    totalTimesFrame.setVisible(true);
}

   private void loadTasks() {
    // Specify the file path directly or use any logic to determine the file path
    String filePath = "C:\\Users\\Darwi\\OneDrive - Universiti Malaya\\Desktop\\tasks.txt";  // Replace this with the actual file path

    // Load tasks
    schedulingSystems.loadTasks(filePath);
    JOptionPane.showMessageDialog(this, "Tasks loaded successfully!");

    // Reset execution flags and enable execution buttos
    queueExecuted = false;
    linkedListExecuted = false;
    stackExecuted = false;
    queuePriorityExecuted = false;
    enableExecutionButtons(true);
}

   private void displayResults(String title, Object[][] data, long avgResponseTime, long avgTurnaroundTime) {
       
    DefaultTableModel tableModel = new DefaultTableModel(data, schedulingSystems.getColumnNames());
    JTable table = new JTable(tableModel);

    JFrame resultFrame = new JFrame(title);
    resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    resultFrame.setLayout(new BorderLayout());
    resultFrame.add(new JScrollPane(table), BorderLayout.CENTER);

    // Create a panel for average response and turnaround times
    JPanel avgPanel = new JPanel();
    avgPanel.setLayout(new GridLayout(2, 1));
    avgPanel.add(new JLabel("Average Response Time: " + avgResponseTime + " microseconds"));
    avgPanel.add(new JLabel("Average Turnaround Time: " + avgTurnaroundTime + " microseconds"));

    resultFrame.add(avgPanel, BorderLayout.SOUTH);

    resultFrame.setSize(800, 600);
    resultFrame.setLocationRelativeTo(this);
    resultFrame.setVisible(true);
}
   private void executeSystems() {
    if (!systemsExecuted) {
        schedulingSystems.executeQueueSystem();
        schedulingSystems.executeLinkedListSystem();
        schedulingSystems.executeStackSystem();
        schedulingSystems.executeQueueSystemPriority();
        systemsExecuted = true;
    }
}
   
   private void enableExecutionButtons(boolean enable) {
    executeQueueButton.setEnabled(enable);
    executeLinkedListButton.setEnabled(enable);
    executeStackButton.setEnabled(enable);
    executeQueuePriorityButton.setEnabled(enable);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SchedulingGUI().setVisible(true);
            }
        });
    }
}
