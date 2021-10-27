package seedu.address.ui;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Application;
import seedu.address.model.position.Position;

/**
 * A factory class containing methods to process a position and its applicants
 * into a pie chart.
 */
public class PositionPieChartFactory {

    /**
     * Processes an {@code ObservableList} of applicants under a specified position
     * into an {@code ObservableList} of {@code PieChart.Data}, where each list item
     * represents a slice in a pie chart.
     */
    public static PieChart createPieChart(ObservableList<Applicant> applicants, Position position) {
        PieChart positionChart = createPieChart(applicants.filtered(applicant -> applicant.isApplyingTo(position)));
        positionChart.setTitle(position.getTitle().fullTitle);
        positionChart.setLabelsVisible(true);

        positionChart.getData().forEach(data -> {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(data.getPieValue() + "%");
            Tooltip.install(data.getNode(), tooltip);
        });

        return positionChart;
    }

    /**
     * Internal method to create a pie chart, given a list of applicants under a single position.
     */
    private static PieChart createPieChart(ObservableList<Applicant> applicantsUnderPosition) {
        int applicantCount = applicantsUnderPosition.size();
        ObservableList<PieChart.Data> pieChartData = Arrays.stream(Application.ApplicationStatus.values())
                .map(status -> createPieChartData(applicantsUnderPosition, applicantCount, status))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        return new PieChart(pieChartData);
    }

    /**
     * Internal method to create a {@code PieChart.Data} object from a given application status.
     */
    private static PieChart.Data createPieChartData(
            ObservableList<Applicant> applicants, int applicantCount, Application.ApplicationStatus status) {
        int applicantsWithStatusCount = applicants.filtered(applicant ->
                applicant.hasApplicationStatus(status)).size();
        double percentage = (double) applicantsWithStatusCount * 100 / applicantCount;

        return new PieChart.Data(status.toString(), percentage);
    }

}
