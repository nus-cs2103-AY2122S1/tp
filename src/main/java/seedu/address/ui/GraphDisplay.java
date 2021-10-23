package seedu.address.ui;

import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class GraphDisplay extends UiPart<Region> {

    private static final String FXML = "GraphDisplay.fxml";

    @FXML
    private StackPane placeHolder;

    @FXML
    private TextArea resultDisplay;

    public GraphDisplay() {
        super(FXML);
    }

    public void setChart(Chart chart) {
        placeHolder.getChildren().add(chart);
    }

    public static BarChart<String, Number> createBarChart(String title, String xLabel, String yLabel,
                                                           Map<String, Number> data) {
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);

        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);

        final BarChart<String,Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle(title);
        barChart.setLegendVisible(false);

        XYChart.Series series = new XYChart.Series();
        for (Map.Entry<String, Number> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(series);

        return barChart;
    }
}
