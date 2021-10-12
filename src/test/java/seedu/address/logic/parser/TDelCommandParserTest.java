package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.TAddCommand;
import seedu.address.logic.commands.TDelCommand;
import seedu.address.model.task.MemberID;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskID;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.MEMBER_ID_DESC_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class TDelCommandParserTest {
    private TDelCommandParser parser = new TDelCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        TaskID expectedTaskID = new TaskID(VALID_TASK_ID);
        MemberID expectedMemberID = new MemberID(VALID_MEMBER_ID_DEL);

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
        assertParseFailure(parser, INVALID_TASK_ID_DESC + MEMBER_ID_DEL_DESC_ONE, TaskID.MESSAGE_CONSTRAINTS);

        //invalid member id
        assertParseFailure(parser, TASK_ID_DESC_ONE + INVALID_MEMBER_ID_DEL_DESC, MemberID.MESSAGE_CONSTRAINTS);
    }
}