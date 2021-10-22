package seedu.notor.logic.executors.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.group.Group;
import seedu.notor.model.person.Person;

/**
 * Executor for a PersonCreateCommand.
 */
public class PersonCreateExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person person;

    /**
     * Constructor for a PersonCreateExecutor instance.
     *
     * @param index Always null value for this command. Ignored for operations.
     * @param person Person to be created.
     */
    public PersonCreateExecutor(Index index, Person person) {
        super(index);
        requireNonNull(person);
        this.person = person;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        if (model.hasPerson(person)) {
            throw new ExecuteException(MESSAGE_DUPLICATE_PERSON);
        }

        model.createPerson(person);

        if (index != null) {
            if (model.getFilteredGroupList().size() > index.getOneBased()) {
                Group group = model.getFilteredGroupList().get(index.getZeroBased());
                group.addPerson(person);
                person.addGroup(group);
            } else {
                // TODO: stub error message, this is supposed to be for when index is out of bounds.
                throw new ExecuteException("Index is out of bounds");
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, person));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCreateExecutor)) {
            return false;
        }

        PersonCreateExecutor e = (PersonCreateExecutor) other;

        // state check
        return super.equals(other) && person.equals(e.person);
    }
}
