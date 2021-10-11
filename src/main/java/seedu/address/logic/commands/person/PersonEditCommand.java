package seedu.address.logic.commands.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.executors.exceptions.ExecuteException;
import seedu.address.logic.executors.person.PersonEditExecutor;
import seedu.address.logic.executors.person.PersonExecutor;

/**
 * Edits the details of an existing person in the address book.
 */
public class PersonEditCommand extends PersonCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

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
}
