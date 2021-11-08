package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindSupplierCommand;
import seedu.address.model.person.supplier.SupplierClassContainsKeywordsPredicate;

public class FindSupplierCommandParserTest {

    private FindSupplierCommandParser parser = new FindSupplierCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSupplierCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindSupplierCommand() {
        // no leading and trailing whitespaces
        FindSupplierCommand expectedFindSupplierCommand =
                new FindSupplierCommand(new SupplierClassContainsKeywordsPredicate(Arrays.asList("Chicken", "Beef")));
        assertParseSuccess(parser, "Chicken Beef", expectedFindSupplierCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Chicken    Beef \n  \t", expectedFindSupplierCommand);
    }
}
