package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.POSITIVE_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.MAX_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkAssignmentCommand;

public class MarkAssignmentCommandParserTest {
    private final MarkAssignmentCommandParser parser = new MarkAssignmentCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser, POSITIVE_INDEX,
                new MarkAssignmentCommand(INDEX_FIRST_ASSIGNMENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsAtLimit_returnsMarkAssignmentCommand() {
        assertParseSuccess(parser, Integer.toString(ParserUtil.MAX_INDEX_LIMIT),
                new MarkAssignmentCommand(MAX_INDEX));
    }

    @Test
    public void parse_invalidOverLimitArgs_throwsParseException() {
        assertParseFailure(parser, Integer.toString(ParserUtil.MAX_INDEX_LIMIT + 1),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAssignmentCommand.MESSAGE_USAGE));
    }
}
