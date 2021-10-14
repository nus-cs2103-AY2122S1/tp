package seedu.address.logic.commands.games;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

public class GetGameCommand extends Command {

    public static final String COMMAND_FAMILY = "game";
    public static final String COMMAND_WORD = "--get";

    public static final String MESSAGE_USAGE = COMMAND_FAMILY + " " + COMMAND_WORD
            + ": Gets all the information about a game whose "
            + "GAME_ID matches the given keyword(s) exactly (case-insensitive) and displays them in a "
            + "easy-to-read format.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_FAMILY + " " + COMMAND_WORD + " Minecraft";
    public static final String MESSAGE_GAME_FULL_INFORMATION = "Showing the complete information of %1$s";

    private final GameId gameId;

    public GetGameCommand(GameId gameId) {
        this.gameId = gameId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGameId(gameId)) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_GAME_ID, gameId.value));
        }

        Game game = model.getGame(gameId);
        return new CommandResult(
                String.format(MESSAGE_GAME_FULL_INFORMATION, gameId.value), CommandType.GAME_GET, game);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetGameCommand // instanceof handles nulls
                && gameId.equals(((GetGameCommand) other).gameId)); // state check
    }
}
