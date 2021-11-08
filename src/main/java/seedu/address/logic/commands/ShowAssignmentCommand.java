package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX_WITH_LIMIT;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Shows the assignment list of the person identified using it's displayed index from the address book.
 */
public class ShowAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the assignments assigned to the person identified by the "
            + "index number used in the displayed person list.\n"
            + "Parameters: " + MESSAGE_INVALID_INDEX_WITH_LIMIT + "\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_ASSIGNMENT_SUCCESS = "Showing assignments assigned to the person: %1$s";

    private final Index targetIndex;

    public ShowAssignmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShow = lastShownList.get(targetIndex.getZeroBased());
        model.updateAssignmentList(personToShow);
        return new CommandResult(String.format(MESSAGE_SHOW_ASSIGNMENT_SUCCESS, personToShow.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowAssignmentCommand // instanceof handles nulls
                && targetIndex.equals(((ShowAssignmentCommand) other).targetIndex)); // state check
    }
}
