package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.model.student.GroupContainsKeywordsPredicate;
import seedu.address.model.student.IdContainsKeywordsPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.TagContainsKeywordsPredicate;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleTags_throwsParseException() {
        assertParseFailure(parser, " -n Alice -g T02A",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsNameSearch_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " -n Alice Bob", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -n  Alice   Bob  ", expectedSearchCommand);
    }

    @Test
    public void parse_validArgsIdSearch_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new IdContainsKeywordsPredicate(Arrays.asList("E03", "7")));
        assertParseSuccess(parser, " -i E03 7", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -i  E03  7", expectedSearchCommand);
    }

    @Test
    public void parse_validArgsGroupSearch_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new GroupContainsKeywordsPredicate(Arrays.asList("T01", "A")));
        assertParseSuccess(parser, " -g T01 A", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -g  T01  A", expectedSearchCommand);
    }

    @Test
    public void parse_validArgsTagSearch_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues")));
        assertParseSuccess(parser, " -t friends colleagues", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " -t  friends  colleagues", expectedSearchCommand);
    }

}
