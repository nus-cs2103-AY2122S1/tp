package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;

import java.util.List;

import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * Adds a lab with total score and default score for all the students in the list.
 */
public class AddLabCommand extends Command {

    public static final String COMMAND_WORD = "addlab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lab to all the students in the list.\n"
            + "Parameters: "
            + PREFIX_LAB_NUM + "<LAB_NUMBER> "
            + PREFIX_LAB_TOTAL + "<TOTAL_SCORE>\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LAB_NUM + "1 "
            + PREFIX_LAB_TOTAL + "20";

    public static final String MESSAGE_ADD_LAB_SUCCESS = "Lab Added: %1$s";



    public static final String NO_STUDENT_CONSTRAINTS = "There are no students to add the lab to.";

    private final Lab result;

    /**
     * @param result the lab result to be added.
     * */
    public AddLabCommand(Lab result) {
        requireNonNull(result);
        this.result = result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> studentList = model.getAllStudents();

        if (studentList.isEmpty()) {
            throw new CommandException(NO_STUDENT_CONSTRAINTS);
        }

        if (result.getLabTotal().getLabTotalScore() < 0) {
            throw new CommandException(Lab.MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT);
        }

        for (Student std: studentList) {
            Student target = std;
            Lab newLab = this.result.copy();
            if (!target.addLab(newLab)) {
                throw new CommandException(String.format(Lab.MESSAGE_LAB_ALREADY_EXISTS, result));
            }
            model.setStudent(target, std);
        }
        if (!model.getSelectedInformation().isEmpty()) {
            Student selectedStudent = model.getSelectedStudent().copy();
            selectedStudent.addLab(result);
            model.setSelectedStudent(selectedStudent);
            model.setSelectedLabs(selectedStudent.getLabList());
        }
        return new CommandResult(String.format(MESSAGE_ADD_LAB_SUCCESS, result));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLabCommand// instanceof handles nulls
                && result.equals(((AddLabCommand) other).result)); // state check
    }
}

