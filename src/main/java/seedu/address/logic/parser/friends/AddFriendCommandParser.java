package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_ID;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.friends.AddFriendCommand;
import seedu.address.logic.commands.friends.FriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;

public class AddFriendCommandParser extends FriendCommandParser {
    private FriendId friendId;
    private FriendName friendName;

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddFriendCommand
     * and returns an AddFriendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FriendCommand parse(String args) throws ParseException {
        //TODO: Parse the command and check for id, and (optional) name
        ArgumentMultimap argMultimap;

        if (args.contains(FLAG_FRIEND_NAME.toString())) {
            argMultimap = ArgumentTokenizer.tokenize(args, FLAG_FRIEND_ID, FLAG_FRIEND_NAME);
            friendName = ParserUtil.parseFriendName(argMultimap.getValue(FLAG_FRIEND_NAME).get());
        } else {
            argMultimap = ArgumentTokenizer.tokenize(args, FLAG_FRIEND_ID);
            friendName = AddFriendCommand.DEFAULT_FRIEND_NAME;
        }

        if (!arePrefixesPresent(argMultimap, FLAG_FRIEND_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FriendCommand.MESSAGE_USAGE));
        }

        friendId = ParserUtil.parseFriendId(argMultimap.getValue(FLAG_FRIEND_ID).get());
        return new AddFriendCommand(new Friend(friendId, friendName));
    }

}
