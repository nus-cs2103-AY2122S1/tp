package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.summary.Summary;

/**
 * Deletes a contact identified using it's displayed index from the address book.
 */
public class DeleteCommandIndex extends DeleteCommand {

    private final Index targetIndex;

    public DeleteCommandIndex(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // if index is greater than list size, notify user
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        Summary summary = new Summary(model.getAddressBook());
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete), summary);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommandIndex // instanceof handles nulls
                && targetIndex.equals(((DeleteCommandIndex) other).targetIndex)); // state check
    }
}
