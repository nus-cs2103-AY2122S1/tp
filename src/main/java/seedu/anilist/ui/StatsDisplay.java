package seedu.anilist.ui;

import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import java.awt.datatransfer.Clipboard;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.anilist.commons.core.LogsCenter;


/**
 * The UI component responsible for displaying the statistics breakdown of animes in the anime list.
 */
public class StatsDisplay extends UiPart<Stage> {
    private static final String TOTAL_ANIMES_MSG = "You have %d anime(s) in AniList!";
    private static final String NUM_ANIMES_WATCHING_MSG = "Watching (%d)";
    private static final String NUM_ANIMES_TOWATCH_MSG  = "To Watch (%d)";
    private static final String NUM_ANIMES_FINISHED_MSG = "Finished (%d)";

    private static final Logger logger = LogsCenter.getLogger(StatsDisplay.class);
    //TODO add more stats eg top genres
    private static final String FXML = "StatsDisplay.fxml";
//
//    @FXML
//    private PieChart pieChart;

//    @FXML
//    private Button copyButton;
//
//    @FXML
//    private Label helpMessage;

    /**
     * Creates a StatsDisplay UI component.
     */
    public StatsDisplay(Stage root) {
        super(FXML, root);
//        pieChart.setLabelsVisible(false);
//        //pieChart.setAnimated(false);
//        pieChart.setLegendSide(Side.RIGHT);
        //helpMessage.setText("TESTTESTTEST");
    }

    /**
     * Creates a new StatsDisplay.
     */
    public StatsDisplay() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing stats display window.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    public void setAnimeListStats(int totalAnimesCount, int watchingCount, int toWatchCount, int finishedCount) {
//        pieChart.getData().clear();
//        requireAllNonNull(totalAnimesCount, toWatchCount, watchingCount, finishedCount);
//        pieChart.setTitle(String.format(TOTAL_ANIMES_MSG, totalAnimesCount));

        addToPieChart(NUM_ANIMES_WATCHING_MSG, watchingCount);
        addToPieChart(NUM_ANIMES_TOWATCH_MSG, toWatchCount);
        addToPieChart(NUM_ANIMES_FINISHED_MSG, finishedCount);
    }


    private void addToPieChart(String displayMsg, int count) {
        //pieChart.getData().add(new PieChart.Data(String.format(displayMsg, count), count));
    }
}
