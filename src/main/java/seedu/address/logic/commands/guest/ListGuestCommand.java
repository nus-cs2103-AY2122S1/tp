package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GUESTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListGuestCommand extends Command {

    public static final String COMMAND_WORD = "listguest";

    public static final String MESSAGE_SUCCESS = "Listed all guests";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
        return new CommandResult(MESSAGE_SUCCESS, "Guests");
    }
}
