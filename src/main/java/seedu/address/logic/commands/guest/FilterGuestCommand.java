package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.guest.GuestPredicate;

/**
 * Finds and lists all guests in guest book whose fields match any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterGuestCommand extends Command {

    public static final String COMMAND_WORD = "filterguest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the guest list by any given fields. "
            + "Requires at least one parameter.\n"
            + "Parameters: "
            + "[" + PREFIX_PASSPORT_NUMBER + "PASSPORT_NUMBER" + "] "
            + "[" + PREFIX_NAME + "NAME" + "] "
            + "[" + PREFIX_ROOM_NUMBER + "ROOM_NUMBER" + "] "
            + "[" + PREFIX_EMAIL + "EMAIL" + "] "
            + "[" + PREFIX_TAG + "TAG" + "]" + "...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Bill "
            + PREFIX_TAG + "Vaccinated "
            + "\nThis filters the list by those named Bill and have the Vaccinated tag.";

    private final GuestPredicate predicate;

    /**
     * Creates a filter guest command
     *
     * @param predicate Contains the filter for the guest command.
     */
    public FilterGuestCommand(GuestPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGuestList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_GUESTS_LISTED_OVERVIEW, model.getFilteredGuestList().size()), "Guests");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterGuestCommand // instanceof handles nulls
                && predicate.equals(((FilterGuestCommand) other).predicate)); // state check
    }

}
