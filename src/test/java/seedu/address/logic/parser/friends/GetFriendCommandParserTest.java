package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.GetFriendCommand;
import seedu.address.model.friend.FriendId;

public class GetFriendCommandParserTest {

    private GetFriendCommandParser parser = new GetFriendCommandParser();

    @Test
    public void parse_emptyArgsWithNoFlag_throwsParseException() {
        String emptyArgs1 = "";
        String emptyArgs2 = "    ";
        String emptyArgs3 = "--get";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetFriendCommand.MESSAGE_USAGE);

        assertParseFailure(parser, emptyArgs1, expectedMessage);
        assertParseFailure(parser, emptyArgs2, expectedMessage);
        assertParseFailure(parser, emptyArgs3, expectedMessage);
    }

    @Test
    public void parse_emptyArgsWithFlag_throwsParseException() {
        String emptyArgs1 = " " + FLAG_GET.getFlag();
        String emptyArgs2 = " " + FLAG_GET.getFlag() + "          ";
        String expectedMessage = FriendId.MESSAGE_INVALID_CHARACTERS;

        assertParseFailure(parser, emptyArgs1, expectedMessage);
        assertParseFailure(parser, emptyArgs2, expectedMessage);
    }

    @Test
    public void parse_multipleWordArgsWithFlag_throwsParseException() {
        String emptyArgs = " " + FLAG_GET.getFlag() + "alice yeoh";
        String expectedMessage = FriendId.MESSAGE_INVALID_CHARACTERS;

        assertParseFailure(parser, emptyArgs, expectedMessage);
    }

    @Test
    public void parse_singleWordArgsWithFlag_correctCommand() {
        String validArgs = "drdisrespect";
        GetFriendCommand expectedCommand = new GetFriendCommand(new FriendId(validArgs));
        assertParseSuccess(parser, " " + FLAG_GET + " " + validArgs, expectedCommand);
    }
}
