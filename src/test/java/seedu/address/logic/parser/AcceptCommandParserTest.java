package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AcceptCommand;

public class AcceptCommandParserTest {
    private AcceptCommandParser parser = new AcceptCommandParser();

    @Test
    public void parse_validArgs_returnsAcceptCommand() {
        assertParseSuccess(parser, "1", new AcceptCommand(INDEX_FIRST_APPLICATION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "!", String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "100000000000000000",
                String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-9", String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a/4", String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_noParameterPresent_failure() {
        String expectedMessage = String.format(
                MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE);

        // command word only
        assertParseFailure(parser, "", expectedMessage);

        // white space only
        assertParseFailure(parser, "         ", expectedMessage);

        // no parameter
        assertParseFailure(parser, PREAMBLE_NON_EMPTY, expectedMessage);

        // unsupported parameter
        assertParseFailure(parser, "!!!!" + PREFIX_COMPLETION, expectedMessage);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        String expectedMessage = String.format(
                MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                AcceptCommand.MESSAGE_USAGE);

        assertParseFailure(parser, PREAMBLE_NON_EMPTY + "4", expectedMessage);
    }
}
