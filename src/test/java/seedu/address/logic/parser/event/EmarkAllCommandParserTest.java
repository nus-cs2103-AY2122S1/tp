package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_INDEX_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_INDEX_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EmarkAllCommand;

class EmarkAllCommandParserTest {
    private EmarkAllCommandParser parser = new EmarkAllCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Index expectedEventId = Index.fromOneBased(1);

        assertParseSuccess(parser, EVENT_INDEX_DESC_ONE, new EmarkAllCommand(expectedEventId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmarkAllCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid task id
        assertParseFailure(parser, INVALID_EVENT_INDEX_DESC, MESSAGE_INVALID_INDEX);
    }
}
