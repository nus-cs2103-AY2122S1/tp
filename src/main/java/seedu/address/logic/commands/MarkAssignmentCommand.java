package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX_WITH_LIMIT;

/**
 * Marks an assignment to the person's assignment list.
 */
public class MarkAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the specified person's assignment identified by the "
            + "index number used in the displayed assignment list.\n"
            + "Parameters: " + MESSAGE_INVALID_INDEX_WITH_LIMIT + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "1";

    public static final String MESSAGE_MARK_ASSIGNMENT_SUCCESS = "Marked Assignment: %1$s";
    public static final String MESSAGE_ASSIGNMENT_ALREADY_COMPLETED =
            "This assignment is already completed by the student!";

    private final Index targetAssignmentIndex;

    /**
     * Creates an MarkAssignmentCommand to mark the specified {@code Assignment}
     */
    public MarkAssignmentCommand(Index targetAssignmentIndex) {
        this.targetAssignmentIndex = targetAssignmentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Assignment assignmentToMark = model.getAssignmentInList(targetAssignmentIndex);

        Person personToRemoveAssignment = model.getActivePerson();

        if (model.isAssignmentCompleted(assignmentToMark)) {
            throw new CommandException(MESSAGE_ASSIGNMENT_ALREADY_COMPLETED);
        }

        model.markAssignment(personToRemoveAssignment, assignmentToMark);

        return new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENT_SUCCESS, assignmentToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAssignmentCommand // instanceof handles nulls
                && targetAssignmentIndex.equals(((MarkAssignmentCommand) other).targetAssignmentIndex)); // state check
    }
}
