package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;

import seedu.address.logic.commands.friends.AddFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;

public class AddFriendCommandParser implements Parser<AddFriendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddFriendCommand
     * and returns an AddFriendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddFriendCommand parse(String args) throws ParseException {
        // assign friend name
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, FLAG_ADD, FLAG_FRIEND_NAME);

        if (!ParserUtil.areFlagsPresent(argumentMultimap, FLAG_ADD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFriendCommand.MESSAGE_USAGE));
        }
        FriendName friendName = argumentMultimap.getValue(FLAG_FRIEND_NAME).isPresent()
                ? ParserUtil.parseFriendName(argumentMultimap.getValue(FLAG_FRIEND_NAME).get())
                : null;
        FriendId friendId = ParserUtil.parseFriendId(argumentMultimap.getValue(FLAG_ADD).get());
        return new AddFriendCommand(new Friend(friendId, friendName));
    }
}
