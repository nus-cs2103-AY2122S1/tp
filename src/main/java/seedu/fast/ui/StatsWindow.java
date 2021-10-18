package seedu.fast.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;


/**
 * Controller for a stats page
 */
public class StatsWindow extends UiPart<Stage> {

    public static final String TEST_MESSAGE = "<<Insert Stats here filler >>";

    private static final Logger logger = LogsCenter.getLogger(StatsWindow.class);
    private static final String FXML = "StatsWindow.fxml";

    private Fast fast;

    @FXML
    private Label testMessage;

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

    public void populatePriorityPieChart() {
        int highPriorityCount = this.fast.getHighPriorityCounts();
        int mediumPriorityCount = this.fast.getHighPriorityCounts();
        int lowPriorityCount = this.fast.getLowPriorityCounts();

        if (highPriorityCount > 0) {
            PieChart.Data highData = new PieChart.Data("High Priority", highPriorityCount);
            priorityPieChart.getData().add(highData);
        }
        if (mediumPriorityCount > 0) {
            PieChart.Data mediumData = new PieChart.Data("Medium Priority", mediumPriorityCount);
            priorityPieChart.getData().add(mediumData);
        }
        if (lowPriorityCount > 0) {
            PieChart.Data lowData = new PieChart.Data("Low Priority", highPriorityCount);
            priorityPieChart.getData().add(lowData);
        }
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


}
