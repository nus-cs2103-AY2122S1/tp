package seedu.notor.logic.commands.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonExecutor;
import seedu.notor.logic.executors.person.PersonNoteExecutor;

/**
 * Shows the Note of the person by the given index.
 */
public class PersonNoteCommand extends PersonCommand {
    public static final String COMMAND_WORD = "note";
    public static final List<String> COMMAND_WORDS = Arrays.asList("note", "n");

    private static final String COMMAND_DESCRIPTION =
            ": Allows user to edit the notes of the person specified by the index.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none\n"
            + "Example: "
            + PersonCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final PersonExecutor executor;

    /**
     * @param index Index of the person to edit note for.
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
