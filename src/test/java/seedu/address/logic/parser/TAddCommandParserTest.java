package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.TAddCommand;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class TAddCommandParserTest {
    private TAddCommandParser parser = new TAddCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Task expectedTask = new Task(VALID_TASK_NAME);
        MemberID expectedMemberID = new MemberID(VALID_MEMBER_ID);

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
        assertParseFailure(parser, TASK_NAME_DESC_POEM + INVALID_MEMBER_ID_DESC, MemberID.MESSAGE_CONSTRAINTS);
    }
}