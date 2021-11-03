package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Name;
import seedu.address.model.summary.Summary;

/**
 * Deletes a contact identified using its displayed index from the address book.
 */
public class DeleteCommandName extends DeleteCommand {

    private final Name targetName;

    public DeleteCommandName(Name targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Contact contactToDelete = findContact(model);
        model.deleteContact(contactToDelete);
        Summary summary = new Summary(model.getAddressBook());
        return new CommandResult(String.format(MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete), summary);
    }

    /**
     * This method finds the contact object for Delete Command to delete
     * @param model the current address book model
     * @return Returns the contact to be deleted (if any)
     * @throws CommandException if the contact does not exist
     */
    private Contact findContact(Model model) throws CommandException {
        List<Contact> contactList = model.getFilteredContactList();

        for (Contact contact : contactList) {
            String fullName = contact.getName().fullName;
            if (fullName.equals(targetName.fullName.trim())) {
                return contact;
            }
        }
        throw new CommandException(String.format(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_NAME,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommandName // instanceof handles nulls
                && targetName.equals(((DeleteCommandName) other).targetName)); // state check
    }
}
