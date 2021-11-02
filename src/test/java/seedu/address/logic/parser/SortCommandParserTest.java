package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommandName;
import seedu.address.logic.commands.SortCommandRating;


public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    // empty argument
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    // invalid argument: integer
    @Test
    public void parse_invalidArgInt_throwsParseException() {
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    // invalid argument: String
    @Test
    public void parse_invalidArgString_throwsParseException() {
        assertParseFailure(parser, "test", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    // valid argument: name
    @Test
    public void parse_validArg_returnsSortCommandName() {
        assertParseSuccess(parser, "name", new SortCommandName());
    }

    // valid argument: rating
    @Test
    public void parse_validArg_returnsSortCommandRating() {
        assertParseSuccess(parser, "rating", new SortCommandRating());
    }
}
