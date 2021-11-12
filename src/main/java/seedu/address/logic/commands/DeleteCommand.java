package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends UndoableCommand {

    public static final String COMMAND_ACTION = "Delete Student";

    public static final String COMMAND_WORD = "delete";

    public static final String COMMAND_PARAMETERS = "INDEX";

    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + COMMAND_PARAMETERS;

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Example: " + COMMAND_EXAMPLE;

    public static final String USER_TIP = "Try deleting a student using: \n"
            + COMMAND_WORD + " " + COMMAND_PARAMETERS + "\n"
            + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted student: %1$s";

    private Index targetIndex;

    private Person deletedPerson;

    /**
     * Constructs a Delete Command.
     */
    public DeleteCommand(Index targetIndex) {
        super(COMMAND_ACTION);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToDelete = CommandUtil.getPerson(lastShownList, targetIndex);

        targetIndex = setToDefinitiveIndex(personToDelete);

        model.deletePerson(personToDelete);
        deletedPerson = personToDelete;
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, personToDelete));
    }

    @Override
    protected Person undo() {
        requireNonNull(model);

        model.addPersonAtIndex(deletedPerson, targetIndex);
        return deletedPerson;
    }

    @Override
    protected Person redo() {
        requireNonNull(model);

        checkValidity(deletedPerson);

        model.deletePerson(deletedPerson);
        return deletedPerson;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
