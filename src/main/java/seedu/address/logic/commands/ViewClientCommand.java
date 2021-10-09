package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.ClientContainsIdPredicate;

/**
 * Views a client based on the id specified.
 */
public class ViewClientCommand extends Command {
    public static final String COMMAND_WORD = "view -c";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Views a current client identified by the index number used in the displayed "
                    + "client list.\n"
                    + "If no such client exists, nothing will be shown\n"
                    + "Parameters: INDEX (must be a positive integer)"
                    + "Example usage : "
                    + COMMAND_WORD
                    + " 20 ";

    private final ClientContainsIdPredicate predicate;

    public ViewClientCommand(ClientContainsIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewClientCommand // instanceof handles nulls
                && predicate.equals(((ViewClientCommand) other).predicate)); // state check
    }
}
