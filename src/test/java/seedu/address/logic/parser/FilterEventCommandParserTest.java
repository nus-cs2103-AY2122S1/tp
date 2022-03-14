package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterEventCommand;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventDateTimePredicate;
import seedu.address.model.event.EventTime;

/**
 * Contains integration tests (interaction with the Model) for FilterEventCommandParser.
 */
public class FilterEventCommandParserTest {

    private FilterEventCommandParser parser = new FilterEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsOnlyTime_throwsParseException() {
        assertParseFailure(parser, "0900",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDate_throwsParseException() {
        // invalid month
        assertParseFailure(parser, " d/2021-20-10", EventDate.MESSAGE_CONSTRAINTS);

        // invalid day
        assertParseFailure(parser, " d/2021-10-32", EventDate.MESSAGE_CONSTRAINTS);

        // invalid month and day
        assertParseFailure(parser, " d/2021-20-32", EventDate.MESSAGE_CONSTRAINTS);

        // invalid year
        assertParseFailure(parser, " d/-2021-10-10", EventDate.MESSAGE_CONSTRAINTS);

        // Empty date
        assertParseFailure(parser, " d/", EventDate.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validDateInvalidTime_throwsParseException() {
        // invalid hour
        assertParseFailure(parser, " d/2021-9-9 t/2500", EventTime.MESSAGE_CONSTRAINTS);

        // invalid minute
        assertParseFailure(parser, " d/2021-9-9 t/2060", EventTime.MESSAGE_CONSTRAINTS);

        // invalid hour and minute
        assertParseFailure(parser, " d/2021-9-9 t/2560", EventTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgsOnlyDate_returnsFilterEventCommand() {
        // no leading and trailing whitespaces
        FilterEventCommand expectedFilterEventCommand =
                new FilterEventCommand(new EventDateTimePredicate(Arrays.asList("2021-9-1")));
        assertParseSuccess(parser, " d/2021-9-1", expectedFilterEventCommand);

        // multiple whitespaces
        assertParseSuccess(parser, "       d/2021-9-1      ", expectedFilterEventCommand);
    }

    @Test
    public void parse_validArgsDateAndTime_returnsFilterEventCommand() {
        // no leading and trailing whitespaces
        FilterEventCommand expectedFilterEventCommand =
                new FilterEventCommand(new EventDateTimePredicate(Arrays.asList("2021-9-1", "0900")));
        assertParseSuccess(parser, " d/2021-9-1 t/0900", expectedFilterEventCommand);

        // only leading and trailing whitespaces (multiple spaces in between)
        assertParseSuccess(parser, "   \n \t    d/2021-9-1  \t \n  t/0900 \n  ", expectedFilterEventCommand);
    }
}
