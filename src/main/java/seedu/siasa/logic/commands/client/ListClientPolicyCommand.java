package seedu.siasa.logic.commands.client;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.PolicyIsOwnedByPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ListClientPolicyCommand extends Command {

    public static final String COMMAND_WORD = "clientpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all policies that belongs to the client"
            + "with the specified index.\n"
            + "Parameters: KEYWORD INDEX\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_LIST_CLIENT_POLICY_SUCCESS = "Listed all %1$s policies belonging to user %2$s";

    private final Index clientId;

    public ListClientPolicyCommand(Index clientId) {
        this.clientId = clientId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (clientId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person client = lastShownList.get(clientId.getZeroBased());
        model.updateFilteredPolicyList(new PolicyIsOwnedByPredicate(client));
        return new CommandResult(
                String.format(
                        MESSAGE_LIST_CLIENT_POLICY_SUCCESS,
                        model.getFilteredPolicyList().size(),
                        clientId.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListClientPolicyCommand // instanceof handles nulls
                && clientId.equals(((ListClientPolicyCommand) other).clientId)); // state check
    }
}
