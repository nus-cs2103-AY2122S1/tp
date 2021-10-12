package seedu.unify.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.unify.commons.core.LogsCenter;
import seedu.unify.logic.Logic;
import seedu.unify.model.task.Date;

public class WeeklyPanel extends UiPart<Region> {

    private static final String FXML = "WeeklyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WeeklyPanel.class);
    private Logic logic;
    // add date to be passed down from Weekly Panel
    private Date[] dateRange;


    @FXML
    private Label weekLabel;

    @FXML
    private HBox dailyHBox;

    /**
     * Creates a {@code WeeklyPanel} with the given {@code ObservableList}.
     */
    public WeeklyPanel(Date date) {
        super(FXML);
        weekLabel.setText(date.toString());
        // must include an increment operation to either
        // 1) Set Date for Daily Panels
        // 2) Form a date range and loop through to create Daily Panels
        for (int i = 0; i < 7; i++) {
            // these are just placeholders
            VBox placeHolderVBox = new VBox();
            DailyPanel dailyPanel = new DailyPanel(date, indexToDay(i));
            placeHolderVBox.getChildren().add(dailyPanel.getRoot());
            placeHolderVBox.setStyle("-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;");
            dailyHBox.widthProperty().addListener(e -> placeHolderVBox.setPrefWidth(dailyHBox.getWidth() / 7));
            dailyHBox.getChildren().add(placeHolderVBox);
        }
    }

    private String indexToDay(Integer index) {
        String day;
        switch (index) {
        case 0:
            day = "Monday";
            break;
        case 1:
            day = "Tuesday";
            break;
        case 2:
            day = "Wednesday";
            break;
        case 3:
            day = "Thursday";
            break;
        case 4:
            day = "Friday";
            break;
        case 5:
            day = "Saturday";
            break;
        case 6:
            day = "Sunday";
            break;
        default:
            day = "Unknown";
        }
        return day;
    }
}
