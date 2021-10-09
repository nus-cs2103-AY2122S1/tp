package seedu.address.ui;

import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Student;

/**
 * An UI component that displays information of a {@code Lesson}
 */
public class LessonCard extends UiPart<Region> {

    private static final String FXML = "LessonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     *      * As a consequence, UI elements' variable names cannot be set to such keywords
     *      * or an exception will be thrown by JavaFX during runtime.
     *      *
     *      * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label subject;
    @FXML
    private Label id;
    @FXML
    private Label grade;
    @FXML
    private Label dayOfWeek;
    @FXML
    private Label time;
    @FXML
    private Label endTime;
    @FXML
    private Label price;
    @FXML
    private Label lessonCode;

    /**
     * Creates a {@code LessonCode} with the given {@code Lesson} and index to display.
     */
    public LessonCard(Lesson lesson, int displayIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayIndex + ". ");
        lessonCode.setText(lesson.getLessonCode());
        subject.setText(lesson.getSubject());
        grade.setText(lesson.getGrade().value);
        dayOfWeek.setText(lesson.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        time.setText(String.format("%s - %s", lesson.getStartTime().toString(), lesson.getEndTime().toString()));
        price.setText(Double.toString(lesson.getPrice()));
    }

    @Override
    public boolean equals(Object other) {
        //short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonCard)) {
            return false;
        }

        // state check
        LessonCard card = (LessonCard) other;
        return id.getText().equals(card.id.getText())
                && lesson.equals(card.lesson);
    }


}
