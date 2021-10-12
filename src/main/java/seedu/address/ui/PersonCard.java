package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.lesson.LessonCode;
import seedu.address.model.person.Student;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String STRING_FORMAT_PARENT_CONTACT = "Parent's Contact: \t%s";
    private static final String STRING_FORMAT_ADDRESS = "Address: \t\t%s";
    private static final String STRING_FORMAT_EMAIL = "Email Address: \t%s";
    private static final String STRING_FORMAT_GRADE = "Grade: \t\t\t%s";
    private static final String STRING_FORMAT_LESSON = "Lesson(s): \t";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
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
    private FlowPane tags;
    @FXML
    private Label lessons;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        name.setUnderline(true);
        parentContact.setText(String.format(STRING_FORMAT_PARENT_CONTACT, student.getParentContact().value));
        address.setText(String.format(STRING_FORMAT_ADDRESS, student.getAddress().value));
        email.setText(String.format(STRING_FORMAT_EMAIL, student.getEmail().value));
        grade.setText(String.format(STRING_FORMAT_GRADE, student.getGrade().value));
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        lessons.setText(student.getLessonCodes().stream()
                .map(LessonCode::toString)
                .sorted()
                .reduce(STRING_FORMAT_LESSON, (l1, l2) -> l1 + '\t' + l2));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
