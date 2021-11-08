package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.track2gather.logic.commands.TShiftCommand;

public class TShiftCommandParserTest {
    private final TShiftCommandParser parser = new TShiftCommandParser();

    @Test
    public void parse_negativeValues() {
        assertParseSuccess(parser, "-2", new TShiftCommand(-2));
        assertParseSuccess(parser, " -2", new TShiftCommand(-2));
        assertParseSuccess(parser, "-2 ", new TShiftCommand(-2));
        assertParseSuccess(parser, " -2 ", new TShiftCommand(-2));

        assertParseSuccess(parser, "-12345678", new TShiftCommand(-12345678));
    }

    @Test
    public void parse_positiveValues() {
        assertParseSuccess(parser, "2", new TShiftCommand(2));
        assertParseSuccess(parser, " 2", new TShiftCommand(2));
        assertParseSuccess(parser, "2 ", new TShiftCommand(2));
        assertParseSuccess(parser, " 2 ", new TShiftCommand(2));

        assertParseSuccess(parser, "12345678", new TShiftCommand(12345678));
    }

    @Test
    public void parse_invalidInputs() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "two", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-i ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, TShiftCommand.MESSAGE_USAGE));
    }
}
