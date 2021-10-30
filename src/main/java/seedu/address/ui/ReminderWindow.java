package seedu.address.ui;

import java.util.logging.Logger;

import com.calendarfx.model.Entry;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.Lesson;

/**
 * Controller for a reminder window.
 */
public class ReminderWindow extends ExternalWindow {
    private static final String FXML = "ReminderWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);
    private static final String PLACEHOLDER_MESSAGE = "There is no upcoming lesson.";

    @FXML
    private ListView<Entry<Lesson>> lessonsList;

    @FXML
    private Label placeholder;

    /**
     * Constructs a reminder window with the list of upcoming lessons.
     *
     * @param lessons List of upcoming lessons to be shown.
     */
    public ReminderWindow(ObservableList<Entry<Lesson>> lessons) {
        this(new Stage());
        lessonsList.setItems(lessons);
        lessonsList.setCellFactory(listView -> new ReminderWindow.LessonListViewCell());
        lessonsList.managedProperty().bind(Bindings.isNotEmpty(lessonsList.getItems()));
        placeholder.managedProperty().bind(Bindings.isEmpty(lessonsList.getItems()));
        placeholder.visibleProperty().bind(Bindings.isEmpty(lessonsList.getItems()));
        placeholder.setText(PLACEHOLDER_MESSAGE);
    }

    /**
     * Creates a new ReminderWindow.
     *
     * @param root Stage to use as the root of the ReminderWindow.
     */
    public ReminderWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Shows the reminder window.
     */
    @Override
    public void show() {
        super.show();
        logger.fine("Showing the reminder of upcoming lessons.");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an upcoming {@code Lesson}
     * using a {@code ReminderLessonCard}.
     */
    class LessonListViewCell extends ListCell<Entry<Lesson>> {
        @Override
        protected void updateItem(Entry<Lesson> lessonEntry, boolean empty) {
            super.updateItem(lessonEntry, empty);

            if (empty || lessonEntry == null) {
                setGraphic(null);
                setText(null);
            } else {
                Lesson lesson = lessonEntry.getUserObject();
                String lessonTitle = lessonEntry.getTitle();
                setGraphic(new ReminderLessonCard(lesson, getIndex() + 1, lessonTitle).getRoot());
            }
        }
    }
}
