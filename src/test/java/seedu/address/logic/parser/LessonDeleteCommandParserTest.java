package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LessonDeleteCommand;

class LessonDeleteCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonDeleteCommand.MESSAGE_USAGE);

    private LessonDeleteCommandParser parser = new LessonDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsLessonDeleteCommand() {
        assertParseSuccess(parser, "1 1",
                new LessonDeleteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_LESSON));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_negativeArgs_throwsParseException() {
        assertParseFailure(parser, "-2 1", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_singleArg_throwsParseException() {
        assertParseFailure(parser, "2 ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_aboveTwoIndicesParseException() {
        assertParseFailure(parser, "1 2 3 4 5", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a 1", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidArgs2_throwsParseException() {
        assertParseFailure(parser, "a abdag", MESSAGE_INVALID_INDEX);
    }
}
