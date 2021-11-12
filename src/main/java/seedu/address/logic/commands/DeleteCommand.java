package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.FullPersonCard;
import seedu.address.ui.UiManager;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameter: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        checkDisplayAfterDelete(lastShownList);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

    /**
     * Updates the display window once a particular person is deleted
     * @param lastShownList The list of people in the address book
     */
    public void checkDisplayAfterDelete(List<Person> lastShownList) {
        if (targetIndex.getOneBased() < FullPersonCard.getDisplayedIndex()) {
            FullPersonCard.setDisplayedIndex(FullPersonCard.getDisplayedIndex() - 1);
            UiManager.displayFunction();
        } else if (targetIndex.getOneBased() == FullPersonCard.getDisplayedIndex()) {
            if (targetIndex.getOneBased() == lastShownList.size() + 1) {
                FullPersonCard.setDisplayedIndex(FullPersonCard.getDisplayedIndex() - 1);
                UiManager.displayFunction();
            }
            UiManager.displayFunction();
        }
    }
}
