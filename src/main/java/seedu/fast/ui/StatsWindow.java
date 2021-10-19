package seedu.fast.ui;

import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
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

    private Fast fast;

    @FXML
    private PieChart priorityPieChart;

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
     * Focuses on the help window.
     */
    public void focus() {
        populatePriorityPieChart();
        labelPriorityPieChart();
        getRoot().requestFocus();
    }


}
