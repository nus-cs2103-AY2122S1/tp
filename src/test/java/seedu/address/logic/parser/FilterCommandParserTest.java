package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_FIELDS_EMPTY;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.client.ClientContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefixArg_throwsParseException() {
        assertParseFailure(parser, " t/ ", String.format(MESSAGE_FIELDS_EMPTY, "Tag"));
        assertParseFailure(parser, " t/ a/ p/ e/", String.format(MESSAGE_FIELDS_EMPTY, "Tag, Address, Phone, Email"));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ClientContainsKeywordsPredicate(
                        ArgumentTokenizer.tokenize("Alice Bob e/example.com a/Blk 30",
                                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG)
                ));
        assertParseSuccess(parser, "Alice Bob e/example.com a/Blk 30", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t e/example.com a/Blk 30", expectedFilterCommand);
    }
}
