package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.ReadOnlySummary;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class SummaryPanel extends UiPart<Region> {

    private static final String FXML = "SummaryPanel.fxml";
    private static final String DISPLAY_TOTAL_ELDERLY = "Total number of elderly: ";
    private static final String DISPLAY_OVERDUE_VISITS = "Number of overdue visits: ";
    private static final String DISPLAY_VISITS_NEXT_WEEK = "Number of scheduled visits in the next 7 days: ";
    private static final String DISPLAY_VISITS_NEXT_MONTH = "Number of scheduled visits in the next 30 days: ";
    private static final String DISPLAY_VISITS_LAST_WEEK = "Number of last visits in the past 7 days: ";
    private static final String DISPLAY_VISITS_LAST_MONTH = "Number of last visits in the past 30 days: ";

    public final ReadOnlySummary summary;

    @FXML
    private HBox summaryPanelPane;
    @FXML
    private Label totalElderly;
    @FXML
    private Label overdueVisits;
    @FXML
    private Label visitsNextWeek;
    @FXML
    private Label visitsNextMonth;
    @FXML
    private Label visitsLastWeek;
    @FXML
    private Label visitsLastMonth;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SummaryPanel(ReadOnlySummary summary) {
        super(FXML);
        this.summary = summary;
        totalElderly.setText(DISPLAY_TOTAL_ELDERLY + summary.getTotalElderly());
        overdueVisits.setText(DISPLAY_OVERDUE_VISITS + summary.getOverdueVisits());
        visitsLastWeek.setText(DISPLAY_VISITS_LAST_WEEK + summary.getVisitsLastWeek());
        visitsLastMonth.setText(DISPLAY_VISITS_LAST_MONTH + summary.getVisitsLastMonth());
        visitsNextWeek.setText(DISPLAY_VISITS_NEXT_WEEK + summary.getVisitsNextWeek());
        visitsNextMonth.setText(DISPLAY_VISITS_NEXT_MONTH + summary.getVisitsNextMonth());
    }
}

