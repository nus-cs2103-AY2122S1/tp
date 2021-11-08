package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private StackPane placeHolder;

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
        placeHolder.getChildren().add(resultDisplay);
    }

    public void getWelcomeMessage() {
        String welcomeMessage = "Welcome to Socius!\n"
                + "\nSocius is a desktop application that can help you to\n"
                + "\n1. manage your classmates’ contacts\n"
                + "2. make friends\n"
                + "3. find teammates!\n"
                + "\nLearn how to get started by entering 'help'!";
        resultDisplay.setText(welcomeMessage);
    }

    public void setChartToUser(List<ObservableList<PieChart.Data>> datas, List<String> titles) {
        ScrollPane scroll = new ScrollPane();

        for (int i = 0; i < 1; i++) {
            ObservableList<PieChart.Data> pieChartData = datas.get(i);
            double total = pieChartData.stream().map(PieChart.Data::getPieValue).reduce(0.0, Double::sum);

            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    String.format("%s (%.2f%%)", data.getName(), data.getPieValue() / total * 100)
                            )
                    )
            );

            PieChart pieChart = new PieChart(pieChartData);

            pieChart.setTitle(titles.get(i));
            pieChart.setLabelsVisible(true);
            pieChart.setStartAngle(180);

            placeHolder.getChildren().add(pieChart);

            VBox spacing = new VBox(500);
            spacing.setSpacing(150);

            placeHolder.getChildren().add(spacing);
        }
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setContent(placeHolder);
    }

    /**
     * Clear chart from previous commands.
     */
    public void clearDisplay() {
        placeHolder.getChildren().clear();
    }
}
