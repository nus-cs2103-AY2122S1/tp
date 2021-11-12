package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_FRIEND;
import static seedu.address.logic.parser.CliSyntax.FLAG_DELETE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.FriendId;

public class DeleteFriendCommand extends Command {
    public static final String COMMAND_WORD = "--delete";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_FRIEND + " " + FLAG_DELETE + "FRIEND_ID\n"
            + "Example: "
            + CMD_FRIEND + " " + FLAG_DELETE + "Draco";
    public static final String MESSAGE_DELETE_FRIEND_SUCCESS = "Deleted friend - FRIEND_ID: %1$s";

    private final FriendId friendToDeleteId;

    /**
     * Command to delete a friend using the unique FRIEND_ID.
     *
     * @param friendId The friend id to identify the friend to be deleted.
     */
    public DeleteFriendCommand(FriendId friendId) {
        requireNonNull(friendId);
        friendToDeleteId = friendId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasFriendWithId(friendToDeleteId)) {
            model.deleteFriend(friendToDeleteId);
            return new CommandResult(String.format(MESSAGE_DELETE_FRIEND_SUCCESS,
                    friendToDeleteId), CommandType.FRIEND_DELETE);
        } else {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteFriendCommand // instanceof handles nulls
                && friendToDeleteId.equals(((DeleteFriendCommand) other).friendToDeleteId)); // state check
    }
}
