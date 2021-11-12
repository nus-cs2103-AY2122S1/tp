package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.lesson.LessonWithAttendees;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class LessonScheduleCard extends UiPart<Region> {

    private static final String FXML = "LessonScheduleCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final LessonWithAttendees lessonWithAttendees;

    @FXML
    private HBox cardPane;
    @FXML
    private Label details;
    @FXML
    private FlowPane attendees;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public LessonScheduleCard(LessonWithAttendees lessonWithAttendees) {
        super(FXML);
        this.lessonWithAttendees = lessonWithAttendees;
        details.setText(lessonWithAttendees.getLesson().toString());
        lessonWithAttendees.getAttendeeList().stream()
                .forEach(attendee -> attendees.getChildren().add(new Label(attendee.getAttendeeDetails())));
        details.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonScheduleCard)) {
            return false;
        }

        // state check
        LessonScheduleCard card = (LessonScheduleCard) other;
        return lessonWithAttendees.equals(card.lessonWithAttendees);
    }
}
