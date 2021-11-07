package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_PARTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.PARTY_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.testutil.EventBuilder;

class EventCommandParserTest {
    private EventCommandParser parser = new EventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(PARTY_EVENT).build();

        // with preamble whitespace
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_EVENT_NAME_PARTY,
                new EventCommand(expectedEvent));

        // without preamble whitespace
        assertParseSuccess(parser, VALID_EVENT_NAME_PARTY,
                new EventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.MESSAGE_USAGE);

        // preamble whitespace
        assertParseFailure(parser, PREAMBLE_WHITESPACE, expectedMessage);

        // whitespace
        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid eventName
        assertParseFailure(parser, INVALID_EVENT_NAME, EventName.MESSAGE_CONSTRAINTS);
    }
}
