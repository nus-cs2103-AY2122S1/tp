package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_DELETE;

import seedu.address.logic.commands.friends.DeleteFriendCommand;
import seedu.address.logic.commands.friends.FriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;

public class DeleteFriendCommandParser extends FriendCommandParser {
    private FriendId friendId;
    private ArgumentMultimap argMultimap;


    @Override
    public FriendCommand parse(String args) throws ParseException {
        try {
            argMultimap = ArgumentTokenizer.tokenize(args, FLAG_DELETE);
            friendId = ParserUtil.parseFriendId(argMultimap.getValue(FLAG_DELETE).get());
            return new DeleteFriendCommand(friendId);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFriendCommand.MESSAGE_USAGE), pe);
        }
    }
}
