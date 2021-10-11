package seedu.address.logic.parser.friends;

import static seedu.address.logic.parser.CliSyntax.FLAG_LIST;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;

import seedu.address.logic.commands.friends.ListFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListFriendCommandParser implements Parser<ListFriendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListFriendCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args + FLAG_POSTFIX, FLAG_LIST);

        if (argMultimap.getValue(FLAG_LIST).isPresent()) {
            return new ListFriendCommand(
                    new FriendIdContainsKeywordPredicate(argMultimap.getValue(FLAG_LIST).get()));
        }
        // no tags present, default to friend search
        return new ListFriendCommand(new FriendIdContainsKeywordPredicate(args));
    }

}
