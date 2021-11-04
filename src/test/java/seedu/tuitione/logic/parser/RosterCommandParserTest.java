package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tuitione.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;

import org.junit.jupiter.api.Test;

import seedu.tuitione.logic.commands.RosterCommand;

public class RosterCommandParserTest {

    private RosterCommandParser parser = new RosterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RosterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsRosterCommand() {
        // no leading and trailing whitespaces
        RosterCommand expectedRosterCommand = new RosterCommand(INDEX_SECOND_LESSON);
        assertParseSuccess(parser, "2", expectedRosterCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, "\n   2    \t", expectedRosterCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RosterCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0", MESSAGE_INVALID_INDEX);
    }

}
