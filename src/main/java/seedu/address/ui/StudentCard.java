package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.student.Student;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private static final String FXML = "StudentListCard.fxml";

    protected final Student student;

    @FXML
    protected FlowPane tags;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label studentNumber;
    @FXML
    private Label githubLink;
    @FXML
    private VBox studentValuesContainer;


    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        email.setText("Email: " + student.getEmail().value);
        studentNumber.setText("Student Number: " + student.getStudentNumber().toString());
        if (student.hasGroupName()) {
            Label studentGrp = new Label("Group: " + student.getGroupName().toString());
            tags.getChildren().add(studentGrp);
            studentGrp.getStyleClass().add("studentGroupLabel");
        }

        studentValuesContainer.getChildren().addAll(
                new StudentValuesBox(StudentValuesBox.ATTENDANCE_HEADER, student.getAttendance()),
                new StudentValuesBox(StudentValuesBox.PARTICIPATION_HEADER, student.getParticipation()));
        githubLink.setText("Github: " + student.getStudentLink());

        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
}
