package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCommand.MESSAGE_USAGE));
        }

        return new GetCommand(new FriendIdMatchesKeywordPredicate(trimmedArgs));
    }

}
