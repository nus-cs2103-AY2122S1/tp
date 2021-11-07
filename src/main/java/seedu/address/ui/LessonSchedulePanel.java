package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.lesson.LessonWithAttendees;

/**
 * Panel containing the schedule with a list of lessons.
 */
public class LessonSchedulePanel extends UiPart<Region> {
    private static final String FXML = "LessonScheduleList.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<LessonWithAttendees> lessonScheduleView;

    /**
     * Creates a {@code LessonSchedulePanel} with the given {@code List}.
     */
    public LessonSchedulePanel(ObservableList<LessonWithAttendees> lessonWithAttendees) {
        super(FXML);
        lessonScheduleView.setItems(lessonWithAttendees);

        //lessonScheduleView.setItems(lessonWithAttendees);
        lessonScheduleView.setCellFactory(listView -> new LessonListCellView());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class LessonListCellView extends ListCell<LessonWithAttendees> {
        @Override
        protected void updateItem(LessonWithAttendees lessonWithAttendees, boolean empty) {
            super.updateItem(lessonWithAttendees, empty);

            if (empty || lessonWithAttendees == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LessonScheduleCard(lessonWithAttendees).getRoot());
            }
        }
    }

}
