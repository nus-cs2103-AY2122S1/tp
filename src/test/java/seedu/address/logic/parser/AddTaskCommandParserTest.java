package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Date;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        Task expectedTask = new Task(new Label("test label"), new Date("test date"));

        //basic test command
        assertParseSuccess(parser, " l/test label d/test date", new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        //right now, only validation check is that both fields are not empty after trimming spaces
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        //missing label prefix
        assertParseFailure(parser, " d/test date", expectedMessage);
        assertParseFailure(parser, " test label d/test date", expectedMessage);

        //missing date prefix
        assertParseFailure(parser, " l/test label", expectedMessage);
        assertParseFailure(parser, " l/test label test date", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //empty label
        assertParseFailure(parser, " l/ d/date", "empty label");

        //empty date after spaces are trimmed
        assertParseFailure(parser, " l/label d/ ", "empty date");
    }
}
