package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.CLIENT_DELIMITER;
import static seedu.address.commons.util.StringUtil.COMMA_DELIMITER;
import static seedu.address.commons.util.StringUtil.joinListToString;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.Client.EditClientDescriptor;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.exceptions.DuplicateClientException;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by the client's ID. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + " CLIENT_ID... "
            + "<attribute>/CHANGED_VALUE...\n"
            + "Example: " + COMMAND_WORD + " " + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CLIENT_SUCCESS = "Edited Client: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This operation will result in a"
            + " duplicate in the address book.";
    public static final String MESSAGE_CHANGE_CLIENTID = "Client's ID cannot be changed.";

    private final List<ClientId> clientIds;
    private final EditClientDescriptor editClientDescriptor;

    /**
     * @param clientIds            of the client in the filtered client list to edit
     * @param editClientDescriptor details to edit the client with
     */
    public EditCommand(List<ClientId> clientIds, EditClientDescriptor editClientDescriptor) {
        requireNonNull(clientIds);
        requireNonNull(editClientDescriptor);

        this.clientIds = clientIds;
        this.editClientDescriptor = new EditClientDescriptor(editClientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<ClientId> distinctClientIds = clientIds.stream().distinct().collect(Collectors.toList());
        List<ClientId> invalidClientIds = distinctClientIds.stream()
            .filter(c -> !model.hasClientId(c))
            .collect(Collectors.toList());

        if (!invalidClientIds.isEmpty()) {
            String invalidClientIdsString = joinListToString(invalidClientIds, COMMA_DELIMITER);
            throw new CommandException(String.format(Messages.MESSAGE_NONEXISTENT_CLIENT_ID, invalidClientIdsString));
        }

        List<Client> editedClients;
        try {
            editedClients = model.setAllClients(clientIds, editClientDescriptor);
        } catch (DuplicateClientException de) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }


        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        String clientsString = joinListToString(editedClients, CLIENT_DELIMITER);
        return new CommandResult(String.format(MESSAGE_EDIT_CLIENT_SUCCESS, clientsString));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return clientIds.equals(e.clientIds)
                && editClientDescriptor.equals(e.editClientDescriptor);
    }

}
