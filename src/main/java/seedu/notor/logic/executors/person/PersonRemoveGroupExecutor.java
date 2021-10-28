package seedu.notor.logic.executors.person;

import static java.util.Objects.requireNonNull;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.person.Person;

/**
 * TODO: Add subgroup removing functionality from deprecated classes.
 */
public class PersonRemoveGroupExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "Removed person from %s";
    public static final String MESSAGE_NOT_IN_GROUP = "This person is not in the group";
    public static final String MESSAGE_GROUP_NOT_FOUND = "The group inputted is not found.";

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
        checkPersonList();
        requireNonNull(model);
        try {
            Person personToEdit = super.getPerson();
            Group group = model.findGroup(groupName);
            if (group == null) {
                throw new ExecuteException(MESSAGE_GROUP_NOT_FOUND);
            }
            if (groupName.contains("_")) {
                personToEdit.removeSubGroup((SubGroup) group);
            } else {
                personToEdit.removeSuperGroup(groupName);
            }
            group.removePerson(personToEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, groupName));
        } catch (ItemNotFoundException e) {
            throw new ExecuteException(MESSAGE_NOT_IN_GROUP);
        }
    }
}
