package seedu.notor.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonClearNoteExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

/**
 * Clears the Note of the person by the given index.
 */
public class PersonClearNoteCommand extends PersonCommand {
    public static final String COMMAND_WORD = "clearnote";
    public static final List<String> COMMAND_WORDS = Arrays.asList("clearnote", "cn");

    private static final String COMMAND_DESCRIPTION = ": Clears the note of the person specified.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none\n"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final PersonExecutor executor;

    /**
     * Constructor for a PersonClearNoteCommand.
     *
     * @param index Index of the person to clear note.
     */
    public PersonClearNoteCommand(Index index) {
        super(index);
        requireNonNull(index);
        this.executor = new PersonClearNoteExecutor(index);
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
        if (!(other instanceof PersonClearNoteCommand)) {
            return false;
        }
        // state check
        PersonClearNoteCommand e = (PersonClearNoteCommand) other;
        return executor.equals(e.executor);
    }
}
