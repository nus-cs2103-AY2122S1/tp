package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.Student;

/**
 * Adds a student to ProgrammerError.
 */
public class CreateLabResultCommand extends Command {

    public static final String COMMAND_WORD = "create";

    //todo
    public static final String MESSAGE_USAGE = COMMAND_WORD + "work in progress";

    public static final String MESSAGE_CREATE_RESULT_SUCCESS = "Success";

    private final Index targetIndex;

    private final LabResult result;

    public CreateLabResultCommand(Index targetIndex, LabResult result) {
        this.targetIndex = targetIndex;
        this.result = result;
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
        replacement.addLabResult(result);

        model.setStudent(target,replacement);
        return new CommandResult(String.format(MESSAGE_CREATE_RESULT_SUCCESS, target));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateLabResultCommand// instanceof handles nulls
                && targetIndex.equals(((CreateLabResultCommand) other).targetIndex)); // state check
    }
}

