package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 *
 */
public class NoteCommand extends Command {
    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Opened note to Person: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Person: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the notes of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing notes will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    private final Index index;

    /**
     * @param index Index of the person in currently displayed person list to edit note for.
     */
    public NoteCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        return new CommandResult(generateSuccessMessage(personToEdit), false, true, personToEdit, false);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_NOTE_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }
        // state check
        NoteCommand e = (NoteCommand) other;
        return index.equals(e.index);
    }
}
