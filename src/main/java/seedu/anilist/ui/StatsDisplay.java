package seedu.anilist.ui;

import static seedu.anilist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.model.stats.Stats;


/**
 * The UI component responsible for displaying the statistics breakdown of animes in the anime list.
 */
public class StatsDisplay extends UiPart<Stage> {
    private static final String PIE_CHART_ID = "pieChart";
    private static final String PIE_CHART_EMPTY_ID = "pieChartEmpty";
    private static final String BAR_CHART_ID = "barChart";
    private static final String BAR_CHART_EMPTY_ID = "barChartEmpty";

    private static final String TOTAL_ANIMES_MSG = "You have %d anime(s) in AniList!";
    private static final String NO_ANIMES_MSG = "You haven't added any animes...";
    private static final String NUM_ANIMES_WATCHING_MSG = "Watching (%d)";
    private static final String NUM_ANIMES_TOWATCH_MSG = "To Watch (%d)";
    private static final String NUM_ANIMES_FINISHED_MSG = "Finished (%d)";
    private static final String GENRES_MSG = "You have tagged animes with %d unique genre(s) in total.\n"
                                            + "Here are your top anime genres.";
    private static final String NO_GENRES_TO_SHOW_MSG = "You haven't tagged any animes with genres yet.";

    private static final Logger logger = LogsCenter.getLogger(StatsDisplay.class);
    private static final String FXML = "StatsDisplay.fxml";

    private Runnable onStatsExit;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<Number, String> barChart;

    @FXML
    private StackPane statusbarPlaceholder;

    class IntegerStringConverter extends StringConverter<Number> {
        private final double epsilon = 1E-3;

        public IntegerStringConverter() {}

        @Override
        public String toString(Number object) {
            String tickLabel = "";
            //handle error where integer values do not show up due to rounding errors
            if (object.intValue() - epsilon <= object.doubleValue()
                    && object.intValue() + epsilon >= object.doubleValue()) {
                tickLabel += object.intValue();
            } else if (object.intValue() - epsilon + 1 <= object.doubleValue()
                    && object.intValue() + epsilon + 1 >= object.doubleValue()) {
                tickLabel += (object.intValue() + 1);
            }
            return tickLabel;
        }

        @Override
        public Number fromString(String string) {
            Number val = Double.parseDouble(string);
            return val.intValue();
        }
    }

    /**
     * Creates a StatsDisplay UI component.
     */
    public StatsDisplay(Stage root) {
        super(FXML, root);
        pieChart.setLabelsVisible(false);
        pieChart.setLegendSide(Side.RIGHT);
        getRoot().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                onStatsExit.run();
            }
        });

        KeyCombination closeStatsDisplayHotKey = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
        Runnable closeStatsDisplayRunnable = () -> {
            this.hide();
            onStatsExit.run();
        };

        getRoot().getScene().getAccelerators().put(closeStatsDisplayHotKey, closeStatsDisplayRunnable);
    }

    /**
     * Creates a new StatsDisplay.
     */
    public StatsDisplay() {
        this(new Stage());
    }

    public void setStatsCloseCommand(Runnable onStatsExit) {
        this.onStatsExit = onStatsExit;
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
        if (stats.getTotalAnimesCount() == 0) {
            pieChart.setId(PIE_CHART_EMPTY_ID);
            pieChart.setTitle(NO_ANIMES_MSG);
            return;
        }
        pieChart.setId(PIE_CHART_ID);
        pieChart.setTitle(String.format(TOTAL_ANIMES_MSG, stats.getTotalAnimesCount()));

        addToPieChart(NUM_ANIMES_WATCHING_MSG, stats.getWatchingCount());
        addToPieChart(NUM_ANIMES_TOWATCH_MSG, stats.getToWatchCount());
        addToPieChart(NUM_ANIMES_FINISHED_MSG, stats.getFinishedCount());
    }

    private void setGenreStats(HashMap<Genre, Integer> genreStats, int uniqueGenresCount) {
        barChart.getData().clear();
        barChart.layout();

        if (genreStats.isEmpty()) {
            barChart.setId(BAR_CHART_EMPTY_ID);
            barChart.getXAxis().setOpacity(0);
            barChart.setTitle(NO_GENRES_TO_SHOW_MSG);
            return;
        }

        CategoryAxis yAxis = new CategoryAxis();
        NumberAxis xAxis = new NumberAxis();

        BarChart<Number, String> tempGenreStats = new BarChart<>(xAxis, yAxis);
        tempGenreStats.getData().add(getBarChartData(genreStats));
        barChart.setData(tempGenreStats.getData());
        barChart.getYAxis().setTickMarkVisible(false);

        //set horizontal labels to be integer values
        NumberAxis numberAxis = (NumberAxis) barChart.getXAxis();
        numberAxis.setTickLabelFormatter(new IntegerStringConverter());

        numberAxis.setOpacity(1);
        numberAxis.setTickMarkVisible(false);

        barChart.setId(BAR_CHART_ID);
        barChart.setTitle(String.format(GENRES_MSG, uniqueGenresCount));
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
