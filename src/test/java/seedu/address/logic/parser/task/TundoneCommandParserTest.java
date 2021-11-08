package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TASK_INDEX_DESC_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TundoneCommand;

class TundoneCommandParserTest {
    private TundoneCommandParser parser = new TundoneCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Index expectedTaskId = Index.fromOneBased(1);
        Set<Index> expectedTaskIdList = new HashSet<>();
        expectedTaskIdList.add(expectedTaskId);

        assertParseSuccess(parser, TASK_INDEX_DESC_ONE, new TundoneCommand(expectedTaskIdList));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TundoneCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid task id
        assertParseFailure(parser, INVALID_TASK_INDEX_DESC, MESSAGE_INVALID_INDEX);
    }
}
