package seedu.notor.logic.executors.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.person.Person;

/**
 * TODO: Add subgroup removing functionality from deprecated classes.
 */
public class PersonRemoveGroupExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "Removed person from %s";
    public static final String MESSAGE_NOT_IN_GROUP = "This person is not in the group";

    private final String groupName;

    /**
     * Constructor for a PersonRemoveGroupExecutor instance.
     *
     * @param index Index of the Person to remove a Group from.
     * @param groupName Name of the Group to be removed from the Person.
     */
    public PersonRemoveGroupExecutor(Index index, String groupName) {
        super(index);
        this.groupName = groupName;
    }

    @Override public CommandResult execute() throws ExecuteException {
        requireNonNull(model);
        try {
            Person personToEdit = super.getPerson();
            personToEdit.removeSuperGroup(groupName);
            model.findGroup(groupName).removePerson(personToEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, groupName));
        } catch (ItemNotFoundException e) {
            throw new ExecuteException(MESSAGE_NOT_IN_GROUP);
        }
    }
}
