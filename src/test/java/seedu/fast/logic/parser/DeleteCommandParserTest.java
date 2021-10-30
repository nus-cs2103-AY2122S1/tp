package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.DeleteCommand;

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
    public void parse_validArgsSingle_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(new Index[] {INDEX_FIRST_PERSON}));
    }

    @Test
    public void parse_invalidArgsSingle_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsMultiple_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 2 3",
                new DeleteCommand(new Index[] {INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON}));
    }

    @Test
    public void parse_invalidArgsMultiple_throwsParseException() {
        assertParseFailure(parser, "2 a 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_rangeArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1-3",
                new DeleteCommand(new Index[] {INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON}));

        assertParseSuccess(parser, "1 - 3",
                new DeleteCommand(new Index[] {INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON}));
    }

    @Test
    public void parse_sameRangeArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "3-3",
                new DeleteCommand(new Index[] {INDEX_THIRD_PERSON}));
    }

    @Test
    public void parse_reversedRangeArgs_throwsParseException() {
        assertParseFailure(parser, "10-8", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "6-5", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidRangeArgs_throwsParseException() {
        assertParseFailure(parser, "a-5", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-5", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1-", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1--3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));

    }
}
