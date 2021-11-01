package seedu.address.logic.commands.games;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

public class GetGameCommand extends Command {

    public static final String COMMAND_WORD = "--get";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_GAME + " " + FLAG_GET + "GAME_ID\n"
            + "Example: "
            + CMD_GAME + " " + FLAG_GET + "Minecraft";
    public static final String MESSAGE_GAME_FULL_INFORMATION = "Retrieved full game information - GAME_ID: %1$s";

    private final GameId gameId;

    public GetGameCommand(GameId gameId) {
        this.gameId = gameId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasGameWithId(gameId)) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_GAME_ID_NOT_FOUND, gameId.value));
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
