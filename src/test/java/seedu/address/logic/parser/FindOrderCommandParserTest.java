package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

public class FindOrderCommandParserTest {
    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindOrderCommand expectedFindOrderCommand =
                new FindOrderCommand(new OrderContainsKeywordsPredicate(Arrays.asList("Button", "SO1")));
        assertParseSuccess(parser, "Button SO1", expectedFindOrderCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Button \n \t SO1  \t", expectedFindOrderCommand);
    }
}
