package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tutoraid.model.lesson.Lesson;

/**
 * An UI component that displays information of a {@code Lesson}.
 */
public class LessonCard extends UiPart<Region> {

    private static final String FXML = "LessonListCard.fxml";

    private static final String LESSON_NAME_LABEL = "Lesson";
    private static final String TIMING_LABEL = "Timing";
    private static final String PRICE_LABEL = "Price";
    private static final String CAPACITY_LABEL = "Capacity";
    private static final String STUDENTS_LABEL = "Students";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on LessonBook level 4</a>
     */

    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label lessonName;
    @FXML
    private Label timing;
    @FXML
    private Label price;
    @FXML
    private Label capacity;
    @FXML
    private Label students;

    /**
     * Creates a {@code LessonCard} with the given {@code Lesson} and index to display.
     */
    public LessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayedIndex + ". ");
        lessonName.setText(formatCardLabel(LESSON_NAME_LABEL, lesson.getLessonName().lessonName));
        timing.setText(formatCardLabel(TIMING_LABEL, lesson.getTiming().timing));
        price.setText(formatCardLabel(PRICE_LABEL, lesson.getPrice().price));
        capacity.setText(formatCardLabel(CAPACITY_LABEL, lesson.getCapacity().capacity));
        students.setText(formatCardLabel(STUDENTS_LABEL, lesson.getStudents().toString()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
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

    /**
     * Formats the text for a LessonCard to include both the name and value if present.
     * If the value is empty, it is displayed as (None).
     * If the name is empty, we will display only the value.
     *
     * @param fieldName The name of the field
     * @param value     The value of the field
     * @return A formatted string that includes the field name and its value
     */
    public static String formatCardLabel(String fieldName, String value) {
        if (value.equals("")) {
            value = "(None)";
        }
        return fieldName.equals("")
                ? value
                : String.format("%s: %s", fieldName, value);
    }
}
