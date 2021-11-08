package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.track2gather.logic.commands.SCallCommand;

public class SCallCommandParserTest {

    private SCallCommandParser parser = new SCallCommandParser();

    @Test
    public void parse_validArgs_returnsSCallCommand() {
        assertParseSuccess(parser, "1", new SCallCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SCallCommand.MESSAGE_USAGE));
    }
}
