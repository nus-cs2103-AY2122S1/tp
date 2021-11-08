package seedu.address.model;

import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;

public class VersionedAddressBook extends AddressBook {
    private static final Logger logger = LogsCenter.getLogger(VersionedAddressBook.class);
    private ArrayList<ReadOnlyAddressBook> addressBookStateList = new ArrayList<>();

    private int currentStatePointer = 0;
    private int size = 1;

    /**
     * Returns the versioned address book instance.
     * @param readOnlyAddressBook The address book
     */
    public VersionedAddressBook(ReadOnlyAddressBook readOnlyAddressBook) {
        super(readOnlyAddressBook);
        addressBookStateList.add(currentStatePointer, readOnlyAddressBook.copyAddressBook());
    }

    /**
     * Undoes the command.
     */
    public void undo() throws CommandException {
        final boolean isEarliestAddressBook = currentStatePointer < 1;

        if (addressBookStateList.isEmpty() || isEarliestAddressBook) {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO);
        }

        currentStatePointer--;
        ReadOnlyAddressBook addressBook = addressBookStateList.get(currentStatePointer);
        logger.info("AddressBook: " + addressBook);
        resetData(addressBook);
    }

    /**
     * Redoes the command.
     */
    public void redo() throws CommandException {
        final boolean isMostRecentAddressBook = currentStatePointer >= size - 1;

        if (addressBookStateList.isEmpty() || isMostRecentAddressBook) {
            throw new CommandException(Messages.MESSAGE_INVALID_REDO);
        }
        currentStatePointer++;
        ReadOnlyAddressBook addressBook = addressBookStateList.get(currentStatePointer);
        logger.info("AddressBook: " + addressBook);
        resetData(addressBook);
    }

    /**
     * Updates address book state list whenever a command is executed(except undo command).
     */
    public void commitAddressBook() {
        deleteElementsAfterPointer(currentStatePointer);
        currentStatePointer++;
        size = currentStatePointer + 1;
        addressBookStateList.add(currentStatePointer, this.copyAddressBook());
    }


    //Solution adapted from
    //https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java
    /**
     * Deletes any commands in undo stack after the current point when a new command is added at that point.
     * @param undoPointer The current point where undo stack is at.
     */
    private void deleteElementsAfterPointer(int undoPointer) {
        if (size < 1) {
            return;
        }

        if (size > undoPointer + 1) {
            addressBookStateList.subList(undoPointer + 1, size).clear();
        }
    }
}
