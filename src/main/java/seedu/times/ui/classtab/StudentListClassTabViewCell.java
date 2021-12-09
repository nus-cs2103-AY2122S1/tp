package seedu.times.ui.classtab;

import javafx.scene.control.ListCell;
import seedu.times.model.person.Student;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
 */
public class StudentListClassTabViewCell extends ListCell<Student> {
    @Override
    protected void updateItem(Student student, boolean empty) {
        super.updateItem(student, empty);

        if (empty || student == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new StudentClassTabCard(student, getIndex() + 1).getRoot());
        }
    }
}
