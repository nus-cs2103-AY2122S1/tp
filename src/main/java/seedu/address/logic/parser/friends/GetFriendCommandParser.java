package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;

import seedu.address.logic.commands.friends.GetFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;

/**
 * Parses input arguments and creates a new GetFriendCommand object
 */
public class GetFriendCommandParser implements Parser<GetFriendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetFriendCommand
     * and returns a GetFriendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetFriendCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_GET);

        if (!ParserUtil.areFlagsPresent(argMultimap, FLAG_GET)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFriendCommand.MESSAGE_USAGE));
        }

        String keyword = argMultimap.getValue(FLAG_GET).get().trim();
        if (!FriendId.isValidFriendId(keyword)) {
            throw new ParseException(FriendId.MESSAGE_INVALID_CHARACTERS);
        }

        return new GetFriendCommand(new FriendId(keyword));
    }

}
