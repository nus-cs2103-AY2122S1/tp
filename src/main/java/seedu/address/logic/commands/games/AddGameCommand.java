package seedu.address.logic.commands.games;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.game.Game;

public class AddGameCommand extends Command {
    public static final String COMMAND_WORD = "--add";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_GAME + " " + FLAG_ADD + "GAME_ID\n"
            + "Example: "
            + CMD_GAME + " " + FLAG_ADD + "Valorant";
    public static final String MESSAGE_SUCCESS_ADD_GAME = "Added game - GAME_ID: %1$s";
    public static final String MESSAGE_DUPLICATE_GAME = "A game with the same GAME_ID"
            + " already exists in the gitGud games list.";

    private final Game toAdd;

    /**
     * Constructor for AddGameCommand that takes in the game to add as the argument.
     *
     * @param game The game to be added.
     */
    public AddGameCommand(Game game) {
        requireNonNull(game);
        this.toAdd = game;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGameWithId(toAdd.getGameId())) {
            throw new CommandException(MESSAGE_DUPLICATE_GAME);
        }

        model.addGame(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS_ADD_GAME, toAdd), CommandType.GAME_ADD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGameCommand // instanceof handles nulls
                && toAdd.equals(((AddGameCommand) other).toAdd));
    }
}
