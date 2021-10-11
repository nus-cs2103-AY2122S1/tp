package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.gamefriendlink.GameFriendLink;
import seedu.address.model.friend.gamefriendlink.UserName;
import seedu.address.model.game.GameId;

/**
 * Links an existing friend to an existing game, associating them.
 */

public class LinkCommand extends Command {

    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Links game(s) and the associated in-game username(s) "
            + "for each game to a friend. \n"
            + "Parameters: FRIEND_ID --g GAME_NAME:IN_GAME_USERNAME...\n"
            + "Example: " + COMMAND_WORD + " Draco --g Valorant:SmurfLord";

    private final FriendId friendId;
    private final GameId gameId;
    private final UserName userName;

    /**
     * Constructor for LinkCommand.
     */
    public LinkCommand(FriendId friendId, GameId gameId, UserName userName) {
        requireAllNonNull(friendId, gameId, userName);

        this.friendId = friendId;
        this.gameId = gameId;
        this.userName = userName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasFriendWithId(friendId)) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
        }
        if (!model.hasGameWithId(gameId)) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_GAME_ID);
        }

        GameFriendLink gameFriendLink = new GameFriendLink(gameId, friendId, userName);
        List<Friend> lastShownFriendList = model.getFilteredFriendsList();
        // Obtain a Friend object from the model tha matches friendId
        Friend friendToEdit = lastShownFriendList
                .stream()
                .filter(friend -> friend.getFriendId().equals(friendId))
                .findFirst()
                .get();

        model.linkFriend(friendToEdit, gameFriendLink);
        model.updateFilteredFriendsList(Model.PREDICATE_SHOW_ALL_FRIENDS);

        return new CommandResult(generateSuccessMessage(friendToEdit));
    }

    /**
     * Generates a success message referencing the friend and the games linked to him.
     */
    public String generateSuccessMessage(Friend friend) {
        return friend.getFriendId() + " is now linked to " + gameId + " with username " + userName + ".";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkCommand)) {
            return false;
        }

        // state check
        LinkCommand e = (LinkCommand) other;
        return friendId.equals(e.friendId)
                && gameId.equals(e.gameId)
                && userName.equals(e.userName);
    }
}
