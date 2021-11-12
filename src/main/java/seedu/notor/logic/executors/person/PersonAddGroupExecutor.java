package seedu.notor.logic.executors.person;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.CommandResult;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.exceptions.DuplicateItemException;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.person.Person;

/**
 * TODO: Add subgroup adding functionality from deprecated classes.
 */
public class PersonAddGroupExecutor extends PersonExecutor {
    public static final String MESSAGE_SUCCESS = "Added person to %s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This person is already in the group";
    public static final String MESSAGE_GROUP_NOT_FOUND = "The group inputted is not found.";

    private final String groupName;

    /**
     * Constructor for a PersonAddGroupExecutor instance.
     *
     * @param index Index of Person to perform operation on.
     * @param groupName Name of Group to be added to the Person.
     */
    public PersonAddGroupExecutor(Index index, String groupName) {
        super(index);
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute() throws ExecuteException {
        try {
            checkPersonList();
            Person person = super.getPerson();
            Group group = model.findGroup(groupName);
            if (group == null) {
                throw new ExecuteException(MESSAGE_GROUP_NOT_FOUND);
            }
            if (groupName.contains("_")) {
                person.addSubGroup((SubGroup) group);
            } else {
                person.addGroup(group);
            }
            group.addPerson(person);
            return new CommandResult(String.format(MESSAGE_SUCCESS, groupName));
        } catch (DuplicateItemException e) {
            throw new ExecuteException(MESSAGE_DUPLICATE_GROUP);
        }
    }
}
