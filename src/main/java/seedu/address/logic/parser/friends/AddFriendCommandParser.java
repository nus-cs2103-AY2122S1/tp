package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
    private FriendId friendId;
    private FriendName friendName;

    /**
     * Parses the given {@code String} of arguments in the context of the AddFriendCommand
     * and returns an AddFriendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddFriendCommand parse(String args) throws ParseException {

        if (args.contains(FLAG_FRIEND_NAME.toString())) {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, FLAG_FRIEND_NAME);
            friendName = ParserUtil.parseFriendName(argMultimap.getValue(FLAG_FRIEND_NAME).get());
        } else {
            friendName = AddFriendCommand.DEFAULT_FRIEND_NAME;
        }

        friendId = getFriendId(args);
        return new AddFriendCommand(new Friend(friendId, friendName));
    }

    private FriendId getFriendId(String args) throws ParseException {
        String[] splitCommand = args.split(" ");
        try {
            String friendId = splitCommand[1];
            return ParserUtil.parseFriendId(friendId);
        } catch (IndexOutOfBoundsException e) {
            // TODO set a different message if preferred
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FriendId.MESSAGE_CONSTRAINTS));
        }
    }

}
