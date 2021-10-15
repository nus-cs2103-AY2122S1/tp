package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;

/**
 * Gets and displays the complete information of a friend from the address book whose FRIEND_ID matches the given
 * argument keywords.
 * Keyword matching is case-insensitive.
 */
public class GetFriendCommand extends Command {

    public static final String COMMAND_FAMILY = "friend";
    public static final String COMMAND_WORD = "--get";

    public static final String MESSAGE_USAGE = COMMAND_FAMILY + " " + COMMAND_WORD
            + ": Gets all the information about a friend whose "
            + "FRIEND_ID matches the given keyword(s) exactly (case-insensitive) and displays them in a "
            + "easy-to-read format.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_FAMILY + " " + COMMAND_WORD + " Draco";
    public static final String MESSAGE_FRIEND_FULL_INFORMATION = "Showing the complete information of %1$s";

    private final FriendId friendId;

    public GetFriendCommand(FriendId friendId) {
        this.friendId = friendId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasFriendWithId(friendId)) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_FRIEND_ID, friendId.value));
        }

        Friend friend = model.getFriend(friendId);
        return new CommandResult(
                String.format(MESSAGE_FRIEND_FULL_INFORMATION, friendId.value), CommandType.FRIEND_GET, friend);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetFriendCommand // instanceof handles nulls
                && friendId.equals(((GetFriendCommand) other).friendId)); // state check
    }
}
