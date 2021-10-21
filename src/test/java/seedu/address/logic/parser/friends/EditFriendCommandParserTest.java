package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.FLAG_EDIT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.model.friend.FriendId.MESSAGE_EMPTY_FRIEND_ID;
import static seedu.address.model.friend.FriendId.MESSAGE_INVALID_CHARACTERS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.EditFriendCommand;

public class EditFriendCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFriendCommand.MESSAGE_USAGE);
    private static final String COMMAND_EDIT_PREFIX = FLAG_POSTFIX.getFlag() + FLAG_EDIT;
    private EditFriendCommandParser parser = new EditFriendCommandParser();


    @Test
    public void parse_noFriendIdSpecified_failure() {
        // no friendId specified
        assertParseFailure(parser, FLAG_POSTFIX.getFlag() + FLAG_FRIEND_NAME + VALID_NAME_AMY,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidFriendId_failure() {
        // friendId no special characters
        assertParseFailure(parser, COMMAND_EDIT_PREFIX + "$2" + NAME_DESC_AMY, MESSAGE_INVALID_CHARACTERS);

        // friendId no spaces
        assertParseFailure(parser, COMMAND_EDIT_PREFIX + "James blunt" + NAME_DESC_AMY, MESSAGE_INVALID_CHARACTERS);
    }

    @Test
    public void parse_noFieldsUpdated_failure() {
        // no fields to update specified
        assertParseFailure(parser, COMMAND_EDIT_PREFIX + VALID_FRIEND_ID_AMY,
                EditFriendCommand.MESSAGE_NOT_EDITED);

        // no friendId and no field specified
        assertParseFailure(parser, FLAG_POSTFIX.getFlag() + FLAG_EDIT, MESSAGE_EMPTY_FRIEND_ID);
    }
}
