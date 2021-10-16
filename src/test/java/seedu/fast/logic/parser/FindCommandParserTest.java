package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.FindCommand;
import seedu.fast.model.person.NameContainsKeywordsPredicate;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.tag.PriorityTag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindPriorityCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriorityPredicate(Arrays.asList(PriorityTag.LowPriority.TERM,
                        PriorityTag.MediumPriority.TERM)));
        assertParseSuccess(parser , PriorityTag.PRIORITY_TAG_PREFIX
                + PriorityTag.LowPriority.TERM + " "
                + PriorityTag.MediumPriority.TERM , expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, PriorityTag.PRIORITY_TAG_PREFIX
                + PriorityTag.LowPriority.TERM + " \n \t "
                + PriorityTag.MediumPriority.TERM , expectedFindCommand);
    }

}
