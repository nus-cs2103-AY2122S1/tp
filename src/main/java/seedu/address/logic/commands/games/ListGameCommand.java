package seedu.address.logic.commands.games;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;
import static seedu.address.logic.parser.CliSyntax.FLAG_LIST;

import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameIdContainsKeywordPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListGameCommand extends Command {

    public static final String COMMAND_WORD = "--list";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_GAME + " " + FLAG_LIST + "[KEYWORD]\n"
            + "Example: "
            + CMD_GAME + " " + FLAG_GET + "Valorant";
    public static final String MESSAGE_SUCCESS_PREPEND = "Listed all games";
    public static final String MESSAGE_UNKNOWN_PREDICATE = "Unknown search filter entered";

    private final Predicate<Game> predicate;

    public ListGameCommand(Predicate<Game> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (predicate instanceof GameIdContainsKeywordPredicate) {
            GameIdContainsKeywordPredicate gameIdContainsKeywordPredicate =
                    (GameIdContainsKeywordPredicate) predicate;
            model.updateFilteredGamesList(gameIdContainsKeywordPredicate);
            return new CommandResult(getMessageSuccess(), CommandType.GAME_LIST);
        }
        // ListCommand initialized with unknown predicate
        throw new CommandException(MESSAGE_UNKNOWN_PREDICATE);
    }

    /**
     * Returns success message to display after running list
     *
     * @return String containing success message
     */
    public String getMessageSuccess() throws CommandException {
        if (predicate instanceof GameIdContainsKeywordPredicate) {
            GameIdContainsKeywordPredicate gameIdContainsKeywordPredicate =
                    (GameIdContainsKeywordPredicate) predicate;
            String keyword = gameIdContainsKeywordPredicate.getKeyword();
            String messageEnd = keyword.isEmpty()
                    ? ""
                    : " matching filter - KEYWORD: " + keyword;
            return MESSAGE_SUCCESS_PREPEND + messageEnd;
        }
        throw new CommandException(MESSAGE_UNKNOWN_PREDICATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListGameCommand // instanceof handles nulls
                && predicate.equals(((ListGameCommand) other).predicate)); // state check
    }
}
