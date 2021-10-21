package seedu.anilist.ui;

import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;


/**
 * The UI component responsible for displaying the statistics breakdown of animes in the anime list.
 */
public class StatsDisplay extends UiPart<AnchorPane> {
    private static final String FXML = "SummaryDisplay.fxml";
    private static final String TOTAL_ANIMES_MSG = "Total Animes in anime list %d";
    private static final String NUM_ANIMES_WATCHING_MSG = "Watching: %d";
    private static final String NUM_ANIMES_TOWATCH_MSG  = "To Watch: %d";
    private static final String NUM_ANIMES_FINISHED_MSG = "Finished: %d";

    @FXML
    private PieChart pieChart;

    /**
     * Creates a StatsDisplay UI component.
     */
    public StatsDisplay() {
        super(FXML);
        pieChart.setAnimated(false);
        pieChart.setLabelsVisible(false);
        pieChart.setLegendSide(Side.LEFT);
    }

    private void addToPieChart(String formatString, long count) {
        pieChart.getData().add(new PieChart.Data(String.format(formatString, count), count));
    }

    public void setAnimeListStats(int totalAnimesCount, int watchingCount, int toWatchCount, int finishedCount) {
        requireAllNonNull(totalAnimesCount, toWatchCount, watchingCount, finishedCount);
        pieChart.setTitle(String.format(TOTAL_ANIMES_MSG, totalAnimesCount));

        pieChart.getData().clear();

        addToPieChart(NUM_ANIMES_WATCHING_MSG, watchingCount);
        addToPieChart(NUM_ANIMES_TOWATCH_MSG, toWatchCount);
        addToPieChart(NUM_ANIMES_FINISHED_MSG, finishedCount);
    }
}
