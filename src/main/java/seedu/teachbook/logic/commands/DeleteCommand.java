package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.Student;

/**
 * Deletes students identified using the displayed indices from the TeachBook.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes one or more students identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX...||all\n"
            + "Examples: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " 2 4 5, " + COMMAND_WORD + " all";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student(s):\n%1$s";
    public static final String MESSAGE_NOTHING_TO_DELETE = "There is nothing to delete as there are no students.";

    private final List<Index> targetIndices;
    private final boolean isAll;

    /**
     * Creates a {@code DeleteCommand} to delete students at the specified {@code targetIndices}.
     *
     * @param targetIndices the index numbers of the students in the filtered student list to be deleted.
     */
    public DeleteCommand(List<Index> targetIndices) {
        this.targetIndices = targetIndices;
        isAll = false;
    }

    /**
     * Creates a {@code DeleteCommand} to delete all students in the filtered student list.
     */
    public DeleteCommand() {
        targetIndices = new ArrayList<>();
        this.isAll = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Although there is a noticeable code duplication to MarkCommand, we decided to not extract out the
        // similarities because delete, grade, mark, and unmark are different commands after all
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (isAll) {
            if (lastShownList.size() == 0) {
                throw new CommandException(MESSAGE_NOTHING_TO_DELETE);
            }
            for (int i = lastShownList.size() - 1; i >= 0; i--) {
                targetIndices.add(Index.fromZeroBased(i));
            }
        } else {
            // Ensures all indices are valid before executing the command
            for (Index targetIndex : targetIndices) {
                if (targetIndex.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
                }
            }
        }

        List<String> studentToDeleteDescriptions = new ArrayList<>();
        for (Index targetIndex : targetIndices) {
            Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
            studentToDeleteDescriptions.add(studentToDelete.toString());
            model.deleteStudent(studentToDelete);
        }
        Collections.reverse(studentToDeleteDescriptions);

        model.commitTeachBook();
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS,
                String.join("\n", studentToDeleteDescriptions)),
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndices.equals(((DeleteCommand) other).targetIndices)
                && isAll == ((DeleteCommand) other).isAll); // state check
    }
}
