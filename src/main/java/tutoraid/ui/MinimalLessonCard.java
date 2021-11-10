package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tutoraid.model.lesson.Lesson;

/**
 * An UI component that displays information of a {@code Lesson}.
 */
public class MinimalLessonCard extends Card<Lesson> {

    private static final String FXML = "MinimalLessonListCard.fxml";

    private static final String LABEL_LESSON_NAME = "Lesson";
    private static final String LABEL_TIMING = "Timing";

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


    /**
     * Creates a {@code LessonCard} with the given {@code Lesson} and index to display.
     */
    public MinimalLessonCard(Lesson lesson, int displayedIndex) {
        super(FXML, lesson, displayedIndex);
        id.setText(displayedIndex + ". ");
        lessonName.setText(lesson.toNameString());
        timing.setText(formatCardLabel(LABEL_TIMING, lesson.getTiming().toString()));
    }
}
