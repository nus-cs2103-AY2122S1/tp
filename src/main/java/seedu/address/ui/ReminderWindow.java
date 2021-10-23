package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import com.calendarfx.model.Entry;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    @FXML
    private ListView<Entry<Lesson>> lessonsList;

    public ReminderWindow(ObservableList<Entry<Lesson>> lessons) {
        this(new Stage());
        lessonsList.setItems(lessons);
        lessonsList.setCellFactory(listView -> new ReminderWindow.LessonListViewCell());
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

    public ReminderWindow updateLessons(ObservableList<Entry<Lesson>> lessons) {
        this.hide();
        return new ReminderWindow(lessons);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
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
