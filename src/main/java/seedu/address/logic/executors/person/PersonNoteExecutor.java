package seedu.address.logic.executors.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.executors.exceptions.ExecuteException;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

/**
 * Executor for a PersonNoteCommand.
 */
public class PersonNoteExecutor extends PersonExecutor {
    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Person: %1$s";

    private final Note note;

    /**
     * Constructor for a PersonNoteExecutor instance.
     *
     * @param index Index of the person to add a note to.
     * @param note Note to be added to the person.
     */
    public PersonNoteExecutor(Index index, Note note) {
        super(index);
        requireAllNonNull(index, note);
        this.note = note;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        Person storedPerson = super.getPerson();
        Person editedPerson = new Person(
                storedPerson.getName(), storedPerson.getPhone(), storedPerson.getEmail(), note, storedPerson.getTags());

        model.setPerson(storedPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !note.value.isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, personToEdit);
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
        return super.equals(other) && note.equals(e.note);
    }
}
