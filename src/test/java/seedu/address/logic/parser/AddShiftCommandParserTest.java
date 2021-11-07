package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.AddShiftCommand;
import seedu.address.model.person.Name;

public class AddShiftCommandParserTest {
    private static final LocalDate START_DATE = LocalDate.of(2021, 10, 1);
    private static final LocalDate DEFAULT_END_DATE = START_DATE.plusDays(6);
    private static final LocalDate END_DATE = LocalDate.of(2021, 11, 1);

    private AddShiftCommandParser parser = new AddShiftCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongFormat_throwsParseException() {
        assertParseFailure(parser, "addShift -i 1 d/mon-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "addShift -i 1 d/MON-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));

        //Date format must exactly follow xxxx-xx-xx
        assertParseFailure(parser, "addShift -i 1 d/monday-1 da/2021-1-12",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "addShift -i 1 d/monday-1 da/2021-10-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_missing_throwsParseException() {
        assertParseFailure(parser, "addShift", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "addShift d/monday-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "addShift n/testName d/monday-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "addShift i/1 d/monday-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void prefix_duplicate_throwsParseException() {
        assertParseFailure(parser, "addShift -i 1 -n testingName d/monday-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "addShift -n testingName -i 1 d/monday-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        AddShiftCommand expectedNameCommand = new AddShiftCommand(null, new Name("testing"),
                "monday-1", DateTimeUtil.getDisplayedDateArray()[0],
                DateTimeUtil.getDisplayedDateArray()[1]);
        AddShiftCommand expectedIndexCommand = new AddShiftCommand(Index.fromOneBased(1), null,
                "monday-1", DateTimeUtil.getDisplayedDateArray()[0],
                DateTimeUtil.getDisplayedDateArray()[1]);
        AddShiftCommand expectedGivenStartDayCommand = new AddShiftCommand(Index.fromOneBased(1), null,
                "monday-1", START_DATE, DEFAULT_END_DATE);
        AddShiftCommand expectedGivenStartEndDayCommand = new AddShiftCommand(Index.fromOneBased(1), null,
                "monday-1", START_DATE, END_DATE);
        assertParseSuccess(parser, " -n testing d/monday-1", expectedNameCommand);
        assertParseSuccess(parser, " -i 1 d/monday-1", expectedIndexCommand);
        assertParseSuccess(parser, " -i 1 d/monday-1 da/2021-10-01", expectedGivenStartDayCommand);
        assertParseSuccess(parser, " -i 1 d/monday-1 da/2021-10-01 da/2021-11-01",
                expectedGivenStartEndDayCommand);
    }
}
