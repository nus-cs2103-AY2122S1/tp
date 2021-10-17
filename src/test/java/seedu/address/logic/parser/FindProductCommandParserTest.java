package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindProductCommand;
import seedu.address.model.product.ProductContainsKeywordsPredicate;

public class FindProductCommandParserTest {

    private FindProductCommandParser parser = new FindProductCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProductCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindProductCommand() {
        // no leading and trailing whitespaces
        FindProductCommand expectedFindProductCommand =
                new FindProductCommand(new ProductContainsKeywordsPredicate(Arrays.asList("Panadol", "Mask")));
        assertParseSuccess(parser, "Panadol Mask", expectedFindProductCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Panadol \n \t Mask  \t", expectedFindProductCommand);
    }

}
