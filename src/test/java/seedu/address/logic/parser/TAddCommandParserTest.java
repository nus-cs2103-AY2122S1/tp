package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEMBER_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_ID_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_POEM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TAddCommand;
import seedu.address.model.task.Task;

class TAddCommandParserTest {
    private TAddCommandParser parser = new TAddCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Task expectedTask = new Task(VALID_TASK_NAME);
        Index expectedMemberID = Index.fromOneBased(VALID_MEMBER_ID);

        assertParseSuccess(parser, TASK_NAME_DESC_POEM + MEMBER_ID_DESC_ONE,
                new TAddCommand(expectedMemberID, expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TAddCommand.MESSAGE_USAGE);

        //missing member id
        assertParseFailure(parser, TASK_NAME_DESC_POEM, expectedMessage);

        //missing task name
        assertParseFailure(parser, MEMBER_ID_DESC_ONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid task name (blank)
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + MEMBER_ID_DESC_ONE, Task.MESSAGE_CONSTRAINTS);

        //invalid member id
        assertParseFailure(parser, TASK_NAME_DESC_POEM + INVALID_MEMBER_ID_DESC, MESSAGE_INVALID_INDEX);
    }
}
