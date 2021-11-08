package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tutoraid.model.student.Student;

/**
 * A UI component that displays information of a {@code Student}. The full student card displays every progress entry.
 */
public class FullStudentCard extends Card<Student> {

    private static final String FXML = "StudentListCard.fxml";

    private static final String LABEL_STUDENT_NAME = "";
    private static final String LABEL_STUDENT_PHONE = "Mobile";
    private static final String LABEL_PARENT_NAME = "Parent";
    private static final String LABEL_PARENT_PHONE = "Parent Mobile";
    private static final String LABEL_PROGRESS = "Progress";
    private static final String LABEL_LESSONS = "Lessons";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on StudentBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label studentName;
    @FXML
    private Label studentPhone;
    @FXML
    private Label parentName;
    @FXML
    private Label parentPhone;
    @FXML
    private Label progress;
    @FXML
    private Label lessons;


    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public FullStudentCard(Student student, int displayedIndex) {
        super(FXML, student, displayedIndex);
        id.setText(displayedIndex + ". ");
        studentName.setText(formatCardLabel(LABEL_STUDENT_NAME, student.getStudentName().fullName));
        studentPhone.setText(formatCardLabel(LABEL_STUDENT_PHONE, student.getStudentPhone().value));
        parentName.setText(formatCardLabel(LABEL_PARENT_NAME, student.getParentName().fullName));
        parentPhone.setText(formatCardLabel(LABEL_PARENT_PHONE, student.getParentPhone().value));
        progress.setText(formatCardLabel(LABEL_PROGRESS, student.getProgressList().toString()));
        lessons.setText(formatCardLabel(LABEL_LESSONS, student.getLessons().toString()));
    }
}
