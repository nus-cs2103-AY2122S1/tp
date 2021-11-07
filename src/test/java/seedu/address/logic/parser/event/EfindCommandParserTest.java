package seedu.address.logic.parser.event;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.event.EfindCommand;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.event.Event;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class EfindCommandParserTest {
    private EfindCommandParser parser = new EfindCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        NameContainsKeywordsPredicate<Event> predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("test"));

        assertParseSuccess(parser, "test", new EfindCommand(predicate));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EfindCommand.MESSAGE_USAGE);

        //keyword is empty
        assertParseFailure(parser, "", expectedMessage);
    }
}
