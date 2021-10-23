package seedu.anilist.ui;

import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;


/**
 * The UI component responsible for displaying the statistics breakdown of animes in the anime list.
 */
public class StatsDisplay extends UiPart<AnchorPane> {
    //TODO add more stats eg top genres
    private static final String FXML = "StatsDisplay.fxml";
    private static final String TOTAL_ANIMES_MSG = "You have %d anime(s) in AniList!";
    private static final String NUM_ANIMES_WATCHING_MSG = "Watching (%d)";
    private static final String NUM_ANIMES_TOWATCH_MSG  = "To Watch (%d)";
    private static final String NUM_ANIMES_FINISHED_MSG = "Finished (%d)";

    @FXML
    private PieChart pieChart;

    /**
     * Creates a StatsDisplay UI component.
     */
    public StatsDisplay() {
        super(FXML);
        pieChart.setLabelsVisible(false);
        //pieChart.setAnimated(false);
        pieChart.setLegendSide(Side.RIGHT);
    }

    public void setAnimeListStats(int totalAnimesCount, int watchingCount, int toWatchCount, int finishedCount) {
        pieChart.getData().clear();
        requireAllNonNull(totalAnimesCount, toWatchCount, watchingCount, finishedCount);
        pieChart.setTitle(String.format(TOTAL_ANIMES_MSG, totalAnimesCount));

        addToPieChart(NUM_ANIMES_WATCHING_MSG, watchingCount);
        addToPieChart(NUM_ANIMES_TOWATCH_MSG, toWatchCount);
        addToPieChart(NUM_ANIMES_FINISHED_MSG, finishedCount);
    }


    private void addToPieChart(String displayMsg, int count) {
        pieChart.getData().add(new PieChart.Data(String.format(displayMsg, count), count));
    }
}
