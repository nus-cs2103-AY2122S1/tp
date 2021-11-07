package seedu.address.logic.commands.friends;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_FRIEND;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_UNLINK;

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

public class UnlinkFriendCommand extends Command {

    public static final String COMMAND_WORD = "--unlink";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_FRIEND + " " + FLAG_UNLINK + "FRIEND_ID " + FLAG_GAME + "GAME_NAME\n"
            + "Example: "
            + CMD_FRIEND + " " + FLAG_UNLINK + "Draco " + FLAG_GAME + "Valorant";

    private final FriendId friendId;
    private final GameId gameId;

    /**
     * Constructor for UnlinkFriendCommand.
     */
    public UnlinkFriendCommand(FriendId friendId, GameId gameId) {
        requireAllNonNull(friendId, gameId);

        this.friendId = friendId;
        this.gameId = gameId;
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
        Friend friendToUnlink = model.getFriend(friendId);
        Game game = model.getGame(gameId);
        if (!friendToUnlink.hasGameAssociation(game)) {
            throw new CommandException(Messages.MESSAGE_GAME_NOT_ASSOCIATED);
        }

        model.unlinkFriend(friendToUnlink, game);
        model.updateFilteredAndSortedFriendsList(Model.PREDICATE_SHOW_ALL_FRIENDS);

        return new CommandResult(generateSuccessMessage(friendToUnlink), CommandType.FRIEND_UNLINK);
    }

    /**
     * Generates a success message referencing the friend and the games unlinked.
     */
    public String generateSuccessMessage(Friend friend) {
        return "Unlinked friend from game - FRIEND_ID: " + friend.getFriendId() + ", GAME_ID: " + gameId;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnlinkFriendCommand)) {
            return false;
        }

        // state check
        UnlinkFriendCommand e = (UnlinkFriendCommand) other;
        return friendId.equals(e.friendId)
                && gameId.equals(e.gameId);
    }
}
