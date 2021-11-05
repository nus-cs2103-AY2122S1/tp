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

    public static final String MESSAGE_DEL_LAB_SUCCESS = "Lab Deleted: %1$s";
    public static final String MESSAGE_LAB_DOES_NOT_EXIST = "Lab doesn't exist: %1$s";

    private final Lab result;

    /**
     * @param result the lab result to be added.
     */
    public DeleteLabCommand(Lab result) {
        this.result = result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        if (!model.hasLab(result)) {
            throw new CommandException(String.format(MESSAGE_LAB_DOES_NOT_EXIST, result));
        }

        for (Student std : lastShownList) {
            Student target = std;
            target.deleteLab(this.result);
            model.setStudent(target, std);
        }
        return new CommandResult(String.format(MESSAGE_DEL_LAB_SUCCESS, result));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLabCommand// instanceof handles nulls
                && result.equals(((DeleteLabCommand) other).result)); // state check
    }
}
