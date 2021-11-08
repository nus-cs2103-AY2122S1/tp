package seedu.programmer.ui.components;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.ui.UiPart;


/**
 * A UI component that displays information of a {@code student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    private final Student student;

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
    private Label email;
    @FXML
    private FlowPane tags;

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
     * Creates a student particular card to display on the side panel.
     */
    public StudentCard(Student student) {
        super(FXML);
        this.student = student;
        id.setText(">");
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
     * Updates the student particulars to be displayed.
     */
    public void updateStudentInformation(Student student) {
        name.setText(student.getNameValue());
        studentId.setText("Student ID: " + student.getStudentIdValue());
        classId.setText("Class ID: " + student.getClassIdValue());
        email.setText("Email: " + student.getEmailValue());
        tags.getChildren().clear();
        ObservableList<Lab> labResults = student.getLabList();
        for (Lab lab : labResults) {
            Label labLabel = new Label(lab.toString());
            labLabel.setStyle(" -fx-background-color: red;");
            if (lab.isMarked()) {
                labLabel.setStyle(" -fx-background-color: green;");
            }
            tags.getChildren().add(labLabel);
        }
    }

    public Student getStudent() {
        return student;
    }
}
