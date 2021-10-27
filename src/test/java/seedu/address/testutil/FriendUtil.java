package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;

import seedu.address.logic.commands.friends.AddFriendCommand;
import seedu.address.logic.commands.friends.EditFriendCommand;
import seedu.address.model.friend.Friend;

/**
 * A utility class for Friend.
 */
public class FriendUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddFriendCommand(Friend friend) {
        return AddFriendCommand.COMMAND_WORD + " " + getFriendDetails(friend);
    }

    /**
     * Returns the part of command string for the given {@code friend}'s details.
     */
    public static String getFriendDetails(Friend friend) {
        StringBuilder sb = new StringBuilder();
        sb.append(friend.getFriendId().value + " ");
        sb.append(FLAG_FRIEND_NAME + friend.getFriendName().fullName + " ");
        friend.getGameFriendLinks().values().stream().forEach(
            game -> sb.append(FLAG_GAME + game.getGameId().value + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFriendDescriptor}'s details.
     */
    public static String getEditFriendDescriptorDetails(EditFriendCommand.EditFriendDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getFriendName().ifPresent(name -> sb.append(FLAG_FRIEND_NAME).append(name.fullName).append(" "));
        return sb.toString();
    }
}
