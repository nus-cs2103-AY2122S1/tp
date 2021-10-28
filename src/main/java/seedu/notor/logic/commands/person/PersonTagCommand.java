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
import seedu.notor.logic.executors.person.PersonTagExecutor;
import seedu.notor.model.tag.Tag;

/**
 * Edits the details of an existing person in Notor.
 */
public class PersonTagCommand extends PersonCommand {

    public static final String COMMAND_WORD = "tag";
    public static final List<String> COMMAND_WORDS = Arrays.asList("tag", "t");
    public static final String MESSAGE_NO_TAGS = "At least one tag must be provided.";
    public static final String MESSAGE_TAG_REQUIREMENTS = "Tags are required to be alphanumeric";

    private static final String COMMAND_DESCRIPTION =
            ": Adds tags to the person identified by theindex number used in the displayed person list, if they do "
                    + "not already have them. You may list multiple tags separated by commas.\n";

    public static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]  \n"
            + "Example: " + PersonCommand.COMMAND_WORD
            + " 1 /" + COMMAND_WORD + " "
            + PREFIX_TAG + "important, needsSupport";

    private final PersonExecutor executor;

    /**
     * Constructor for a PersonTagCommandInstance.
     *
     * @param index Index of person to add tags to.
     * @param tags Tags to be added to specified person.
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
