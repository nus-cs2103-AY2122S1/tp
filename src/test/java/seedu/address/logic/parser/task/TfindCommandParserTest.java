package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.TfindCommand;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.task.Task;

class TfindCommandParserTest {
    private TfindCommandParser parser = new TfindCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        NameContainsKeywordsPredicate<Task> predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("test"));

        assertParseSuccess(parser, "test", new TfindCommand(predicate));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TfindCommand.MESSAGE_USAGE);

        //keyword is empty
        assertParseFailure(parser, "", expectedMessage);
    }

}
