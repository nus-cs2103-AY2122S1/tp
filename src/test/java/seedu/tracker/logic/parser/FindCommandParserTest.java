package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tracker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.tracker.logic.commands.FindCommand;
import seedu.tracker.model.module.ModuleContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_EMPTY_KEYWORD));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
            new FindCommand(new ModuleContainsKeywordsPredicate(Arrays.asList("CS2103T", "GEQ1000")));
        assertParseSuccess(parser, "CS2103T GEQ1000", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103T \n \t GEQ1000  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_failure() {
        // prefix is not empty
        assertParseFailure(parser, "c/cs2103t ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "t/cs2103t ge",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // no keywords given
        assertParseFailure(parser, "c/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "tag/ ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
