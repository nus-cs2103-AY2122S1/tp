package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;

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
    private final HashMap<String, String> games;

    /**
     * Constructor for LinkCommand.
     */
    public LinkCommand(FriendId friendId, HashMap<String, String> games) {
        requireAllNonNull(friendId, games);

        this.friendId = friendId;
        this.games = games;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasFriendWithId(friendId)) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
        }

        // check if game(s) are in model, else throw exception

        List<Friend> lastShownList = model.getFilteredFriendsList();

        // get list of games from model

        Friend friendToEdit = lastShownList // obtain Friend object from the model that matches friendId
                .stream()
                .filter(friend -> friend.getFriendId().equals(friendId))
                .findFirst()
                .get();

        // update the game(s) in the model (since the usernames for them have been changed)

        model.linkFriend(friendToEdit, new HashSet<>());
        model.updateFilteredFriendsList(Model.PREDICATE_SHOW_ALL_FRIENDS);

        return new CommandResult(generateSuccessMessage(friendToEdit));
    }

    /**
     * Generates a success message referencing the friend and the games linked to him.
     */
    public String generateSuccessMessage(Friend friend) {
        String successMessage = friend.getFriendId() + " is now linked to: ";
        List<String> gameNames = new ArrayList<>(games.keySet());
        for (int i = 0; i < gameNames.size(); i++) {
            String gameName = gameNames.get(i);
            if (i == gameNames.size() - 1) {
                successMessage += gameName + " (" + games.get(gameName) + ")";
            } else {
                successMessage += gameName + " (" + games.get(gameName) + "), ";
            }
        }
        return successMessage;
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
                && games.equals(e.games);
    }
}
