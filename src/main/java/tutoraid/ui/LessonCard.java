package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tutoraid.model.lesson.Lesson;

/**
 * A UI component that displays information of a {@code Lesson}.
 */
public class LessonCard extends Card<Lesson> {

    private static final String FXML = "LessonListCard.fxml";

    private static final String LABEL_LESSON_NAME = "";
    private static final String LABEL_TIMING = "Timing";
    private static final String LABEL_PRICE = "Price";
    private static final String LABEL_CAPACITY = "Capacity";
    private static final String LABEL_STUDENTS = "Students";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on LessonBook level 4</a>
     */

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
        super(FXML, lesson, displayedIndex);
        id.setText(displayedIndex + ". ");
        lessonName.setText(formatCardLabel(LABEL_LESSON_NAME, lesson.getLessonName().toString()));
        timing.setText(formatCardLabel(LABEL_TIMING, lesson.getTiming().toString()));
        price.setText(formatCardLabel(LABEL_PRICE, lesson.getPrice().toString()));
        capacity.setText(formatCardLabel(LABEL_CAPACITY, lesson.getCapacity().toString()));
        students.setText(formatCardLabel(LABEL_STUDENTS, lesson.getStudents().toString()));
    }
}
