package seedu.unify.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.unify.commons.core.LogsCenter;
import seedu.unify.model.task.Task;

/**
 * Panel containing the list of tasks for a particular day.
 */
public class DailyPanel extends UiPart<Region> {

    private static final String FXML = "DailyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DailyPanel.class);

    @FXML
    private VBox box;
    @FXML
    private Label day;
    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public DailyPanel(ObservableList<Task> dailyTaskList, LocalDate date) {
        super(FXML);
        day.setText(date.getDayOfWeek().toString());
        taskListView.setItems(dailyTaskList);
        taskListView.setCellFactory(listView -> new DailyViewCell(dailyTaskList.size()));
        dailyTaskList.addListener((ListChangeListener<Task>) c -> {
            taskListView.setItems(dailyTaskList);
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code DayCard}.
     */
    class DailyViewCell extends ListCell<Task> {

        private final int size;

        DailyViewCell(int size) {
            this.size = size;
        }

        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);
            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayCard(task, size).getRoot());
            }
        }
    }
}
