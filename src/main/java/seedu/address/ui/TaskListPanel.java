package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.TodoTask;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private ListView<Task> taskListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listView -> new TaskListViewCell());
    }

    /**
     * Returns the task list region.
     */
    public Region getRegion() {
        return taskListView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    static class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (!(task instanceof TodoTask) && !(task instanceof EventTask) && !(task instanceof DeadlineTask)) {
                setGraphic(null);
                setText(null);
            }
            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else if (task instanceof TodoTask) {
                setGraphic(new ToDoTaskCard(task, getIndex() + 1).getRoot());
            } else if (task instanceof DeadlineTask) {
                setGraphic(new DeadlineTaskCard(task, getIndex() + 1).getRoot());
            } else if (task instanceof EventTask) {
                setGraphic(new EventTaskCard(task, getIndex() + 1).getRoot());
            } else {
                setGraphic(new ToDoTaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }

}
