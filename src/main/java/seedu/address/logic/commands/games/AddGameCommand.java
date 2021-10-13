package seedu.address.logic.commands.games;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.game.Game;

public class AddGameCommand extends Command {
    public static final String COMMAND_WORD = "--add";
    // add command messages
    public static final String MESSAGE_SUCCESS_ADD_GAME = "New game added - %1$s";
    public static final String MESSAGE_DUPLICATE_GAME = "A game with the same GAME_ID"
            + " already exists in the gitGud games list.";
    public static final String MESSAGE_USAGE = "To add a game: \n"
            + COMMAND_WORD
            + " GAME_ID: "
            + "Adds a game to the gitGud games list. \n"
            + "Parameters: "
            + "GAME_ID"
            + "Example: "
            + COMMAND_WORD + " "
            + "Valorant";
    private final Game toAdd;

    /**
     * Constructor for AddGameCommand that takes in the game to add as the argument.
     * @param game The game to be added.
     */
    public AddGameCommand(Game game) {
        requireNonNull(game);
        this.toAdd = game;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGameId(toAdd.getGameId())) {
            throw new CommandException(MESSAGE_DUPLICATE_GAME);
        }

        model.addGame(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS_ADD_GAME, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGameCommand // instanceof handles nulls
                && toAdd.equals(((AddGameCommand) other).toAdd));
    }
}
