package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Name;


/**
 * Displays details of a contact identified using it's displayed name on the side panel.
 */
public class ViewCommandName extends ViewCommand {

    private final Name targetName;

    public ViewCommandName(Name targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        for (Contact contact : lastShownList) {
            String fullName = contact.getName().fullName;
            if (fullName.equals(targetName.fullName.trim())) {
                Contact selectedContact = contact;
                return new CommandResult(String.format(MESSAGE_VIEW_CONTACT_SUCCESS, selectedContact), selectedContact);
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_NAME);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommandName// instanceof handles nulls
                && targetName.equals(((ViewCommandName) other).targetName)); // state check
    }
}
