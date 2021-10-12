package seedu.programmer.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.programmer.model.student.Student;

/**
 * An UI component that displays information of a {@code student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label studentId;
    @FXML
    private Label classId;
    @FXML
    private Label grade;

    /**
     * Creates a {@code studentCode} with the given {@code student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        updateStudentInformation(student);
    }

    /**
     * Creates a student particular card to display
     */
    public StudentCard(Student student) {
        super(FXML);
        this.student = student;
        updateStudentInformation(student);
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
     * Update the student particulars to be displayed.
     * */
    public void updateStudentInformation(Student student) {
        name.setText(student.getName().fullName);
        studentId.setText("Student_ID: " + student.getStudentId().studentId);
        classId.setText("Class_ID: " + student.getClassId().classId);
        grade.setText("Grade: " + student.getGrade().grade);

        name.setTextFill(Color.WHITE);
        studentId.setTextFill(Color.WHITE);
        classId.setTextFill(Color.WHITE);
        grade.setTextFill(Color.WHITE);
    }
}
