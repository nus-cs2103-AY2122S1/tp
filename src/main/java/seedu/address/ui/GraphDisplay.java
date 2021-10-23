package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.chart.Chart;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

public class GraphDisplay extends UiPart<Region> {

    private static final String FXML = "GraphDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    public GraphDisplay() {
        super(FXML);
    }

    public void setChart(Chart chart) {
        resultDisplay.setText("Hello world");
    }
}
