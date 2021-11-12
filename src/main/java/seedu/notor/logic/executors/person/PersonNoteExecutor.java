package seedu.notor.logic.executors.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.person.Person;

/**
 * Executor for a PersonNoteCommand.
 */
public class PersonNoteExecutor extends PersonExecutor {
    public static final String MESSAGE_OPEN_NOTE_SUCCESS = "Opened note to Person: %1$s";


    /**
     * Constructor for a PersonNoteExecutor instance.
     *
     * @param index Index of the person to add a note to.
     *
     */
    public PersonNoteExecutor(Index index) {
        super(index);
        requireAllNonNull(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        checkPersonList();
        Person storedPerson = super.getPerson();
        return new CommandResult(generateSuccessMessage(storedPerson), false, true, false, storedPerson);
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_OPEN_NOTE_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonNoteExecutor)) {
            return false;
        }

        PersonNoteExecutor e = (PersonNoteExecutor) other;

        // state check
        return super.equals(other);
    }
}
