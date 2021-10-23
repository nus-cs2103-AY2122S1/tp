package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.chart.Chart;
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
        // Clear any existing charts
        placeHolder.getChildren().clear();

        placeHolder.getChildren().add(chart);
    }
}
