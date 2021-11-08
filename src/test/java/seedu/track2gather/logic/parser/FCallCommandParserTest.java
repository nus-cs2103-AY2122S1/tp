package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.track2gather.logic.commands.FCallCommand;

public class FCallCommandParserTest {

    private FCallCommandParser parser = new FCallCommandParser();

    @Test
    public void parse_validArgs_returnsFCallCommand() {
        assertParseSuccess(parser, "1", new FCallCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FCallCommand.MESSAGE_USAGE));
    }
}
