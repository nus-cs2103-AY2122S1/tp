package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAME;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.friend.Friend;
import seedu.address.model.game.Game;

/**
 * A utility class for Friend.
 */
public class FriendUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Friend friend) {
        return AddCommand.COMMAND_WORD + " " + getFriendDetails(friend);
    }

    /**
     * Returns the part of command string for the given {@code friend}'s details.
     */
    public static String getFriendDetails(Friend friend) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_FRIEND_ID + friend.getFriendId().value + " ");
        sb.append(PREFIX_FRIEND_NAME + friend.getName().fullName + " ");
        friend.getGames().stream().forEach(
            game -> sb.append(PREFIX_GAME + game.gameName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFriendDescriptor}'s details.
     */
    public static String getEditFriendDescriptorDetails(EditCommand.EditFriendDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getFriendName().ifPresent(name -> sb.append(PREFIX_FRIEND_NAME).append(name.fullName).append(" "));
        descriptor.getFriendId().ifPresent(friendId -> sb.append(PREFIX_FRIEND_ID).append(friendId.value).append(" "));
        if (descriptor.getGames().isPresent()) {
            Set<Game> games = descriptor.getGames().get();
            if (games.isEmpty()) {
                sb.append(PREFIX_GAME);
            } else {
                games.forEach(game -> sb.append(PREFIX_GAME).append(game.gameName).append(" "));
            }
        }
        return sb.toString();
    }
}
