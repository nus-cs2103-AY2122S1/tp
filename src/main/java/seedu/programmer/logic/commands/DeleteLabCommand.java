package seedu.programmer.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_NUM;

import java.util.List;

import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * Adds a lab with total score and default score for all the students in the list.
 */
public class DeleteLabCommand extends Command {

    public static final String COMMAND_WORD = "dellab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a lab from all the students in the list.\n"
            + "Parameters: "
            + PREFIX_LAB_NUM + "<LAB_NUMBER>\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LAB_NUM + "1";

    public static final String MESSAGE_DEL_LAB_SUCCESS = "Deleted %1$s";
    public static final String MESSAGE_NO_STUDENT = "There are no students whose labs can be deleted";

    private final Lab lab;

    /**
     * @param lab to be added.
     */
    public DeleteLabCommand(Lab lab) {
        this.lab = lab;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> studentList = model.getAllStudents();

        if (studentList.isEmpty()) {
            throw new CommandException(MESSAGE_NO_STUDENT);
        }

        if (!model.hasLab(lab)) {
            throw new CommandException(String.format(Lab.MESSAGE_LAB_NOT_EXISTS, lab));
        }

        for (Student student : studentList) {
            Student originalStudent = student;
            originalStudent.deleteLab(lab);
            model.setStudent(originalStudent, student);
        }

        if (!model.getSelectedInformation().isEmpty()) {
            Student selectedStudent = model.getSelectedStudent().copy();
            selectedStudent.deleteLab(lab);
            model.setSelectedStudent(selectedStudent);
            model.setSelectedLabs(selectedStudent.getLabList());
        }

        return new CommandResult(String.format(MESSAGE_DEL_LAB_SUCCESS, lab));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLabCommand// instanceof handles nulls
                && lab.equals(((DeleteLabCommand) other).lab)); // state check
    }
}
