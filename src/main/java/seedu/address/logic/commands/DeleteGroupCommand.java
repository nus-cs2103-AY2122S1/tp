package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

/**
 * Deletes a student identified using it's displayed index from the address book.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "deletegroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by its unique name.\n"
            + "Parameters: " + PREFIX_GROUP_NAME + "GROUPNAME (must be an ASCII string)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUP_NAME + "CS2103T";

    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted Group: %1$s";

    private final GroupName targetGroupName;

    public DeleteGroupCommand(GroupName targetGroupName) {
        this.targetGroupName = targetGroupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Group groupToDelete = model.getGroupByGroupName(targetGroupName);

        if (groupToDelete == null) {
            throw new CommandException(Messages.MESSAGE_GROUP_NOT_FOUND);
        }

        model.deleteGroup(groupToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGroupCommand // instanceof handles nulls
                && targetGroupName.equals(((DeleteGroupCommand) other).targetGroupName)); // state check
    }
}
