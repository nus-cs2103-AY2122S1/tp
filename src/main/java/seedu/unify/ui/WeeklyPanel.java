package seedu.unify.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
        Integer week;
        LocalDate currentDay;
        week = WeeklyTracker.getWeek();
        weekLabel.setText("Week " + week);
        currentDay = LocalDate.ofYearDay(2021, week * 7).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        dailyHBox.getChildren().clear();
        for (int i = 0; i < 7; i++) {
            // these are just placeholders
            DailyPanel dailyPanel = new DailyPanel(currentDay, weeklyTaskList);
            currentDay = currentDay.plusDays(1);
            dailyHBox.getChildren().add(dailyPanel.getRoot());
            dailyPanel.getRoot().setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1;");
            dailyHBox.widthProperty().addListener(e -> dailyPanel.getRoot().setPrefWidth(dailyHBox.getWidth() / 7));
        }

        weeklyTaskList.addListener((ListChangeListener<Task>) c -> {
            Integer week1 = WeeklyTracker.getWeek();
            weekLabel.setText("Week " + week1);
            LocalDate currentDays = LocalDate.ofYearDay(2021, week1 * 7).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            dailyHBox.getChildren().clear();
            for (int i = 0; i < 7; i++) {
                // these are just placeholders
                DailyPanel dailyPanel1 = new DailyPanel(currentDays, weeklyTaskList);
                currentDays = currentDays.plusDays(1);
                dailyHBox.getChildren().add(dailyPanel1.getRoot());
                dailyPanel1.getRoot().setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1;");
                dailyHBox.widthProperty().addListener(e -> dailyPanel1.getRoot().setPrefWidth(dailyHBox.getWidth() / 7));
            }
        });

        // must include an increment operation to either
        // 1) Set Date for Daily Panels
        // 2) Form a date range and loop through to create Daily Panels


    }
}
