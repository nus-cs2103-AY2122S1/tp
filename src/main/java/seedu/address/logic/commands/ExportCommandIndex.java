package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.storage.ExportStorage;

/**
 * Exports a contact identified using it's displayed index from the address book.
 */
public class ExportCommandIndex extends ExportCommand {

    private final Index targetIndex;

    public ExportCommandIndex(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        // if index is greater than list size, notify user
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToExport = lastShownList.get(targetIndex.getZeroBased());
        ExportStorage.clearStorage();
        model.exportContact(contactToExport);
        return new CommandResult(String.format(MESSAGE_EXPORT_SUCCESS, contactToExport));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExportCommandIndex // instanceof handles nulls
            && targetIndex.equals(((ExportCommandIndex) other).targetIndex)); // state check
    }
}
