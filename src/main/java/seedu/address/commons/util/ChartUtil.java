package seedu.address.commons.util;

import java.util.Map;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Creates JavaFX charts
 */
public class ChartUtil {
    /**
     * Creates a JavaFX BarChart with the given title, axis labels and data points.
     */
    public static BarChart<String, Number> createBarChart(String title, String xLabel, String yLabel,
                                                          Map<String, Number> data) {
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);

        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle(title);
        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Number> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(series);

        return barChart;
    }
}
