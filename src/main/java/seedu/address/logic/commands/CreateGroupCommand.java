package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;

public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "creategroup";
    public static final String MESSAGE_SUCCESS = "New group created";
    public static final String MESSAGE_ERROR = "Group not created. Be sure to use the exact names of group members";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists";

    private final ArrayList<Person> members;
    private GroupName groupName;
    private final boolean validCommand;

    /**
     * Creates a CreateGroupCommand to create the specified {@code Group}
     */
    public CreateGroupCommand(GroupName groupName, ArrayList<Person> members, boolean validCommand) {
        this.groupName = groupName;
        this.members = members;
        this.validCommand = validCommand;
    }

    /**
     * Returns CommandResult object with message representing successful creation of group.
     * If not a valid command, returns CommandResult object with error message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult object to depict status of group creation.
     * @throws CommandException If model is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!validCommand) {
            return new CommandResult(MESSAGE_ERROR);
        }
        Group group = new Group(groupName, members);
        model.addGroup(group);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
