package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.io.IOException;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetDefaultShiftTimingsCommand;
import seedu.address.storage.DefaultShiftTimingsStorage;

public class SetDefaultShiftTimingsCommandParserTest {

    private SetDefaultShiftTimingsCommandParser parser = new SetDefaultShiftTimingsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() throws IOException {
        DefaultShiftTimingsStorage.load();
        DefaultShiftTimingsStorage.reset();
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));
    }

    @Test
    public void parse_validArgs_returnsSetDefaultShiftTimingsCommand() {
        // Normal input
        SetDefaultShiftTimingsCommand expectedCommand1 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(22, 0)}
        );
        assertParseSuccess(parser, " 10:00 16:00 17:00 22:00", expectedCommand1);

        // Input with trailing white spaces
        assertParseSuccess(parser, "     10:00    16:00   17:00  22:00    ", expectedCommand1);

        // Morning shift starts at noon
        SetDefaultShiftTimingsCommand expectedCommand2 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(12, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(22, 0)}
        );
        assertParseSuccess(parser, " 12:00 16:00 17:00 22:00", expectedCommand2);

        // Afternoon shift starts at noon
        SetDefaultShiftTimingsCommand expectedCommand3 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(8, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(22, 0)}
        );
        assertParseSuccess(parser, " 08:00 11:00 12:00 22:00", expectedCommand3);

        // Morning shift ends at the same time afternoon shift starts
        SetDefaultShiftTimingsCommand expectedCommand4 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(12, 0),
                LocalTime.of(16, 0),
                LocalTime.of(16, 0),
                LocalTime.of(22, 0)}
        );
        assertParseSuccess(parser, " 12:00 16:00 16:00 22:00", expectedCommand4);

    }

    @Test
    public void parse_invalidArgs_returnsHelpMessage() {
        // If the number of timings != 4
        assertParseFailure(parser, " 10:00    16:00   17:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));
        assertParseFailure(parser, " 10:00 16:00 17:00 18:00 19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));

        // If the timings are out of order
        assertParseFailure(parser, " 18:00 16:00 17:00 22:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));
        assertParseFailure(parser, " 16:00 16:00 17:00 16:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));

        // Shifts which start and end at the same time (i.e. zero duration)
        assertParseFailure(parser, " 10:00 10:00 17:00 22:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));
        assertParseFailure(parser, " 10:00 16:00 22:00 22:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));

        // If the morning shift is starts after noon
        assertParseFailure(parser, " 12:01 16:00 17:00 18:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));

        // If the afternoon shift starts before noon
        assertParseFailure(parser, " 10:00 11:00 11:30 22:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDefaultShiftTimingsCommand.HELP_MESSAGE));
    }
}
