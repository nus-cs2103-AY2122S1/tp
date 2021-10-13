package seedu.notor.logic.commands.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonNoteExecutor;

/**
 *
 */
public class PersonNoteCommand extends PersonCommand {
    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the notes of the person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    private final PersonNoteExecutor executor;

    /**
     * @param index Index of the person to edit note for.
     *
     */
    public PersonNoteCommand(Index index) {
        super(index);
        requireAllNonNull(index);
        this.executor = new PersonNoteExecutor(index);
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        return executor.execute();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof PersonNoteCommand)) {
            return false;
        }
        // state check
        PersonNoteCommand e = (PersonNoteCommand) other;
        return executor.equals(e.executor);
    }
}
