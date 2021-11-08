package seedu.notor.logic.commands.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonEditExecutor;
import seedu.notor.logic.executors.person.PersonExecutor;

/**
 * Edits the details of an existing person in Notor.
 */
public class PersonEditCommand extends PersonCommand {
    public static final String COMMAND_WORD = "edit";
    public static final List<String> COMMAND_WORDS = Arrays.asList("edit", "e");

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private static final String COMMAND_DESCRIPTION =
            ": Edits the details of the person specified by the index.\n"
                    + "Existing values will be overwritten by the input values.\n";

    private static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " INDEX /" + COMMAND_WORD
            + COMMAND_DESCRIPTION
            + "Parameters:"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL]\n"
            + "Example: " + PersonCommand.COMMAND_WORD
            + " 1 /" + COMMAND_WORD + " "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    private final PersonExecutor executor;

    /**
     * @param index Index of the person in the filtered person list to edit.
     * @param personEditDescriptor Details to edit the person with.
     */
    public PersonEditCommand(Index index, PersonEditExecutor.PersonEditDescriptor personEditDescriptor) {
        super(index);
        requireAllNonNull(index, personEditDescriptor);
        this.executor = new PersonEditExecutor(index, personEditDescriptor);
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
        if (!(other instanceof PersonEditCommand)) {
            return false;
        }

        // state check
        PersonEditCommand e = (PersonEditCommand) other;
        return super.equals(other) && executor.equals(e.executor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(executor);
    }
}
