package seedu.notor.logic.commands.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonExecutor;
import seedu.notor.logic.executors.person.PersonUntagExecutor;
import seedu.notor.model.tag.Tag;

/**
 * Edits the details of an existing person in Notor.
 */
public class PersonUntagCommand extends PersonCommand {

    public static final String COMMAND_WORD = "untag";
    public static final List<String> COMMAND_WORDS = Arrays.asList("untag", "ut");

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " 1 " + COMMAND_WORD
            + ": Removes tags from the person identified by the index number used in the displayed person"
            + "list, if they exist. You may list multiple tags separated by commas \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG ...]  \n"
            + "Example: " + PersonCommand.COMMAND_WORD + " 1 " + COMMAND_WORD
            + PREFIX_TAG + "important";

    public static final String MESSAGE_NO_TAGS = "At least one tag must be provided.";

    public static final String MESSAGE_TAG_REQUIREMENTS = "Tags are required to be alphanumeric";

    private final PersonExecutor executor;

    /**
     *
     * @param index
     * @param tags
     */
    public PersonUntagCommand(Index index, Set<Tag> tags) {
        super(index);
        requireAllNonNull(index, tags);
        this.executor = new PersonUntagExecutor(index, tags);
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
        if (!(other instanceof PersonUntagCommand)) {
            return false;
        }

        // state check
        PersonUntagCommand e = (PersonUntagCommand) other;
        return super.equals(other) && executor.equals(e.executor);
    }
}
