package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TITLE;

import java.util.List;

import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

/**
 * Adds a lab with total score and default score for all the students in the list.
 */
public class EditLabCommand extends Command {

    public static final String COMMAND_WORD = "editlab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a lab for a student in the list. "
            + "Parameters: "
            + PREFIX_INDEX + "Index "
            + PREFIX_LAB_TITLE + "Lab Title "
            + PREFIX_LAB_RESULT + "Score"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 "
            + PREFIX_LAB_TITLE + "1 "
            + PREFIX_LAB_RESULT + "15";

    public static final String MESSAGE_ADD_LAB_SUCCESS = "Student Updated: %1$s";

    private final Index targetIndex;
    private final Double score;
    private final Lab result;

    /**
     * @param result the lab result to be added.
     * */
    public EditLabCommand(Index targetIndex, Lab result, Double score) {
        this.targetIndex = targetIndex;
        this.result = result;
        this.score = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student target = lastShownList.get(targetIndex.getZeroBased());
        Student replacement = target;
        replacement.editLabResult(result, score);

        model.setStudent(target, replacement);
        return new CommandResult(String.format(MESSAGE_ADD_LAB_SUCCESS, result));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditLabCommand// instanceof handles nulls
                && result.equals(((EditLabCommand) other).targetIndex)
                && result.equals(((EditLabCommand) other).result)); // state check
    }
}

