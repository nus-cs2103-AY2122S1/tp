package seedu.tuitione.ui;

import static seedu.tuitione.model.lesson.Price.CURRENCY;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Tuitione level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label parentContact;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label grade;
    @FXML
    private FlowPane remarks;
    @FXML
    private Label lessons;
    @FXML
    private Label subscription;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;

        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        name.setUnderline(true);
        parentContact.setText(student.getParentContact().value);
        address.setText(student.getAddress().value);
        email.setText(student.getEmail().value);
        grade.setText(student.getGrade().value);

        student.getRemarks().stream()
                .sorted(Comparator.comparing(remark -> remark.remarkName))
                .forEach(remark -> remarks.getChildren().add(new Label(remark.remarkName)));

        if (student.getNumberOfLessonsEnrolled() > 0) {
            StringBuilder lessonSb = new StringBuilder();
            student.getLessonCodes().stream()
                    .map(LessonCode::toString)
                    .sorted()
                    .forEach(lc -> lessonSb.append(lc).append("   "));
            lessons.setText(lessonSb.toString());
        } else {
            lessons.setText("-");
        }

        subscription.setText(String.format(CURRENCY + " %.2f", student.getSubscriptionPrice()));
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
