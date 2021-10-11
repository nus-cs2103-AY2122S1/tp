package seedu.address.logic.commands.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.executors.exceptions.ExecuteException;
import seedu.address.logic.executors.person.PersonNoteExecutor;
import seedu.address.model.person.Note;

/**
 *
 */
public class PersonNoteCommand extends PersonCommand {
    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the notes of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing notes will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "[NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Likes to swim.";

    private final PersonNoteExecutor executor;

    /**
     * @param index Index of the person to edit note for.
     * @param note New note to be updated to.
     */
    public PersonNoteCommand(Index index, Note note) {
        super(index);
        requireAllNonNull(index, note);
        this.executor = new PersonNoteExecutor(index, note);
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
