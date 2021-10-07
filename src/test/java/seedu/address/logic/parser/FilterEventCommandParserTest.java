package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterEventCommand;
import seedu.address.model.event.EventDateTimePredicate;

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
    public void parse_validArgsOnlyDate_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterEventCommand expectedFilterEventCommand =
                new FilterEventCommand(new EventDateTimePredicate(Arrays.asList("2021-9-1")));
        assertParseSuccess(parser, " d/2021-9-1", expectedFilterEventCommand);

        // multiple whitespaces
        assertParseSuccess(parser, "       d/2021-9-1      ", expectedFilterEventCommand);
    }

    @Test
    public void parse_validArgsDateAndTime_returnsFindCommand() {
        // no leading and trailing whitespaces
        FilterEventCommand expectedFilterEventCommand =
                new FilterEventCommand(new EventDateTimePredicate(Arrays.asList("2021-9-1", "0900")));
        assertParseSuccess(parser, " d/2021-9-1 t/0900", expectedFilterEventCommand);

        // only leading and trailing whitespaces (multiple spaces in between)
        assertParseSuccess(parser, "   \n \t    d/2021-9-1  \t \n  t/0900 \n  ", expectedFilterEventCommand);
    }
}
