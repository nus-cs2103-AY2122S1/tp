package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FriendsList;
import seedu.address.model.GamesList;
import seedu.address.model.Model;

/**
 * Clears the gitGud friends and games list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_SUCCESS = "Cleared gitGud friends and games list";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFriendsList(new FriendsList());
        model.setGamesList(new GamesList());
        return new CommandResult(MESSAGE_SUCCESS, CommandType.CLEAR);
    }
}

