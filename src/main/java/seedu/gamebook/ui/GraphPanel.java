package seedu.gamebook.ui;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.gamebook.model.ModelManager;
import seedu.gamebook.model.gameentry.GameEntry;
import seedu.gamebook.model.stats.Average;

public class GraphPanel extends UiPart<Region> {
    private static final String FXML = "GraphPanel.fxml";

    @FXML
    private LineChart<String, Number> lineChart;
    private ObservableList<GameEntry> gameEntryList;
    private XYChart.Series<String, Number> series;
    private TreeMap<String, Double> averageProfits = new TreeMap<>();

    /**
     * Constructor used to initialise the GraphPanel and a given GameEntry List
     *
     * @param gameEntryList
     */
    public GraphPanel(ObservableList<GameEntry> gameEntryList) {
        super(FXML);
        requireNonNull(gameEntryList);
        lineChart.setTitle(String.format("Average Profit per Date over the Latest %s Dates",
                ModelManager.NUMBER_OF_DATES_TO_PLOT));
        series = new XYChart.Series<>();
        series.setName("Average Profit per Date");
        lineChart.setAnimated(false);
        this.gameEntryList = gameEntryList;
    }

    /**
     * Resets the series and panel, and draws a graph of the latest k dates using the provided TreeMap
     * @param k The number of dates to be plotted.
     */
    public void drawGraphOfLatestKDates(int k) {
        assert k >= 0;

        averageProfits = Average.getAverageData(gameEntryList);
        lineChart.getData().clear();
        lineChart.getData().add(series);
        series.getData().clear();
        fillXYSeriesWithLastKDataPoints(series, averageProfits, k);
    }

    /**
     * Fills an XYChartSeries with only the last k data points in dataPoints provided.
     * @param series The XYChart.Series to be filled
     * @param dataPoints A SortedMap object encapsulating the dataPoints
     * @param k An integer, k. Only the last k data points in dataPoints will be used to fill series
     */
    private void fillXYSeriesWithLastKDataPoints(XYChart.Series<String, Number> series,
                                               SortedMap<String, Double> dataPoints, int k) {
        int numDataPoints = dataPoints.size();
        int startIndex = numDataPoints - k + 1;
        int counter = 1;

        for (Map.Entry<String, Double> dataPoint : dataPoints.entrySet()) {
            if (counter < startIndex) {
                counter++;
                continue;
            }
            series.getData().add(new XYChart.Data<>(dataPoint.getKey(), dataPoint.getValue()));
            counter++;
        }
    }

    /**
     * Resets the value of the gameList after every command and calls draw graph
     *
     * @param gameList
     */
    public void updateGameEntryList(ObservableList<GameEntry> gameList) {
        requireNonNull(gameList);
        this.gameEntryList = gameList;
        this.drawGraphOfLatestKDates(ModelManager.NUMBER_OF_DATES_TO_PLOT);
    }

}

