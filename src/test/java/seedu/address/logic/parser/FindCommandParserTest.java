package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.student.GroupContainsKeywordsPredicate;
import seedu.address.model.student.IdContainsKeywordsPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleTags_throwsParseException() {
        assertParseFailure(parser, " -n Alice -g T02A",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsNameSearch_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " -n Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -n  Alice   Bob  ", expectedFindCommand);
    }

    @Test
    public void parse_validArgsIdSearch_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new IdContainsKeywordsPredicate(Arrays.asList("E03", "7")));
        assertParseSuccess(parser, " -i E03 7", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -i  E03  7", expectedFindCommand);
    }

    @Test
    public void parse_validArgsGroupSearch_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new GroupContainsKeywordsPredicate(Arrays.asList("T01", "A")));
        assertParseSuccess(parser, " -g T01 A", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -g  T01  A", expectedFindCommand);
    }

    @Test
    public void parse_validArgsTagSearch_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues")));
        assertParseSuccess(parser, " -t friends colleagues", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -t  friends  colleagues", expectedFindCommand);
    }

}
