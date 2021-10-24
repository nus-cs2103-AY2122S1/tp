package seedu.address.commons.util;

import java.util.Map;

import java.util.*;

import javafx.scene.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Creates JavaFX charts
 */
public class ChartUtil {
    private static final double DEFAULT_TICK_UNIT = 5.0;

    /**
     * Creates a JavaFX BarChart with the given title, axis labels and data points.
     */
    public static BarChart<String, Number> createBarChart(String title, String xLabel, String yLabel,
                                                          Map<String, Number> data) {
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);
        // Disable auto-ranging so that we can configure our own tick units
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(DEFAULT_TICK_UNIT);

        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle(title);
        barChart.setLegendVisible(false);

        double maxY = DEFAULT_TICK_UNIT;

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Number> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            maxY = Math.max(maxY, entry.getValue().doubleValue());
        }

        double yUpperBound = roundUpToNearestMultiple(maxY, (int) DEFAULT_TICK_UNIT);

        // Add another tick unit as padding, else if there is a data point having a value which is a multiple of
        // the tick unit, the graph will look as if it is cut-off.
        yUpperBound += DEFAULT_TICK_UNIT;
        yAxis.setUpperBound(yUpperBound);

        barChart.getData().add(series);
        return barChart;
    }

    /**
     * Creates a JavaFX LineChart with the given title, axis labels and data points.
     */
    public static LineChart<String, Number> createLineChart(String title, String xLabel, String yLabel,
                                                          List<Map<String, Number>> dataSet) {

        // Defining axes
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);

//        // Disable auto-ranging so that we can configure our own tick units
//        yAxis.setAutoRanging(false);
//        yAxis.setTickUnit(DEFAULT_TICK_UNIT);

        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle(title);
//        lineChart.setLegendVisible(false);

//        double maxY = DEFAULT_TICK_UNIT;

        for (Map<String, Number> data : dataSet) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Map.Entry<String, Number> entry : data.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                //            maxY = Math.max(maxY, entry.getValue().doubleValue());
            }
            lineChart.getData().add(series);
        }

//        double yUpperBound = roundUpToNearestMultiple(maxY, (int) DEFAULT_TICK_UNIT);

//        // Add another tick unit as padding, else if there is a data point having a value which is a multiple of
//        // the tick unit, the graph will look as if it is cut-off.
//        yUpperBound += DEFAULT_TICK_UNIT;
//        yAxis.setUpperBound(yUpperBound);
        return lineChart;
    }





    private static double roundUpToNearestMultiple(double val, int multiple) {
        return Math.round(val / multiple) * multiple;
    }
}
