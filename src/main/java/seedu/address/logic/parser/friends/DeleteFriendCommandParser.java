package seedu.address.logic.parser.friends;

import seedu.address.commons.core.index.*;
import seedu.address.logic.commands.*;
import seedu.address.logic.commands.friends.*;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.*;
import seedu.address.model.friend.*;

import static seedu.address.commons.core.Messages.*;
import static seedu.address.logic.parser.CliSyntax.*;

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
