package safeforhall.logic.parser.find;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.commands.CommandTestUtil.CAPACITY_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.DATE_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_CAPACITY_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_DATE_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VENUE_FOOTBALL_TRAINING;
import static safeforhall.logic.commands.CommandTestUtil.VENUE_DESC_FOOTBALL_TRAINING;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ROOM;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.find.FindEventCommand;
import safeforhall.logic.commands.find.FindEventCommand.FindCompositePredicate;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.logic.parser.CommandParserTestUtil;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.Venue;

public class FindEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE);

    private static final String INVALID_ROOM_FOR_FIND1 = "AA";
    private static final String INVALID_ROOM_FOR_FIND2 = "A12";
    private static final String INVALID_ROOM_FOR_FIND3 = "12";

    private static final String VALID_ROOM_FOR_FIND1 = "A";
    private static final String VALID_ROOM_FOR_FIND2 = "A1";
    private static final String VALID_ROOM_FOR_FIND3 = "E200";

    private static final String INVALID_ROOM_DESC1 = " " + PREFIX_ROOM + INVALID_ROOM_FOR_FIND1;
    private static final String INVALID_ROOM_DESC2 = " " + PREFIX_ROOM + INVALID_ROOM_FOR_FIND2;
    private static final String INVALID_ROOM_DESC3 = " " + PREFIX_ROOM + INVALID_ROOM_FOR_FIND3;

    private static final String VALID_ROOM_DESC1 = " " + PREFIX_ROOM + VALID_ROOM_FOR_FIND1;
    private static final String VALID_ROOM_DESC2 = " " + PREFIX_ROOM + VALID_ROOM_FOR_FIND2;
    private static final String VALID_ROOM_DESC3 = " " + PREFIX_ROOM + VALID_ROOM_FOR_FIND3;

    private FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindEventCommand expectedFindEventCommand =
                new FindEventCommand(new FindEventCommand.FindCompositePredicate(preparePredicate("Football Training",
                        null, null, null)));
        CommandParserTestUtil.assertParseSuccess(parser,
                FindEventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "Football Training",
                expectedFindEventCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser,
                FindEventCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "  Football  Training  ",
                expectedFindEventCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", FindEventCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments
        assertParseFailure(parser, "some random string", FindEventCommand.MESSAGE_NOT_FILTERED);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", FindEventCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid event name
        assertParseFailure(parser, INVALID_EVENT_NAME_DESC, EventName.MESSAGE_CONSTRAINTS);

        // invalid event date
        assertParseFailure(parser, INVALID_EVENT_DATE_DESC, EventDate.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = NAME_DESC_FOOTBALL_TRAINING + DATE_DESC_FOOTBALL_TRAINING + VENUE_DESC_FOOTBALL_TRAINING
                + CAPACITY_DESC_FOOTBALL_TRAINING;

        FindEventCommand.FindCompositePredicate predicate = new FindEventCommand.FindCompositePredicate();
        predicate.setEventName(new EventName(VALID_NAME_FOOTBALL_TRAINING));
        predicate.setEventDate(new EventDate(VALID_DATE_FOOTBALL_TRAINING));
        predicate.setVenue(new Venue(VALID_VENUE_FOOTBALL_TRAINING));
        predicate.setCapacity(new Capacity(VALID_CAPACITY_FOOTBALL_TRAINING));

        FindEventCommand expectedCommand = new FindEventCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Parses {@code userInput} into a {@code FindCompositePredicate}.
     */
    private FindCompositePredicate preparePredicate(String eventName, String eventDate, String venue, String capacity) {
        FindCompositePredicate f = new FindCompositePredicate();

        if (eventName != null) {
            f.setEventName(new EventName(eventName));
        }
        if (eventDate != null) {
            f.setEventDate(new EventDate(eventDate));
        }
        if (venue != null) {
            f.setVenue(new Venue(venue));
        }
        if (capacity != null) {
            f.setCapacity(new Capacity(capacity));
        }

        return f;
    }
}
