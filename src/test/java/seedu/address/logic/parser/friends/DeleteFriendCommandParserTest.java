package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.DeleteFriendCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteFriendCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteFriendCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteFriendCommandParserTest {

    private DeleteFriendCommandParser parser = new DeleteFriendCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteFriendCommand() {
        // TODO Update after delete command is updated
        // assertParseSuccess(parser, DeleteFriendCommand.COMMAND_WORD + " " + AMY.getFriendId().toString(),
        //         new DeleteFriendCommand(AMY.getFriendId()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, DeleteFriendCommand.COMMAND_WORD, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFriendCommand.MESSAGE_USAGE));
    }
}
