package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;


public class DeleteFriendCommand extends Command {
    public static final String COMMAND_WORD = "--delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the person identified by the friend id"
            + ".\n"
            + "Parameters: FRIEND_ID (must be an existing friend id)\n"
            + "Example: " + COMMAND_WORD + " Draco";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    private FriendId friendToDeleteId;
    private Friend friendToDelete;

    /**
     * Command to delete a friend using the unique FRIEND_ID.
     * @param friendId The friend id to identify the friend to be deleted.
     */
    public DeleteFriendCommand(FriendId friendId) {
        requireNonNull(friendId);
        friendToDeleteId = friendId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasFriendId(friendToDeleteId)) {
            model.deleteFriend(friendToDeleteId);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, friendToDeleteId));
        } else {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteFriendCommand // instanceof handles nulls
                && friendToDeleteId.equals(((DeleteFriendCommand) other).friendToDeleteId)); // state
                // check
    }

    /**
     * Finds and returns a friend if the friendId is found inside the list.
     * @param list The list to search for the friend.
     * @param friendId The friendId used to search for the friend.
     * @return The friend with the friend id.
     * @throws CommandException Thrown when no friend with the friendId is found inside the list.
     */
    public Friend findFriendInList(List<Friend> list, FriendId friendId) throws CommandException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFriendId().equals(friendId)) {
                return list.get(i);
            }
        }
        throw new CommandException(Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
    }
}
