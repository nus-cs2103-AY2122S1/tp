package seedu.address.logic.parser.games;

import static seedu.address.logic.parser.CliSyntax.FLAG_LIST;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;

import seedu.address.logic.commands.games.ListGameCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.game.GameIdContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new ListGameCommand object
 */
public class ListGameCommandParser implements Parser<ListGameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListGameCommand
     * and returns a ListGameCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListGameCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args + FLAG_POSTFIX, FLAG_LIST);

        if (argMultimap.getValue(FLAG_LIST).isPresent()) {
            return new ListGameCommand(
                    new GameIdContainsKeywordPredicate(argMultimap.getValue(FLAG_LIST).get()));
        }
        // default to list all
        return new ListGameCommand(new GameIdContainsKeywordPredicate(""));
    }

}
