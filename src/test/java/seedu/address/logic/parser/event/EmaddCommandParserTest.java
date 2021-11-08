package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_INDEX_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEMBER_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_INDEX_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EmaddCommand;

class EmaddCommandParserTest {
    private EmaddCommandParser parser = new EmaddCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Index expectedEventId = Index.fromOneBased(VALID_EVENT_INDEX);
        Index expectedMemberId = Index.fromOneBased(VALID_MEMBER_INDEX);
        Set<Index> expectedMemberIdList = new HashSet<>();
        expectedMemberIdList.add(expectedMemberId);

        assertParseSuccess(parser, EVENT_INDEX_DESC_ONE + MEMBER_INDEX_DESC_ONE,
                new EmaddCommand(expectedEventId, expectedMemberIdList));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EmaddCommand.MESSAGE_USAGE);

        //missing member id
        assertParseFailure(parser, EVENT_INDEX_DESC_ONE, expectedMessage);

        //missing event id
        assertParseFailure(parser, MEMBER_INDEX_DESC_ONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid event id
        assertParseFailure(parser, INVALID_EVENT_INDEX_DESC + MEMBER_INDEX_DESC_ONE, MESSAGE_INVALID_INDEX);

        //invalid member id
        assertParseFailure(parser, EVENT_INDEX_DESC_ONE + INVALID_MEMBER_INDEX_DESC, MESSAGE_INVALID_INDEX);
    }
}
