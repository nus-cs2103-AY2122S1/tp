package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class StatisticsDisplay extends UiPart<Region> {

    private static final String FXML = "StatisticsDisplay.fxml";

    @FXML
    private PieChart pieChart;

    private final ObservableList<PieChart.Data> statList;

    /**
     * Constructor for StatisticsDisplay.
     */
    public StatisticsDisplay(ObservableList<PieChart.Data> statList) {
        super(FXML);
        this.statList = statList;
        pieChart.setData(statList);
        initialise();
    }

    /**
     * Initialises the GUI values for {@code PieChart}.
     */
    private void initialise() {
        pieChart.setLabelLineLength(30);

        for (Node node : pieChart.lookupAll(".chart-legend-item")) {
            if (node instanceof Label) {
                Label label = (Label) node;
                label.setWrapText(true);
                label.setManaged(true);
                label.setPrefWidth(160);
                label.setStyle("-fx-border-width: 4");
                label.setStyle("-fx-border-color: darkblue");
            }
        }
    }
}
