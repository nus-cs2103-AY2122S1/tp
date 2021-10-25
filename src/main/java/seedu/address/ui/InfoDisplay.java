package seedu.address.ui;

import static seedu.address.logic.commands.ShowCommand.Info;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.AssessmentStatistics;
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

    private Student student;
    private Assessment assessment;

    @FXML
    private Label name;
    @FXML
    private Label info1;
    @FXML
    private Label info2;
    @FXML
    private Label info3;
    @FXML
    private Label info4;
    @FXML
    private Label info5;

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

    /**
     * Displays info of a {@code student}.
     */
    public void setStudentInfo(Student student) {
        name.setText(student.getName().fullName);
        info1.setText("ID: " + student.getId().value);

        String groupsString = student.getGroups().stream()
                .map(Group::toString).collect(Collectors.joining(", "));
        info2.setText("Groups: " + groupsString);
    }

    /**
     * Displays info of an {@code assessment}.
     */
    public void setAssessmentInfo(Assessment assessment) {
        name.setText(assessment.getName());

        AssessmentStatistics statistics = assessment.getStatistics();
        info1.setText("Grade range: " + statistics.getMin() + "-" + statistics.getMax());
        info2.setText("Median: " + statistics.getMedian());
        info3.setText("Mean: " + statistics.getMean());
        info4.setText("25th percentile: " + statistics.getXPercentile(25));
        info5.setText("75th percentile: " + statistics.getXPercentile(75));
    }

    /**
     * Clears any existing info.
     */
    public void clearInfo() {
        name.setText(null);
        info1.setText(null);
        info2.setText(null);
        info3.setText(null);
        info4.setText(null);
        info5.setText(null);
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
