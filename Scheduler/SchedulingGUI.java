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

        add(loadTasksButton);
        add(executeQueueButton);
        add(executeLinkedListButton);
        add(executeStackButton);
        add(executeQueuePriorityButton);

        loadTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTasks();
            }
        });

       executeQueueButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//        if (!systemsExecuted) {
            // Execute the queue system and display results
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
         showGraphButton = new JButton("Show Graph");
        add(showGraphButton);

        showGraphButton.addActionListener(e -> showGraph());

        pack();
        setLocationRelativeTo(null);
    }
   private void showGraph() {
       
    // Check if all systems have been executed
    if (!queueExecuted || !linkedListExecuted || !stackExecuted || !queuePriorityExecuted) {
        JOptionPane.showMessageDialog(this, "Please execute all systems before showing the graph.");
        return;
    }

    // Fetch the results from the previous executions
    Object[][] queueResults = schedulingSystems.getQueueResults();
    Object[][] linkedListResults = schedulingSystems.getLinkedListResults();
    Object[][] stackResults = schedulingSystems.getStackResults();
    Object[][] queuePriorityResults = schedulingSystems.getQueuePriorityResults();

    // Create datasets for JFreeChart
    CategoryDataset dataset1 = createDataset(queueResults, linkedListResults, stackResults, queuePriorityResults, false);
    CategoryDataset dataset2 = createDataset(queueResults, linkedListResults, stackResults, queuePriorityResults, true);

    // Create and display the first chart
    JFreeChart chart1 = createBarChart(dataset1, "Average Response and Turnaround Times", "Data Structures", "Time (microseconds)");
    displayChart(chart1, "Average Times Comparison");

    // Create and display the second chart
    JFreeChart chart2 = createBarChart(dataset2, "Average Response and Turnaround Times (Averages)", "Data Structures", "Time (microseconds)");
    displayChart(chart2, "Average Times Averages");

    // Determine the fastest data structure and show it in a JOptionPane
    String fastestStructure = determineFastestStructure(queueResults, linkedListResults, stackResults, queuePriorityResults);
    JOptionPane.showMessageDialog(this, "The fastest data structure is: " + fastestStructure);
    
}
   
    private CategoryDataset createDataset(Object[][] queueResults, Object[][] linkedListResults, Object[][] stackResults, Object[][] queuePriorityResults, boolean isAverage) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Add data points to the dataset
    addDataToDataset(dataset, queueResults, "Queue", isAverage);
    addDataToDataset(dataset, linkedListResults, "Linked List", isAverage);
    addDataToDataset(dataset, stackResults, "Stack", isAverage);
    addDataToDataset(dataset, queuePriorityResults, "Priority Queue", isAverage);

    return dataset;
}


    // Determine the fastest data structure based on average times
    private String determineFastestStructure(Object[][] queueResults, Object[][] linkedListResults, Object[][] stackResults, Object[][] queuePriorityResults) {
        // Determine the fastest data structure based on average times
        long avgQueueResponseTime = schedulingSystems.getAverageResponseTime(queueResults);
        long avgLinkedListResponseTime = schedulingSystems.getAverageResponseTime(linkedListResults);
        long avgStackResponseTime = schedulingSystems.getAverageResponseTime(stackResults);
        long avgPriorityQueueResponseTime = schedulingSystems.getAverageResponseTime(queuePriorityResults);

        // Find the minimum response time
        long minResponseTime = Math.min(avgQueueResponseTime,
                Math.min(avgLinkedListResponseTime,
                        Math.min(avgStackResponseTime, avgPriorityQueueResponseTime)));

        // Determine the fastest structure based on the minimum response time
        if (minResponseTime == avgQueueResponseTime) {
            return "Queue";
        } else if (minResponseTime == avgLinkedListResponseTime) {
            return "LinkedList";
        } else if (minResponseTime == avgStackResponseTime) {
            return "Stack";
        } else {
            return "PriorityQueue";
        }
    }
    
     private void addDataToDataset(DefaultCategoryDataset dataset, Object[][] results, String structureName, boolean isAverage) {
        for (Object[] result : results) {
        // Assuming the result array has [dataStructure, averageTime] format
        String dataStructure = (String) result[0];
        long averageTime;

        // Use different method based on isAverage flag
        if (isAverage) {
            averageTime = schedulingSystems.getAverageResponseTime(results);
        } else {
            if (result[1] instanceof String) {
                averageTime = parseTime((String) result[1]);
            } else if (result[1] instanceof Integer) {
                averageTime = (Integer) result[1];
            } else {
                // Handle other cases or throw an exception based on your requirements
                throw new IllegalArgumentException("Unsupported time format: " + result[1].getClass());
            }
        }

        dataset.addValue(averageTime, structureName, dataStructure);
    }
}
     
     private JFreeChart createBarChart(CategoryDataset dataset, String title, String xAxisLabel, String yAxisLabel) {
        return ChartFactory.createBarChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    private void displayChart(JFreeChart chart, String chartTitle) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        JFrame chartFrame = new JFrame(chartTitle);
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setLayout(new BorderLayout());
        chartFrame.add(chartPanel, BorderLayout.CENTER);
        chartFrame.pack();
        chartFrame.setLocationRelativeTo(this);
        chartFrame.setVisible(true);
    }

    private long parseTime(Object timeObject) {
    if (timeObject instanceof String) {
        String timeString = (String) timeObject;
        return Long.parseLong(timeString.split(" ")[0]);
    } else if (timeObject instanceof Integer) {
        return (Integer) timeObject;
    } else {
        // Handle other cases or throw an exception based on your requirements
        throw new IllegalArgumentException("Unsupported time format: " + timeObject.getClass());
    }
}

    private void loadTasks() {
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(this);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        schedulingSystems.loadTasks(filePath);
        JOptionPane.showMessageDialog(this, "Tasks loaded successfully!");

        // Reset execution flags and enable execution buttons
        queueExecuted = false;
        linkedListExecuted = false;
        stackExecuted = false;
        queuePriorityExecuted = false;
        enableExecutionButtons(true);
    }
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
