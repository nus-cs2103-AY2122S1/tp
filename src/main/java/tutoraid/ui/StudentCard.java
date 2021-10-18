package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tutoraid.model.student.Student;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends Card<Student> {

    private static final String FXML = "StudentListCard.fxml";

    private static final String STUDENT_NAME_LABEL = "";
    private static final String STUDENT_PHONE_LABEL = "Mobile";
    private static final String PARENT_NAME_LABEL = "Parent";
    private static final String PARENT_PHONE_LABEL = "Parent Mobile";
    private static final String PROGRESS_LABEL = "Progress";
    private static final String PAYMENT_STATUS_LABEL = "";

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
    private Label paymentStatus;


    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML, student, displayedIndex);
        id.setText(displayedIndex + ". ");
        studentName.setText(formatCardLabel(STUDENT_NAME_LABEL, student.getStudentName().fullName));
        studentPhone.setText(formatCardLabel(STUDENT_PHONE_LABEL, student.getStudentPhone().value));
        parentName.setText(formatCardLabel(PARENT_NAME_LABEL, student.getParentName().fullName));
        parentPhone.setText(formatCardLabel(PARENT_PHONE_LABEL, student.getParentPhone().value));
        progress.setText(formatCardLabel(PROGRESS_LABEL, student.getProgress().toString()));
        paymentStatus.setText(formatCardLabel(PAYMENT_STATUS_LABEL, student.getPaymentStatus().toString()));
    }
}
