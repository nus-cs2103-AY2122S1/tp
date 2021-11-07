package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.SetShiftTimeCommand;
import seedu.address.model.person.Name;

public class SetShiftTimeCommandParserTest {
    private static final LocalDate START_DATE = LocalDate.of(2021, 10, 1);
    private static final LocalDate DEFAULT_END_DATE = START_DATE.plusDays(6);
    private static final LocalDate END_DATE = LocalDate.of(2021, 11, 1);

    private SetShiftTimeCommandParser parser = new SetShiftTimeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetShiftTimeCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_missing_throwsParseException() {
        assertParseFailure(parser, "setShiftTime", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetShiftTimeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "setShiftTime d/monday-1 st/19:00-20:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetShiftTimeCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_duplicate_throwsParseException() {
        assertParseFailure(parser, "setShiftTime -i 1 -n testingName d/monday-1 st/19:00-20:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetShiftTimeCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_wrongFormat_throwsParseException() {
        assertParseFailure(parser, "setShiftTime -i 1 d/monday-1 st/19:00-20:00 da/2021-1-11",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetShiftTimeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "setShiftTime -i 1 d/monday-1 st/19:00-20:00 da/2021-11-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetShiftTimeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        LocalTime[] times = new LocalTime[]{LocalTime.of(17, 0), LocalTime.of(18, 0)};
        SetShiftTimeCommand expectedNameCommand = new SetShiftTimeCommand(null, new Name("testing"),
                "monday-1", times, DateTimeUtil.getDisplayedDateArray()[0],
            DateTimeUtil.getDisplayedDateArray()[1]);
        SetShiftTimeCommand expectedIndexCommand = new SetShiftTimeCommand(Index.fromOneBased(1), null,
                "monday-1", times, DateTimeUtil.getDisplayedDateArray()[0],
                DateTimeUtil.getDisplayedDateArray()[1]);
        SetShiftTimeCommand expectedOneDateCommand = new SetShiftTimeCommand(Index.fromOneBased(1), null,
                "monday-1", times, START_DATE, DEFAULT_END_DATE);
        SetShiftTimeCommand expectedTwoDateCommand = new SetShiftTimeCommand(Index.fromOneBased(1), null,
                "monday-1", times, START_DATE, END_DATE);
        assertParseSuccess(parser, " -n testing d/monday-1 st/17:00-18:00", expectedNameCommand);

        assertParseSuccess(parser, " -i 1 d/monday-1 st/17:00-18:00", expectedIndexCommand);
        assertParseSuccess(parser, " -i 1 d/monday-1 st/17:00-18:00 da/2021-10-01", expectedOneDateCommand);
        assertParseSuccess(parser, " -i 1 d/monday-1 st/17:00-18:00 da/2021-10-01 da/2021-11-01",
                expectedTwoDateCommand);
    }
}
