package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import tutoraid.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

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

    public final Student student;

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
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        studentName.setText(formatCardLabel(STUDENT_NAME_LABEL, student.getStudentName().fullName));
        studentPhone.setText(formatCardLabel(STUDENT_PHONE_LABEL, student.getStudentPhone().value));
        parentName.setText(formatCardLabel(PARENT_NAME_LABEL, student.getParentName().fullName));
        parentPhone.setText(formatCardLabel(PARENT_PHONE_LABEL, student.getParentPhone().value));
        progress.setText(formatCardLabel(PROGRESS_LABEL, student.getProgress().toString()));
        paymentStatus.setText(formatCardLabel(PAYMENT_STATUS_LABEL, student.getPaymentStatus().toString()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }

    /**
     * Formats the text for a StudentCard to include both the name and value if present.
     * If the value is empty, it is displayed as (None).
     * If the name is empty, we will display only the value.
     *
     * @param fieldName The name of the field
     * @param value The value of the field
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
