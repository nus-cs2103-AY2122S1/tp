package seedu.fast.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.fast.commons.core.LogsCenter;
import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;
import seedu.fast.model.tag.PriorityTag;

/**
 * Controller for a stats page
 */
public class StatsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(StatsWindow.class);
    private static final String FXML = "StatsWindow.fxml";
    private static final String PRIORITY_CHART_MESSAGE_INTRO = "You currently have a total of: ";
    private static final String HIGH_PRIORITY_MESSAGE = "Good job! You have a large proportion of high value clients!\n"
        + "However, this means that you need to be sure to put in extra effort to maintain it!";
    private static final String MEDIUM_PRIORITY_MESSAGE = "Nice! You have a sizeable portion of medium value clients!\n"
        + "This is a great base for your portfolio!";
    private static final String LOW_PRIORITY_MESSAGE = "Keep it up! Focus on developing your client base to boost \n"
        + "your client portfolio!";

    public static final String MESSAGE_USAGE = "Opens a new window with built-in statistics to provide "
        + "you with an overview of your data.\n"
        + "To view the statistics, simply click the \"Stats\" menu item on the top bar or press `F2`.\n"
        + "Currently, FAST supports these statistics:\n"
        + "* Priority Tag Chart\n"
        + "* Insurance Plan Chart (Coming soon!)";

    private Fast fast;

    @FXML
    private PieChart priorityPieChart;

    @FXML
    private Label priorityPieChartDesc;

    /**
     * Creates a new StatsWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public StatsWindow(Stage root, ReadOnlyFast fast) {
        super(FXML, root);
        // Since StatsWindow receives a Fast instance, it is safe to typecast it
        this.fast = (Fast) fast;
        populatePriorityPieChart();
        labelPriorityPieChart();
        priorityPieChart.setLegendVisible(false);
    }


    /**
     * Creates a new HelpWindow.
     */
    public StatsWindow(ReadOnlyFast fast) {
        this(new Stage(), fast);
    }


    /**
     * Shows the stats window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing stats page of your clients.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the stats window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the stats window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Adds the data from Fast into the PieChart.
     */
    public void populatePriorityPieChart() {
        priorityPieChart.getData().clear();
        int highPriorityCount = this.fast.getHighPriorityCount();
        int mediumPriorityCount = this.fast.getMediumPriorityCount();
        int lowPriorityCount = this.fast.getLowPriorityCount();

        assert highPriorityCount >= 0 : "highPriorityCount must be positive";
        assert mediumPriorityCount >= 0 : "mediumPriorityCount must be positive";
        assert lowPriorityCount >= 0 : "lowPriorityCount must be positive";

        addPriorityPieChartDesc(highPriorityCount, mediumPriorityCount, lowPriorityCount);

        addPieChartData(PriorityTag.HighPriority.NAME, highPriorityCount, this.priorityPieChart);
        addPieChartData(PriorityTag.MediumPriority.NAME, mediumPriorityCount, this.priorityPieChart);
        addPieChartData(PriorityTag.LowPriority.NAME, lowPriorityCount, this.priorityPieChart);
    }

    /**
     * Adds the counts to the labels of the PieChart.
     * Adapted from: https://stackoverflow.com/questions/35479375/display-additional-values-in-pie-chart
     * Credit: jewelsea
     */
    public void labelPriorityPieChart() {
        priorityPieChart.getData().forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(
                    data.getName(), ":\n", (int) data.getPieValue(), (data.getPieValue() == 1)
                        ? " person" : " people" // check plural or singular
                )
            ));
    }

    /**
     * Adds the data elements {@code name} and {@code count} to {@code pc}
     */
    public void addPieChartData(String name, int count, PieChart pc) {
        if (count > 0) {
            PieChart.Data data = new PieChart.Data(name, count);
            pc.getData().add(data);
        }
    }

    /**
     * Adds a brief analysis of the client base to the PieChart.
     */
    public void addPriorityPieChartDesc(int highCount, int medCount, int lowCount) {
        int totalCount = highCount + medCount + lowCount;
        String totalClientCount = totalCount + " Clients!";

        // Gets the max out of 3 values
        int maxCount = Math.max(highCount, Math.max(medCount, lowCount));

        // Prioritise higher Priorities first,
        // e.g. if the counts are equal for all, HIGH_PRIORITY_MESSAGE will be used
        if (highCount == maxCount) {
            setPriorityPieChartLabel(totalClientCount, HIGH_PRIORITY_MESSAGE);
            return; //return to prevent fallthrough
        }
        if (medCount == maxCount) {
            setPriorityPieChartLabel(totalClientCount, MEDIUM_PRIORITY_MESSAGE);
            return; //return to prevent fallthrough
        }
        if (lowCount == maxCount) {
            setPriorityPieChartLabel(totalClientCount, LOW_PRIORITY_MESSAGE);
            return;
        }
        // The code should NOT reach here
        assert false;
    }

    /**
     * Sets the text of the label.
     */
    private void setPriorityPieChartLabel(String totalClientCount, String text) {
        priorityPieChartDesc.setText(PRIORITY_CHART_MESSAGE_INTRO + totalClientCount + "\n\n" + text);
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        assert getRoot() != null : "StatsWindow is not initialised";
        populatePriorityPieChart();
        labelPriorityPieChart();
        getRoot().requestFocus();
    }


}
