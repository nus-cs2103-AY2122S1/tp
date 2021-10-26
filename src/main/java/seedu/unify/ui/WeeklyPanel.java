package seedu.unify.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.unify.commons.core.LogsCenter;
import seedu.unify.model.task.WeeklyTasks;

/**
 * Panel containing the list of daily panels for a particular week.
 */
public class WeeklyPanel extends UiPart<Region> {

    private static final String FXML = "WeeklyPanel.fxml";
    private static final String WEEK_LABEL = "Week %d (%s)";
    private final Logger logger = LogsCenter.getLogger(WeeklyPanel.class);

    @FXML
    private Label weekLabel;

    @FXML
    private StackPane progressPanelPlaceholder;

    @FXML
    private HBox dailyHBox;

    /**
     * Creates a {@code WeeklyPanel} with the given {@code ObservableList}.
     */
    public WeeklyPanel(WeeklyTasks weeklyTasks) {
        super(FXML);
        SimpleIntegerProperty observableWeekNumber = weeklyTasks.getObservableWeekNumber();
        LocalDate firstDayOfWeek = weeklyTasks.getFirstDayOfWeek();
        weekLabel.setText(String.format(
                WEEK_LABEL, observableWeekNumber.getValue(), weeklyTasks.getFirstDayOfWeek().toString()));
        for (int i = 0; i < 7; i++) {
            DailyPanel dailyPanel = new DailyPanel(weeklyTasks.getDailyTaskList(i), firstDayOfWeek.plusDays(i));
            dailyHBox.getChildren().add(dailyPanel.getRoot());
            // This can probably be replaced with something more efficient
            dailyHBox.widthProperty()
                    .addListener(e -> dailyPanel.getRoot().setPrefWidth(dailyHBox.getWidth() / 7));
        }

        ProgressPanel progressPanel =
                new ProgressPanel(
                        weeklyTasks.getWeeklyProgress(),
                        weeklyTasks.getTotalWeeklyTasks(),
                        weeklyTasks.getTotalDoneTasks());
        progressPanelPlaceholder.getChildren().add(progressPanel.getRoot());

        observableWeekNumber.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                weekLabel.setText(String.format(
                        WEEK_LABEL, newValue.intValue(), weeklyTasks.getFirstDayOfWeek().toString()));
            }
        });
    }

}
