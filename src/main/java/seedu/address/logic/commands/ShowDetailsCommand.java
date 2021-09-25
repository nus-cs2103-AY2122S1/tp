package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Changes the remark of an existing person in the address book.
 */
public class ShowDetailsCommand extends Command {

    public static final String COMMAND_WORD = "showDetails";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of the Event matching the given name.\n"
            + "Parameters: EVENT_NAME \n"
            + "Example: " + COMMAND_WORD + " CS2103T Finals ";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Remark command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Event Name: %1$s";

    private final String eventName;

    public ShowDetailsCommand(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, eventName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowDetailsCommand // instanceof handles nulls
                && eventName.equals(((ShowDetailsCommand) other).eventName)); // state check
    }
}