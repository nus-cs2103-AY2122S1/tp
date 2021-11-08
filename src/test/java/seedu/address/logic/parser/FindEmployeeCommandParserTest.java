package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindEmployeeCommand;
import seedu.address.model.person.employee.EmployeeClassContainsKeywordsPredicate;

public class FindEmployeeCommandParserTest {
    private FindEmployeeCommandParser parser = new FindEmployeeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmployeeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindEmployeeCommand() {
        // no leading and trailing whitespaces
        FindEmployeeCommand expectedFindCommand =
                new FindEmployeeCommand(new EmployeeClassContainsKeywordsPredicate(Arrays.asList("Alice")));
        assertParseSuccess(parser, "Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n  \t", expectedFindCommand);
    }
}
