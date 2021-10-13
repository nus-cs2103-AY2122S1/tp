package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FriendsList;
import seedu.address.model.Model;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

/**
 * Clears the gitGud friend's list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "gitGud friend's list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFriendsList(new FriendsList());
        // temporary use of clear command to test gameMainCard
        return new CommandResult(MESSAGE_SUCCESS, CommandType.GAME_GET, new Game(new GameId("csgo")));
        // return new CommandResult(MESSAGE_SUCCESS, CommandType.CLEAR);
    }
}

