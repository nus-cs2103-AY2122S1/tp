package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TITLE;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lab to all the students in the list. "
            + "Parameters: "
            + PREFIX_LAB_TITLE + "Lab Title "
            + PREFIX_LAB_TOTAL + "Total Score"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LAB_TITLE + "1 "
            + PREFIX_LAB_TOTAL + "20";

    public static final String MESSAGE_ADD_LAB_SUCCESS = "Lab Added: %1$s";

    private final Lab result;

    /**
     * @param result the lab result to be added.
     * */
    public AddLabCommand(Lab result) {
        this.result = result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Gets the last filtered list displayed
        List<Student> lastShownList = model.getFilteredStudentList();

        for (Student std: lastShownList) {
            Student target = std;
            target.addLabResult(this.result);
            model.setStudent(target, std);
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

