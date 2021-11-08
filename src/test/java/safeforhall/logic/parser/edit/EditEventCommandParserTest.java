package safeforhall.logic.parser.edit;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.commands.CommandTestUtil.CAPACITY_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.CAPACITY_DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.DATE_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.DATE_DESC_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_CAPACITY_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_VENUE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_CAPACITY_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_CAPACITY_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_DATE_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_DATE_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VENUE_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VENUE_SWIM_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VENUE_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VENUE_DESC_SWIM_TRAINING;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static safeforhall.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static safeforhall.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.edit.EditEventCommand;
import safeforhall.logic.commands.edit.EditEventCommand.EditEventDescriptor;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.Venue;
import safeforhall.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {

    private static final String INVALID_EVENT_INDEX = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX + "\n" + EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no index specified
        assertParseFailure(parser, VALID_NAME_FOOTBALL_TRAINING, INVALID_EVENT_INDEX);

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", INVALID_EVENT_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_FOOTBALL_TRAINING, INVALID_EVENT_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_FOOTBALL_TRAINING, INVALID_EVENT_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", INVALID_EVENT_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", INVALID_EVENT_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME_DESC, EventName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, "1" + INVALID_EVENT_DATE_DESC, EventDate.MESSAGE_CONSTRAINTS);

        // invalid venue
        assertParseFailure(parser, "1" + INVALID_VENUE_DESC, Venue.MESSAGE_CONSTRAINTS);

        // invalid capacity
        assertParseFailure(parser, "1" + INVALID_VENUE_DESC, Venue.MESSAGE_CONSTRAINTS);

        // invalid date followed by valid venue
        assertParseFailure(parser, "1" + INVALID_EVENT_DATE_DESC + VENUE_DESC_FOOTBALL_TRAINING,
                EventDate.MESSAGE_CONSTRAINTS);

        // valid capacity followed by invalid capacity. The test case for invalid capacity followed by valid capacity
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + CAPACITY_DESC_SWIM_TRAINING + INVALID_CAPACITY_DESC,
                Capacity.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME_DESC + INVALID_EVENT_DATE_DESC
                        + VALID_CAPACITY_FOOTBALL_TRAINING, EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + VENUE_DESC_SWIM_TRAINING
                + DATE_DESC_FOOTBALL_TRAINING + NAME_DESC_FOOTBALL_TRAINING;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withName(VALID_NAME_FOOTBALL_TRAINING).withVenue(VALID_VENUE_SWIM_TRAINING)
                .withDate(VALID_DATE_FOOTBALL_TRAINING).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + VENUE_DESC_SWIM_TRAINING + DATE_DESC_FOOTBALL_TRAINING;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withVenue(VALID_VENUE_SWIM_TRAINING)
                .withDate(VALID_DATE_FOOTBALL_TRAINING).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_FOOTBALL_TRAINING;
        EditEventDescriptor descriptor =
                new EditEventDescriptorBuilder().withName(VALID_NAME_FOOTBALL_TRAINING).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_FOOTBALL_TRAINING;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_FOOTBALL_TRAINING).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // venue
        userInput = targetIndex.getOneBased() + VENUE_DESC_FOOTBALL_TRAINING;
        descriptor = new EditEventDescriptorBuilder().withVenue(VALID_VENUE_FOOTBALL_TRAINING).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // capacity
        userInput = targetIndex.getOneBased() + CAPACITY_DESC_FOOTBALL_TRAINING;
        descriptor = new EditEventDescriptorBuilder().withCapacity(VALID_CAPACITY_FOOTBALL_TRAINING).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_FOOTBALL_TRAINING + VENUE_DESC_FOOTBALL_TRAINING
                + CAPACITY_DESC_FOOTBALL_TRAINING + DATE_DESC_FOOTBALL_TRAINING + VENUE_DESC_FOOTBALL_TRAINING
                + DATE_DESC_SWIM_TRAINING + VENUE_DESC_SWIM_TRAINING + CAPACITY_DESC_SWIM_TRAINING;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withCapacity(VALID_CAPACITY_SWIM_TRAINING)
                .withVenue(VALID_VENUE_SWIM_TRAINING).withDate(VALID_DATE_SWIM_TRAINING)
                .build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_CAPACITY_DESC + CAPACITY_DESC_SWIM_TRAINING;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withCapacity(VALID_CAPACITY_SWIM_TRAINING).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + VENUE_DESC_SWIM_TRAINING + INVALID_CAPACITY_DESC
                + DATE_DESC_SWIM_TRAINING + CAPACITY_DESC_SWIM_TRAINING;
        descriptor = new EditEventDescriptorBuilder().withCapacity(VALID_CAPACITY_SWIM_TRAINING)
                .withVenue(VALID_VENUE_SWIM_TRAINING).withDate(VALID_DATE_SWIM_TRAINING).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
