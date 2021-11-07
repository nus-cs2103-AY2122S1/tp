package seedu.address.logic.parser.event;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EaddCommand;
import seedu.address.model.module.Name;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.EventDate;
import seedu.address.testutil.EventBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATE_DESC_CHESS;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_CHESS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_CHESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_CHESS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class EaddCommandParserTest {
    private EaddCommandParser parser = new EaddCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder()
                .withName(VALID_EVENT_NAME_CHESS).withDate(VALID_EVENT_DATE_CHESS).withParticipants().build();
        Set<Index> expectedMemberIdList = new HashSet<>();

        assertParseSuccess(parser, EVENT_NAME_DESC_CHESS + EVENT_DATE_DESC_CHESS,
                new EaddCommand(expectedEvent, expectedMemberIdList));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EaddCommand.MESSAGE_USAGE);

        //missing event name
        assertParseFailure(parser, EVENT_DATE_DESC_CHESS, expectedMessage);

        //missing event data
        assertParseFailure(parser, EVENT_NAME_DESC_CHESS, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid event name
        assertParseFailure(parser,
                INVALID_EVENT_NAME_DESC + EVENT_DATE_DESC_CHESS, Name.MESSAGE_CONSTRAINTS);

        //invalid event date
        assertParseFailure(parser,
                EVENT_NAME_DESC_CHESS + INVALID_EVENT_DATE_DESC, EventDate.MESSAGE_CONSTRAINTS);
    }

}