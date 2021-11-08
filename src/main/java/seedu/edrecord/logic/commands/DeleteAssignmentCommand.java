package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.edrecord.commons.core.Messages;
import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.assignment.Assignment;

/**
 * Deletes an assignment from Edrecord using its displayed index.
 */
public class DeleteAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "dlasg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an assignment using its ID number used in the displayed assignment list.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Deleted assignment: %1$s";

    private final Index id;

    public DeleteAssignmentCommand(Index id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasSelectedModule()) {
            throw new CommandException(Messages.MESSAGE_NO_MODULE_SELECTED);
        }

        if (id.getOneBased() >= model.getAssignmentCounter()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment asgToDelete = model.getAssignment(id.getOneBased())
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX));
        model.deleteAssignment(asgToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS, asgToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAssignmentCommand // instanceof handles nulls
                && id.equals(((DeleteAssignmentCommand) other).id)); // state check
    }
}
