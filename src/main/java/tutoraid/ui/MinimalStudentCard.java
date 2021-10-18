package tutoraid.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tutoraid.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class MinimalStudentCard extends Card<Student> {

    private static final String FXML = "MinimalStudentListCard.fxml";

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
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public MinimalStudentCard(Student student, int displayedIndex) {
        super(FXML, student, displayedIndex);
        id.setText(displayedIndex + ". ");
        name.setText(student.getStudentName().fullName);
    }
}
