package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.MAX_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowAssignmentCommand;

public class ShowAssignmentCommandParserTest {

    private ShowAssignmentCommandParser parser = new ShowAssignmentCommandParser();

    @Test
    public void parse_validArgs_returnsShowAssignmentCommand() {
        assertParseSuccess(parser, "1", new ShowAssignmentCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsAtLimit_returnsShowAssignmentCommand() {
        assertParseSuccess(parser, Integer.toString(ParserUtil.MAX_INDEX_LIMIT),
                new ShowAssignmentCommand(MAX_INDEX));
    }

    @Test
    public void parse_invalidOverLimitArgs_throwsParseException() {
        assertParseFailure(parser, Integer.toString(ParserUtil.MAX_INDEX_LIMIT + 1),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowAssignmentCommand.MESSAGE_USAGE));
    }
}
