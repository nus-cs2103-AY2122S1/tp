package seedu.anilist.ui;

import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.model.stats.Stats;


/**
 * The UI component responsible for displaying the statistics breakdown of animes in the anime list.
 */
public class StatsDisplay extends UiPart<Stage> {
    private static final String TOTAL_ANIMES_MSG = "You have %d anime(s) in AniList!";
    private static final String EPISODES_WATCHED_MSG = "Episode(s) watched: %d";
    private static final String NUM_ANIMES_WATCHING_MSG = "Watching (%d)";
    private static final String NUM_ANIMES_TOWATCH_MSG = "To Watch (%d)";
    private static final String NUM_ANIMES_FINISHED_MSG = "Finished (%d)";
    private static final String GENRES_MSG = "You have tagged animes with %d unique genre(s) in total.\n"
                                            + "Here are your top anime genres.";

    private static final Logger logger = LogsCenter.getLogger(StatsDisplay.class);
    private static final String FXML = "StatsDisplay.fxml";

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<Number, String> barChart;

    @FXML
    private StackPane statusbarPlaceholder;


    /**
     * Creates a StatsDisplay UI component.
     */
    public StatsDisplay(Stage root) {
        super(FXML, root);
        pieChart.setLabelsVisible(false);
        pieChart.setLegendSide(Side.RIGHT);
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

    public void setAnimeListStats(Stats stats) {
        requireAllNonNull(stats);
        setAnimeStats(stats);
        setGenreStats(stats.getTopGenres(), stats.getNumUniqueGenres());
    }

    private void setAnimeStats(Stats stats) {
        pieChart.getData().clear();
        barChart.getData().clear();
        pieChart.setTitle(String.format(TOTAL_ANIMES_MSG, stats.getTotalAnimesCount())
                + "\n" + String.format(EPISODES_WATCHED_MSG, stats.getEpisodesCount()));

        addToPieChart(NUM_ANIMES_WATCHING_MSG, stats.getWatchingCount());
        addToPieChart(NUM_ANIMES_TOWATCH_MSG, stats.getToWatchCount());
        addToPieChart(NUM_ANIMES_FINISHED_MSG, stats.getFinishedCount());
    }

    private void setGenreStats(HashMap<Genre, Integer> genreStats, int uniqueGenresCount) {
        barChart.setTitle(String.format(GENRES_MSG, uniqueGenresCount));
        CategoryAxis yAxis = new CategoryAxis();
        NumberAxis xAxis = new NumberAxis();

        //Set horizontal labelling to be integer values
        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis) {
            @Override
            public String toString(Number object) {
                return Integer.toString(Math.round(object.intValue()));
            }
        });

        BarChart<Number, String> tempGenreStats = new BarChart<>(xAxis, yAxis);
        tempGenreStats.getData().add(getBarChartData(genreStats));
        barChart.setData(tempGenreStats.getData());
        barChart.getYAxis().setTickMarkVisible(false);
        barChart.getXAxis().setTickMarkVisible(false);
    }

    /**
     * Determine top five genres based on the number of animes tagged with the genre
     * and creates a bar graph accordingly.
     *
     * @param genreStats The genre statistics
     * @return data for the bar chart featuring the top five genres
     */
    public XYChart.Series<Number, String> getBarChartData(HashMap<Genre, Integer> genreStats) {
        XYChart.Series<Number, String> barChartData = new XYChart.Series<>();
        for (Map.Entry<Genre, Integer> genreCountPair : genreStats.entrySet()) {
            String genreName = genreCountPair.getKey().genreName;
            int genreCount = genreCountPair.getValue();
            barChartData.getData().add(new XYChart.Data<>(
                    genreCount, genreName));
        }
        return barChartData;
    }

    private void addToPieChart(String displayMsg, int count) {
        pieChart.getData().add(new PieChart.Data(String.format(displayMsg, count), count));
    }
}
