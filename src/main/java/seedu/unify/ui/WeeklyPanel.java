package seedu.unify.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.unify.commons.core.LogsCenter;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Task;
import seedu.unify.model.task.WeeklyTracker;

public class WeeklyPanel extends UiPart<Region> {

    private static final String FXML = "WeeklyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WeeklyPanel.class);
    // add date to be passed down from Weekly Panel
    private Date[] dateRange;

    @FXML
    private Label weekLabel;

    @FXML
    private HBox dailyHBox;

    /**
     * Creates a {@code WeeklyPanel} with the given {@code ObservableList}.
     */
    public WeeklyPanel(ObservableList<Task> weeklyTaskList) {
        super(FXML);
        final Integer[] week = new Integer[1];
        final LocalDate[] currentDay = new LocalDate[1];

        // first run
        week[0] = WeeklyTracker.getWeek();
        weekLabel.setText("Week " + week[0]);
        currentDay[0] = LocalDate.ofYearDay(2021, week[0] * 7 - 3);
        dailyHBox.getChildren().clear();
        for (int i = 0; i < 7; i++) {
            // these are just placeholders
            DailyPanel dailyPanel = new DailyPanel(currentDay[0], weeklyTaskList);
            currentDay[0] = currentDay[0].plusDays(1);
            dailyHBox.getChildren().add(dailyPanel.getRoot());
            dailyPanel.getRoot().setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1;");
            dailyHBox.widthProperty().addListener(e -> dailyPanel.getRoot().setPrefWidth(dailyHBox.getWidth() / 7));
        }

        weeklyTaskList.addListener((ListChangeListener<Task>) c -> {
            week[0] = WeeklyTracker.getWeek();
            weekLabel.setText("Week " + week[0]);
            currentDay[0] = LocalDate.ofYearDay(2021, week[0] * 7 - 3);
            dailyHBox.getChildren().clear();
            for (int i = 0; i < 7; i++) {
                // these are just placeholders
                DailyPanel dailyPanel = new DailyPanel(currentDay[0], weeklyTaskList);
                currentDay[0] = currentDay[0].plusDays(1);
                dailyHBox.getChildren().add(dailyPanel.getRoot());
                dailyPanel.getRoot().setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1;");
                dailyHBox.widthProperty().addListener(e -> dailyPanel.getRoot().setPrefWidth(dailyHBox.getWidth() / 7));
            }
        });

        // must include an increment operation to either
        // 1) Set Date for Daily Panels
        // 2) Form a date range and loop through to create Daily Panels


    }
}
