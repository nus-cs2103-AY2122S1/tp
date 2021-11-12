package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.student.Student;

/**
 * Deletes a student identified using it's displayed index from the Academy Directory.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String HELP_MESSAGE = "#### Deleting a student: `delete`\n"
            + "\n"
            + "Avengers can delete a student from Academy Directory.\n"
            + "\n"
            + "Format: `delete INDEX`\n"
            + "\n"
            + "* `INDEX` is an unique id assigned to each student in the system.\n"
            + "* Deletes the student at the specified `INDEX`\n"
            + "* The index refers to the index number shown in the displayed student list.\n"
            + "* The index **must be a positive integer** 1, 2, 3, …\u200B\n"
            + "\n"
            + "Examples:\n"
            + "* `delete 2` deletes the 2nd student in the list.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student from the student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Type in `help delete` for more details\n";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";
    public static final String COMMIT_MESSAGE = "Deleted Student: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete),
                Optional.of(String.format(COMMIT_MESSAGE, studentToDelete.getName())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
