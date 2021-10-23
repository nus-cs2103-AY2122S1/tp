package seedu.address.ui;

import static seedu.address.logic.commands.ShowCommand.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.Student;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class InfoDisplay extends UiPart<Region> {

    private static final String FXML = "InfoDisplay.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public Student student;
    public Assessment assessment;

    @FXML
    private Label name;
    @FXML
    private Label info1, info2, info3, info4;

    public InfoDisplay() {
        super(FXML);
    }

    public void setInfo(Info info) {
        if (info.getStudent().isPresent()) {
            student = info.getStudent().get();
            setStudentInfo(student);
        }

        if (info.getAssessment().isPresent()) {
            assessment = info.getAssessment().get();
            setAssessmentInfo(assessment);
        }
    }

    public void setStudentInfo(Student student) {
        name.setText(student.getName().fullName);
        info1.setText("ID: " + student.getId().value);

        String groupsString = student.getGroups().stream()
                .map(Group::toString).collect(Collectors.joining(", "));
        info2.setText("Groups: " + groupsString);
    }

    public void setAssessmentInfo(Assessment assessment) {
        name.setText(assessment.getName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InfoDisplay)) {
            return false;
        }

        // state check
        InfoDisplay info = (InfoDisplay) other;
        return student.equals(info.student);
    }
}
