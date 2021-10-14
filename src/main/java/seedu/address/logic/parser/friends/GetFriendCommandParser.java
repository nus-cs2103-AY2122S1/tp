package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_SPACE;

import seedu.address.logic.commands.friends.GetFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendIdMatchesKeywordPredicate;

/**
 * Parses input arguments and creates a new GetCommand object
 */
public class GetFriendCommandParser implements Parser<GetFriendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetCommand
     * and returns a GetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetFriendCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_FRIEND_SPACE);

        if (argMultimap.getValue(FLAG_FRIEND_SPACE).isPresent()) {
            String keyword = argMultimap.getValue(FLAG_FRIEND_SPACE).get();
            if (keyword.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFriendCommand.MESSAGE_USAGE));
            }
            return new GetFriendCommand(new FriendIdMatchesKeywordPredicate(keyword));
        }

        // TODO add functionality for FLAG_GAME in the next iteration

        // default to friendId if there are no recognizable flags
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFriendCommand.MESSAGE_USAGE));
        }
        return new GetFriendCommand(new FriendIdMatchesKeywordPredicate(trimmedArgs));
    }

}
