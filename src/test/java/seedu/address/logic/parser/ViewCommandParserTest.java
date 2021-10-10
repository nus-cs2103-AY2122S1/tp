package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.student.NameContainsKeywordsPredicate;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyNameArg_throwsParseException() {
        // single trailing whitespace
        assertParseFailure(parser, " -n ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // multiple trailing whitespaces
        assertParseFailure(parser, " -n   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefixArg_throwsParseException() {
        // single trailing whitespace
        assertParseFailure(parser, " -s Peter",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " --n Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " -nn Tan",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        ViewCommand expectedViewCommand =
                new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " -n Alice Bob", expectedViewCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -n \n Alice \n \t Bob  \t", expectedViewCommand);
    }

}
