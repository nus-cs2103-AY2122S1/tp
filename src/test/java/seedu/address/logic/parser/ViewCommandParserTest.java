package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalContacts.AIRZONE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewCommandIndex;
import seedu.address.logic.commands.ViewCommandName;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommandIndex() {
        assertParseSuccess(parser, "1", new ViewCommandIndex(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_validArgs_returnsViewCommandName() {
        assertParseSuccess(parser, " n/  AIRZONE", new ViewCommandName(AIRZONE.getName()));
    }

    @Test
    public void parse_invalidArgsName_throwsParseException() {
        assertParseFailure(parser, " n/  AIRZONE p/1234", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_inValidArgsIndex_throwsParseException() {
        assertParseFailure(parser, "1 n/  AIRZONE", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_inValidArgs_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ParserUtil.MESSAGE_INVALID_INDEX));
    }
}
