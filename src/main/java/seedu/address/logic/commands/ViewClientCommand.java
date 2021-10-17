package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.List;
import java.util.function.Predicate;

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
                    + "If no such client exists, nothing will be shown\n"
                    + "Parameters: INDEX (must be a positive integer)"
                    + "Example usage : "
                    + COMMAND_WORD
                    + " 20 ";

    private final ClientContainsIdPredicate predicate;
    private Index index;

    /**
     * Constructor for the view client command.
     */
    public ViewClientCommand(ClientContainsIdPredicate predicate) {
        this.predicate = predicate;
        try {
            this.index = Index.fromOneBased(predicate.getId());
        } catch (IndexOutOfBoundsException ioobe) {
            this.index = Index.fromOneBased(Integer.MAX_VALUE);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        List<Client> lastShownList = model.getFilteredClientList();
        System.out.println(this.index.getZeroBased());
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Predicate<Client> clientPredicate = (client -> client.equals(clientToEdit));
        model.updateFilteredClientList(clientPredicate);
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
