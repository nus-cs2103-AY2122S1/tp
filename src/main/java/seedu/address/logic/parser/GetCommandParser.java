package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND;

import seedu.address.logic.commands.GetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendIdMatchesKeywordPredicate;

/**
 * Parses input arguments and creates a new GetCommand object
 */
public class GetCommandParser implements Parser<GetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetCommand
     * and returns a GetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_FRIEND);

        if (argMultimap.getValue(FLAG_FRIEND).isPresent()) {
            String keyword = argMultimap.getValue(FLAG_FRIEND).get();
            if (keyword.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCommand.MESSAGE_USAGE));
            }
            return new GetCommand(new FriendIdMatchesKeywordPredicate(keyword));
        }

        // TODO add functionality for FLAG_GAME in the next iteration

        // default to friendId if there are no recognizable flags
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCommand.MESSAGE_USAGE));
        }
        return new GetCommand(new FriendIdMatchesKeywordPredicate(trimmedArgs));
    }

}
