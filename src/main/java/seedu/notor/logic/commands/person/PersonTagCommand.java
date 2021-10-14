package seedu.notor.logic.commands.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonEditExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;
import seedu.notor.logic.executors.person.PersonTagExecutor;
import seedu.notor.model.tag.Tag;

/**
 * Edits the details of an existing person in Notor.
 */
public class PersonTagCommand extends PersonCommand {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]  \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "important";

    public static final String MESSAGE_NOT_EDITED = "At least one tag must be provided.";

    private final PersonExecutor executor;

    /**
     * @param index Index of the person in the filtered person list to edit.
     * @param personTagDescriptor Details to edit the person with.
     */
    public PersonTagCommand(Index index, Set<Tag> tags) {
        super(index);
        requireAllNonNull(index, tags);
        this.executor = new PersonTagExecutor(index, tags);
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
        if (!(other instanceof PersonTagCommand)) {
            return false;
        }

        // state check
        PersonTagCommand e = (PersonTagCommand) other;
        return super.equals(other) && executor.equals(e.executor);
    }
}
