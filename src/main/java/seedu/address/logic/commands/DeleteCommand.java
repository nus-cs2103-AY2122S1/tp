package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NONEXISTENT_CLIENT_ID;
import static seedu.address.commons.util.StringUtil.PERSON_DELIMITER;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the client id used in the displayed person list.\n"
            + "Parameters: "
            + "CLIENT ID "
            + "\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final List<ClientId> clientIds;

    public DeleteCommand(List<ClientId> clientIds) {
        this.clientIds = clientIds;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<ClientId> distinctClientIds = clientIds.stream().distinct().collect(Collectors.toList());

        List<Person> personToDelete;
        try {
            personToDelete = model.deletePersonByClientIds(distinctClientIds);
        } catch (PersonNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_CLIENT_ID, e.getMessage()));
        }

        String personString = StringUtil.joinListToString(personToDelete, PERSON_DELIMITER);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && clientIds.equals(((DeleteCommand) other).clientIds)); // state check
    }
}
