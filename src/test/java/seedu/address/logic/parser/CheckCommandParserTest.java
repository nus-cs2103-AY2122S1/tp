package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CheckCommandParser.DEFAULT_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.DATE_FORMATTER;
import static seedu.address.logic.parser.ParserUtil.TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.parser.enums.EnumTypeOfCheck;
import seedu.address.model.reservation.ListContainsReservationPredicate;

class CheckCommandParserTest {

    private CheckCommandParser parser = new CheckCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDateTime_throwsParseException() {
        assertParseFailure(parser, "2021 01 01  1900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2021 01 01  19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2021/01/01  1900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2021/01/01  19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "20210101  1900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "20210101  19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01 01 2021  1900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01 01 2021  19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01/01/2021  1900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01/01/2021  19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01012021  1900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01012021  19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01-01-2021  1900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01-01-2021  19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        assertParseFailure(parser, "2021 01 01",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "20210101",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01 01 2021",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01/01/2021",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01012021",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "01-01-2021",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTime_throwsParseException() {
        assertParseFailure(parser, "19:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "19 00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "700",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDateTime_returnsCheckCommand() {
        LocalDate date = LocalDate.parse("2021-01-01", DATE_FORMATTER);
        LocalTime time = LocalTime.parse("1200", TIME_FORMATTER);
        EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.DateTime;

        // no leading and trailing whitespaces
        CheckCommand expectedCheckCommand =
                new CheckCommand(new ListContainsReservationPredicate(date, time, typeOfCheck));
        assertParseSuccess(parser, "2021-01-01 1200", expectedCheckCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 2021-01-01 \n \t 1200  \t", expectedCheckCommand);
    }

    @Test
    public void parse_validDateOnly_returnsCheckCommand() {
        LocalDate date = LocalDate.parse("2021-01-01", DATE_FORMATTER);
        EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.Date;

        // no leading and trailing whitespaces
        CheckCommand expectedCheckCommand =
                new CheckCommand(new ListContainsReservationPredicate(date, DEFAULT_TIME, typeOfCheck));
        assertParseSuccess(parser, "2021-01-01", expectedCheckCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 2021-01-01 \n \t ", expectedCheckCommand);
    }

    @Test
    public void parse_validTimeOnly_returnsCheckCommand() {
        LocalTime time = LocalTime.parse("1200", TIME_FORMATTER);
        EnumTypeOfCheck typeOfCheck = EnumTypeOfCheck.Time;

        // no leading and trailing whitespaces
        CheckCommand expectedCheckCommand =
                new CheckCommand(new ListContainsReservationPredicate(LocalDate.now(), time, typeOfCheck));
        assertParseSuccess(parser, "1200", expectedCheckCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \t 1200  \t", expectedCheckCommand);
    }
}
