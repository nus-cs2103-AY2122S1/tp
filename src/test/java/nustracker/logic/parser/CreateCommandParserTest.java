package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.commands.CommandTestUtil.DATE_DESC_FINAL;
import static nustracker.logic.commands.CommandTestUtil.DATE_DESC_TEST;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EVENTDATE_DESC;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EVENTTIME_DESC;
import static nustracker.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static nustracker.logic.commands.CommandTestUtil.NAME_DESC_FINAL;
import static nustracker.logic.commands.CommandTestUtil.NAME_DESC_TEST;
import static nustracker.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static nustracker.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static nustracker.logic.commands.CommandTestUtil.TIME_DESC_FINAL;
import static nustracker.logic.commands.CommandTestUtil.TIME_DESC_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTDATE_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTTIME_TEST;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nustracker.logic.commands.CreateCommand;
import nustracker.model.event.Event;
import nustracker.model.event.EventDate;
import nustracker.model.event.EventName;
import nustracker.model.event.EventTime;
import nustracker.testutil.EventBuilder;

class CreateCommandParserTest {

    private CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder()
                .withName(VALID_EVENTNAME_TEST).withDate(VALID_EVENTDATE_TEST).withTime(VALID_EVENTTIME_TEST)
                .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TEST + DATE_DESC_TEST + TIME_DESC_TEST,
                new CreateCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_FINAL + NAME_DESC_TEST + DATE_DESC_TEST
                        + TIME_DESC_TEST, new CreateCommand(expectedEvent));

        // multiple dates - last date accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TEST + DATE_DESC_FINAL + DATE_DESC_TEST
                + TIME_DESC_TEST, new CreateCommand(expectedEvent));

        // multiple times - last time accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_TEST + DATE_DESC_TEST + TIME_DESC_FINAL
                + TIME_DESC_TEST, new CreateCommand(expectedEvent));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero participants
        Event expectedEvent = new EventBuilder()
                .withName(VALID_EVENTNAME_TEST).withDate(VALID_EVENTDATE_TEST).withTime(VALID_EVENTTIME_TEST)
                .build();
        assertParseSuccess(parser, NAME_DESC_TEST + DATE_DESC_TEST + TIME_DESC_TEST,
                new CreateCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_EVENTNAME_TEST + DATE_DESC_TEST + TIME_DESC_TEST,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_TEST + VALID_EVENTDATE_TEST + TIME_DESC_TEST,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, NAME_DESC_TEST + DATE_DESC_TEST + VALID_EVENTTIME_TEST,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_EVENTNAME_TEST + VALID_EVENTDATE_TEST + VALID_EVENTTIME_TEST,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DATE_DESC_TEST + TIME_DESC_TEST,
                EventName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_TEST + INVALID_EVENTDATE_DESC + TIME_DESC_TEST,
                EventDate.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, NAME_DESC_TEST + DATE_DESC_TEST + INVALID_EVENTTIME_DESC,
                EventTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EVENTDATE_DESC + TIME_DESC_TEST,
                EventName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INVALID_NAME_DESC + DATE_DESC_TEST + TIME_DESC_TEST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
    }

}
