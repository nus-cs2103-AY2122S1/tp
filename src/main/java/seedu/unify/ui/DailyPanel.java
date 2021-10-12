package seedu.unify.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.unify.commons.core.LogsCenter;
import seedu.unify.model.task.Task;


public class DailyPanel extends UiPart<Region> {

    private static final String FXML = "DailyPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DailyPanel.class);

    @FXML
    private Label day;
    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public DailyPanel(ObservableList<Task> taskList, String dayString) {
        super(FXML);
        day.setText(dayString);
        // create new task list here based on date
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new DailyPanel.DailyViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code DayCard}.
     */
    class DailyViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}
