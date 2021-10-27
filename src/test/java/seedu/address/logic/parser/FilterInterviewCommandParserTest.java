package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterInterviewCommand;
import seedu.address.logic.commands.FilterInterviewFutureCommand;
import seedu.address.logic.commands.FilterInterviewPastCommand;

public class FilterInterviewCommandParserTest {

    private FilterInterviewCommandParser parser = new FilterInterviewCommandParser();
    private String argPast = "past";
    private String argFuture = "future";

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // blank input
        assertParseFailure(parser, "      ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterInterviewCommand.MESSAGE_USAGE));

        // empty input
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterInterviewCommand.MESSAGE_USAGE));

        // Not "past" or "future"
        assertParseFailure(parser, "test", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterInterviewCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterInterviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterInterviewCommand() {
        // no leading and trailing whitespaces
        assertParseSuccess(parser, argPast, new FilterInterviewPastCommand());
        assertParseSuccess(parser, argFuture, new FilterInterviewFutureCommand());

        // leading and trailing whitespaces
        assertParseSuccess(parser, "  " + argPast + "   ", new FilterInterviewPastCommand());
        assertParseSuccess(parser, "  " + argFuture + "   ", new FilterInterviewFutureCommand());
    }
}
