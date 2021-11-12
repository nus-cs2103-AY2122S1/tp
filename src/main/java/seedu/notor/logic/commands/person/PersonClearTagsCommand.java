package seedu.notor.logic.commands.person;

import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonClearTagsExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

/**
 * Clears all tags of a person in Notor.
 */
public class PersonClearTagsCommand extends PersonCommand {

    public static final String COMMAND_WORD = "cleartags";
    public static final List<String> COMMAND_WORDS = Arrays.asList("cleartags", "ct");

    private static final String COMMAND_DESCRIPTION =
            ": Clears all tags from the person specified.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: none\n"
            + "Example: " + PersonCommand.COMMAND_WORD + " 1 /" + COMMAND_WORD;

    private final PersonExecutor executor;

    /**
     * @param index Index of the person
     */
    public PersonClearTagsCommand(Index index) {
        super(index);
        this.executor = new PersonClearTagsExecutor(index);
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
        if (!(other instanceof PersonClearTagsCommand)) {
            return false;
        }

        // state check
        PersonClearTagsCommand e = (PersonClearTagsCommand) other;
        return super.equals(other) && executor.equals(e.executor);
    }
}
