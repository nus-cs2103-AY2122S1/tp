package seedu.address.logic.parser.event;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EdelCommand;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_INDEX_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

class EdelCommandParserTest {
    private EdelCommandParser parser = new EdelCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Index expectedEventId = Index.fromOneBased(VALID_EVENT_INDEX);

        assertParseSuccess(parser, EVENT_INDEX_DESC_ONE, new EdelCommand(expectedEventId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EdelCommand.MESSAGE_USAGE);

        //missing event id
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid event if (blank)
        assertParseFailure(parser, INVALID_EVENT_INDEX_DESC, MESSAGE_INVALID_INDEX);
    }
}
