package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_CONTACTS;
import static seedu.address.model.Model.PREDICATE_HIDE_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.range.Range;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Deletes a contact identified using it's displayed index from the address book.
 */
public class CDeleteCommand extends Command {

    public static final String COMMAND_WORD = "cdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: INDEX[-INDEX] (must be a positive integer)\n"
            + "Example 1: " + COMMAND_WORD + " 1\n"
            + "Example 2: " + COMMAND_WORD + " 2-5";

    public static final String MESSAGE_DELETE_CONTACT_SUCCESS = "Deleted Contact: %1$s";

    private final Range targetRange;

    public CDeleteCommand(Index targetIndex) {
        this.targetRange = new Range(targetIndex, targetIndex);
    }

    public CDeleteCommand(Range targetRange) {
        this.targetRange = targetRange;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        Index startIndex = targetRange.getStart();
        Index endIndex = targetRange.getEnd();
        int end = endIndex.getZeroBased();
        if (end >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }
        if (startIndex.isMoreThan(endIndex)) {
            throw new CommandException(Messages.MESSAGE_START_MORE_THAN_END_INDEX);
        }

        String commandResult = "";
        int indexToDelete = startIndex.getZeroBased();
        // delete the same index starting from the start index, since after deleting a contact,
        // the remaining contacts with larger indexes will have their index decreased by 1. Hence,
        // the next bigger index will have the same index as the deleted contact.
        for (int i = indexToDelete; i <= end; i++) {
            Contact contactToDelete = lastShownList.get(indexToDelete);
            model.deleteContact(contactToDelete);
            commandResult += String.format(MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete);
            if (i != end) {
                commandResult += "\n";
            }
        }
        model.updateFilteredContactList(PREDICATE_HIDE_ALL_CONTACTS); // Hide first to update the contact cards.
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.updateFilteredEventList(PREDICATE_HIDE_ALL_EVENTS); // Hide first to update the event cards.
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(commandResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CDeleteCommand // instanceof handles nulls
                && targetRange.equals(((CDeleteCommand) other).targetRange)); // state check
    }
}
