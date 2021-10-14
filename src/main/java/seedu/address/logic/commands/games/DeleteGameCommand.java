package seedu.address.logic.commands.games;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.game.GameId;


public class DeleteGameCommand extends Command {
    public static final String COMMAND_WORD = "--delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the game identified by the game id"
            + ".\n"
            + "Parameters: GAME_ID (must be an existing game id)\n"
            + "Example: " + COMMAND_WORD + " CSGO";
    public static final String MESSAGE_DELETE_GAME_SUCCESS = "Deleted Game: %1$s";
    private GameId gameToDeleteId;

    /**
     * Command to delete a friend using the unique FRIEND_ID.
     * @param gameId The friend id to identify the friend to be deleted.
     */
    public DeleteGameCommand(GameId gameId) {
        requireNonNull(gameId);
        gameToDeleteId = gameId;
    }

    /**
     * Executes the {@Code game --delete} command.
     * @param model {@code Model} which the command should operate on.
     * @return The resulting {@Code CommandResult} from deleting the game.
     * @throws CommandException Thrown when the game does not exist.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasGameWithId(gameToDeleteId)) {
            model.deleteGame(gameToDeleteId);
            return new CommandResult(String.format(MESSAGE_DELETE_GAME_SUCCESS, gameToDeleteId),
                    CommandType.GAME_DELETE);
        } else {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_GAME_ID);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGameCommand // instanceof handles nulls
                && gameToDeleteId.equals(((DeleteGameCommand) other).gameToDeleteId)); // state
    }
}
