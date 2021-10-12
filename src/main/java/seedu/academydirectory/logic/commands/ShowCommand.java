package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ASSESSMENT;

import javafx.collections.ObservableList;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.student.Student;

/**
 * A class that implements the command to show all the students' grades for an assessment.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String HELP_MESSAGE = "### Show grades for an assessment:  `show`\n"
            + "Avengers will be able to view all students' grades for a particular assessment.\n"
            + "\n"
            + "Format: `show as/ASSESSMENT`\n"
            + "\n"
            + "Record students' `GRADE` for the `ASSESSMENT`.\n"
            + "The input `ASSESSMENT` must be a valid assessment: RA1, MIDTERM, RA2, PE, FINAL.\n"
            + "\n"
            + "Example:\n"
            + "* `show as/RA1`";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show students' grades for an assessment. "
            + "Parameters: "
            + PREFIX_ASSESSMENT + "ASSESSMENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ASSESSMENT + "RA1 ";

    public static final String MESSAGE_SUCCESS = "Grades for %1$s:";

    private final String assessment;

    /**
     * Constructor for ShowCommand.
     * @param assessment The assessment's grades that will be displayed.
     */
    public ShowCommand(String assessment) {
        this.assessment = assessment;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Student> students = model.getFilteredStudentList();
        StringBuilder result = new StringBuilder();
        for (Student student : students) {
            String name = student.getName().fullName;
            String grade = student.getAssessment().getAssessmentGrade(assessment);
            result.append(name).append(": ").append(grade).append("\n");
        }
        return new CommandResult(result.toString());
    }
}
