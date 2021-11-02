package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_FRIEND;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;

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

    public static final String COMMAND_WORD = "--get";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_FRIEND + " " + FLAG_GET + "\n"
            + "Example: "
            + CMD_FRIEND + " " + FLAG_GET + "Draco";
    public static final String MESSAGE_FRIEND_FULL_INFORMATION = "Retrieved full friend information - FRIEND_ID: %1$s";

    private final FriendId friendId;

    public GetFriendCommand(FriendId friendId) {
        this.friendId = friendId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasFriendWithId(friendId)) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_FRIEND_ID_NOT_FOUND, friendId.value));
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
