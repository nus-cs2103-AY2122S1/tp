package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEMBER_ID_DEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_ID_DEL_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_ID_DEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TDelCommand;

class TDelCommandParserTest {
    private TDelCommandParser parser = new TDelCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Index expectedTaskID = Index.fromOneBased(VALID_TASK_ID);
        Index expectedMemberID = Index.fromOneBased(VALID_MEMBER_ID_DEL);

        assertParseSuccess(parser, TASK_ID_DESC_ONE + MEMBER_ID_DEL_DESC_ONE,
                new TDelCommand(expectedMemberID, expectedTaskID));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TDelCommand.MESSAGE_USAGE);

        //missing member id
        assertParseFailure(parser, TASK_ID_DESC_ONE, expectedMessage);

        //missing task id
        assertParseFailure(parser, MEMBER_ID_DEL_DESC_ONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid task name (blank)
        assertParseFailure(parser, INVALID_TASK_ID_DESC + MEMBER_ID_DEL_DESC_ONE, MESSAGE_INVALID_INDEX);

        //invalid member id
        assertParseFailure(parser, TASK_ID_DESC_ONE + INVALID_MEMBER_ID_DEL_DESC, MESSAGE_INVALID_INDEX);
    }
}
