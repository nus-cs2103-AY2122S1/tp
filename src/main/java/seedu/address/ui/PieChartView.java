package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;

public class PieChartView extends UiPart<Region> implements SecondPanel {
    private static String fxml = "PieChart.fxml";

    @FXML
    private PieChart pieChart;

    /**
     * Constructor for the piechart
     */
    public PieChartView() {
        super(fxml);
        // data to be changed
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Profits for 2020", 20),
                        new PieChart.Data("Profits for 2021", 30),
                        new PieChart.Data("Profits for 2022", 50)
                );
        pieChart.getData().addAll(pieChartData);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PieChartView)) {
            return false;
        }
        PieChartView view = (PieChartView) other;
        return pieChart.equals(view.pieChart);
    }
}
