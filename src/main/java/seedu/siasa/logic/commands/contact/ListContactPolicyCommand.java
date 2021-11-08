package seedu.siasa.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.core.Messages.MESSAGE_POLICIES_LIST_EMPTY;

import java.util.List;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.CommandResult;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.PolicyIsOwnedByPredicate;

/**
 * Finds and lists all contacts in SIASA whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ListContactPolicyCommand extends Command {

    public static final String COMMAND_WORD = "contactpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all policies that belongs to the contact"
        + "with the specified index.\n"
        + "Parameters: KEYWORD INDEX\n"
        + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_LIST_CONTACT_POLICY_SUCCESS =
        "Listed all %1$s policies belonging to contact %2$s";

    private final Index contactId;

    public ListContactPolicyCommand(Index contactId) {
        this.contactId = contactId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (contactId.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contact = lastShownList.get(contactId.getZeroBased());
        model.updateFilteredPolicyList(new PolicyIsOwnedByPredicate(contact));

        if (model.getFilteredPolicyList().size() > 0) {
            return new CommandResult(
                    String.format(
                            MESSAGE_LIST_CONTACT_POLICY_SUCCESS,
                            model.getFilteredPolicyList().size(),
                            contactId.getOneBased()));
        } else {
            return new CommandResult(MESSAGE_POLICIES_LIST_EMPTY);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ListContactPolicyCommand // instanceof handles nulls
            && contactId.equals(((ListContactPolicyCommand) other).contactId)); // state check
    }
}
