package seedu.address.logic.commands.friends;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.UserName;

/**
 * Links an existing friend to an existing game, associating them.
 */

public class LinkFriendCommand extends Command {

    public static final String COMMAND_WORD = "--link";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Links a game and the associated in-game username "
            + "to a friend. \n"
            + "Parameters: --friend FRIEND_ID --game GAME_NAME --user IN_GAME_USERNAME...\n"
            + "Example: " + COMMAND_WORD + " --friend Draco --game Valorant --user SmurfLord";

    private final FriendId friendId;
    private final GameId gameId;
    private final UserName userName;

    /**
     * Constructor for LinkFriendCommand.
     */
    public LinkFriendCommand(FriendId friendId, GameId gameId, UserName userName) {
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

        // Obtain a Friend object from the model that matches friendId
        Friend friendToEdit = model.getFriend(friendId);
        Game gameToLink = model.getGame(gameId);
        GameFriendLink gameFriendLink = new GameFriendLink(gameToLink.getGameId(), friendToEdit.getFriendId(),
                userName);

        model.linkFriend(friendToEdit, gameFriendLink);
        model.updateFilteredFriendsList(Model.PREDICATE_SHOW_ALL_FRIENDS);

        return new CommandResult(generateSuccessMessage(friendToEdit), CommandType.FRIEND_LINK);
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
        if (!(other instanceof LinkFriendCommand)) {
            return false;
        }

        // state check
        LinkFriendCommand e = (LinkFriendCommand) other;
        return friendId.equals(e.friendId)
                && gameId.equals(e.gameId)
                && userName.equals(e.userName);
    }
}
