package seedu.address.logic.parser.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_EDIT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;

import seedu.address.logic.commands.friends.EditFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditFriendCommandParser implements Parser<EditFriendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditFriendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_EDIT, FLAG_FRIEND_NAME);

        FriendId friendToEditFriendId;

        if (!ParserUtil.areFlagsPresent(argMultimap, FLAG_EDIT) || argMultimap.getValue(FLAG_EDIT).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFriendCommand.MESSAGE_USAGE));
        }

        friendToEditFriendId = ParserUtil.parseFriendId(argMultimap.getValue(FLAG_EDIT).get());

        EditFriendCommand.EditFriendDescriptor editFriendDescriptor = new EditFriendCommand.EditFriendDescriptor();

        if (argMultimap.getValue(FLAG_FRIEND_NAME).isPresent()) {
            editFriendDescriptor.setFriendName(ParserUtil.parseFriendName(argMultimap.getValue(FLAG_FRIEND_NAME)
                    .get()));
        }

        if (!editFriendDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditFriendCommand.MESSAGE_NOT_EDITED);
        }

        return new EditFriendCommand(friendToEditFriendId, editFriendDescriptor);
    }
}
