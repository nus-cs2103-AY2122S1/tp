package safeforhall.logic.parser.add;

import static safeforhall.logic.commands.CommandTestUtil.CAPACITY_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.CAPACITY_DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.DATE_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.DATE_DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_CAPACITY_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_VENUE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static safeforhall.logic.commands.CommandTestUtil.TIME_DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_CAPACITY_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_DATE_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_TIME_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VENUE_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VENUE_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VENUE_DESC_SWIM_TRAINING;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static safeforhall.testutil.TypicalEvents.SWIM_WO_RESIDENTS;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.add.AddEventCommand;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.Venue;
import safeforhall.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(SWIM_WO_RESIDENTS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, new AddEventCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_FOOTBALL_TRAINING + NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, new AddEventCommand(expectedEvent));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_SWIM_TRAINING + DATE_DESC_FOOTBALL_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, new AddEventCommand(expectedEvent));

        // multiple venues - last venue accepted
        assertParseSuccess(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_FOOTBALL_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, new AddEventCommand(expectedEvent));

        // multiple capacities - last capacity accepted
        assertParseSuccess(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING + CAPACITY_DESC_FOOTBALL_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, new AddEventCommand(expectedEvent));

        // multiple times - last time accepted
        assertParseSuccess(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_SWIM_TRAINING
                + VALID_DATE_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + VALID_DATE_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, expectedMessage);

        // missing time prefix
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + VALID_TIME_SWIM_TRAINING, expectedMessage);

        // missing venue prefix
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VALID_VENUE_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, expectedMessage);

        // missing capacity prefix
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + VALID_CAPACITY_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_EVENT_NAME_DESC
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, EventName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + INVALID_EVENT_DATE_DESC + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, EventDate.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + CAPACITY_DESC_SWIM_TRAINING + INVALID_TIME_DESC, EventTime.MESSAGE_CONSTRAINTS);

        // invalid venue
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + INVALID_VENUE_DESC
                + CAPACITY_DESC_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING, Venue.MESSAGE_CONSTRAINTS);

        // invalid capacity
        assertParseFailure(parser, NAME_DESC_SWIM_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                + INVALID_CAPACITY_DESC + TIME_DESC_SWIM_TRAINING, Capacity.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_SWIM_TRAINING
                        + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING
                        + VALID_CAPACITY_SWIM_TRAINING + TIME_DESC_SWIM_TRAINING,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }
}
