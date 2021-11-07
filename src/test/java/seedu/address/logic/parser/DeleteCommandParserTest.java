package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalContacts.AIRZONE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommandIndex;
import seedu.address.logic.commands.DeleteCommandName;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommandIndex() {
        assertParseSuccess(parser, "1", new DeleteCommandIndex(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_validArgs_returnsDeleteCommandName() {
        assertParseSuccess(parser, " n/  AIRZONE", new DeleteCommandName(AIRZONE.getName()));
    }

    @Test
    public void parse_invalidArgsName_throwsParseException() {
        assertParseFailure(parser, " n/  AIRZONE p/1234", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_inValidArgsIndex_throwsParseException() {
        assertParseFailure(parser, "1 n/  AIRZONE", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_inValidArgs_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.INVALID_INDEX));
    }
}
