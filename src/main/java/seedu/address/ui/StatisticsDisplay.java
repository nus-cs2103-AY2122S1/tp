package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.util.Optional;

import static java.lang.System.exit;

public class StatisticsDisplay extends UiPart<Region> {

    private static final String FXML = "StatisticsDisplay.fxml";

    @FXML
    private PieChart pieChart;

    public StatisticsDisplay() {
        super(FXML);
        updateStatistics(new double[]{1, 0, 0, 0});
    }

    public void updateStatistics(double[] statList) {

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Done", statList[0]),
                        new PieChart.Data("In progress", statList[1]),
                        new PieChart.Data("Overdue", statList[2]),
                        new PieChart.Data("due soon", statList[3])
                );
        if (pieChart.getData().isEmpty()) {
            pieChart.setData(pieChartData);
        }
        pieChart.setLegendSide(Side.LEFT);
        pieChart.getData().get(0).setPieValue(statList[0]);
        pieChart.getData().get(1).setPieValue(statList[1]);
        pieChart.getData().get(2).setPieValue(statList[2]);
        pieChart.getData().get(3).setPieValue(statList[3]);
//        pieChart.getData().get(0).setName(100 + "");
//        pieChart.getData().get(1).setName("1" + "");
//        pieChart.getData().get(2).setName("Overdue" + "");
//        pieChart.getData().get(3).setName(statList[3] + "");
//        pieChart.getData().get(0).setName("1");
//        pieChart.getData().get(1).setName("2");
//        pieChart.getData().get(2).setName("2");
//        pieChart.getData().get(3).setName("3");
//        pieChart.getData().get(1).getNode().setStyle("-fx-pie-color: lightblue;");
//        pieChart.getData().get(2).getNode().setStyle("-fx-pie-color: red;");
//        pieChart.getData().get(3).getNode().setStyle("-fx-pie-color: orange;");
//        PieChart.getData().forEach(d -> {
//            Optional<Node> opTextNode = pieChart.lookupAll(
//                    ".chart-pie-label").stream().filter(n -> (n instanceof Text)
//                    && ((Text) n).getText().equals(d.getName())).findAny();
//            opTextNode.ifPresent(node -> ((Text) node).setText(d.getPieValue() + ""));
//        });p
        pieChart.getData().forEach(d -> {
            Optional<Node> opTextNode = pieChart.lookupAll(
                    ".chart-pie-label").stream().filter(n -> (n instanceof Text)
                    && ((Text) n).getText().contains(d.getName())).findAny();
            opTextNode.ifPresent(node -> ((Text) node).setText((d.getName() + ": " + (int) d.getPieValue())));
        });
        pieChart.getData().forEach(d -> {
            pieChart.lookupAll(
                    ".chart-pie-label").stream().filter(n -> (n instanceof Text)).map(n->((Text) n).getText()).forEach(System.out::println);

        });
    }
}
