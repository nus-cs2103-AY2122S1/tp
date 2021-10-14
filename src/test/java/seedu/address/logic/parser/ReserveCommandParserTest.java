package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReserveCommand;
import seedu.address.model.person.Phone;

class ReserveCommandParserTest {
    private ReserveCommandParser parser = new ReserveCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        ReserveCommand expected = new ReserveCommand(
                new Phone("98765432"),
                2,
                LocalDateTime.parse("2021-11-11 2030", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));

        assertParseSuccess(parser, "2 p/98765432 at/2021-11-11 2030", expected);
    }

    @Test
    public void parse_missingField_failure() {
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                ReserveCommand.MESSAGE_USAGE
        );

        // missing phone
        assertParseFailure(
                parser,
                "2 at/2021-11-11 2000",
                expectedMessage
        );

        // missing number of people
        assertParseFailure(
                parser,
                "p/98765432 at/2021-11-11 2000",
                expectedMessage
        );

        // missing date time
        assertParseFailure(
                parser,
                "2 p/98765432",
                expectedMessage
        );
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid number of people
        assertParseFailure(
                parser,
                "0 p/98765432 at/2021-11-11 2000",
                ParserUtil.MESSAGE_INVALID_NUMBER_OF_PEOPLE
        );

        // invalid phone number
        assertParseFailure(
                parser,
                "1 p/911a at/2021-11-11 2000",
                Phone.MESSAGE_CONSTRAINTS
        );

        // invalid date time format
        assertParseFailure(
                parser,
                "1 p/98765432 at/2021/11/11 20:00",
                ParserUtil.MESSAGE_INVALID_DATE_TIME_FORMAT
        );
    }
}
