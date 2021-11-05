package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientContainsIdPredicate;

/**
 * Views a client based on the id specified.
 */
public class ViewClientCommand extends Command {
    public static final String COMMAND_WORD = "view -c";
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Views a current client identified by the index number used in the displayed "
                    + "client list.\n"
                    + "If no such client exists, nothing will be shown.\n"
                    + "Parameters: INDEX (must be a positive integer)\n"
                    + "Example usage : " + COMMAND_WORD + " 2";

    private Index index;

    /**
     * Constructor for the view client command.
     */
    public ViewClientCommand(ClientContainsIdPredicate predicate) {
        try {
            this.index = Index.fromOneBased(predicate.getId());
        } catch (IndexOutOfBoundsException ex) {
            this.index = Index.fromOneBased(Integer.MAX_VALUE);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Client> lastShownList = model.getFilteredClientList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client client = lastShownList.get(index.getZeroBased());
        return new CommandResult(String.format(Messages.MESSAGE_VIEW_CLIENT, client.getId(), index.getOneBased()),
                CommandType.VIEW, client, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewClientCommand // instanceof handles nulls
                && index.equals(((ViewClientCommand) other).index)); // state check
    }
}
