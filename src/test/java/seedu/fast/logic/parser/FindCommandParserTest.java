package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.FindCommand;
import seedu.fast.model.person.NameContainsQueriesPredicate;
import seedu.fast.model.person.PriorityPredicate;
import seedu.fast.model.person.RemarkContainsKeywordPredicate;
import seedu.fast.model.person.TagMatchesKeywordPredicate;
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
                new FindCommand(new NameContainsQueriesPredicate(Arrays.asList("Alice", "Bob")));
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
        assertParseSuccess(parser , PREFIX_PRIORITY_TAG.getPrefix()
                + PriorityTag.LowPriority.TERM + " "
                + PriorityTag.MediumPriority.TERM , expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREFIX_PRIORITY_TAG.getPrefix()
                + PriorityTag.LowPriority.TERM + " \n \t "
                + PriorityTag.MediumPriority.TERM , expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagMatchesKeywordPredicate(Arrays.asList("poo",
                        "pee")));
        assertParseSuccess(parser , FindCommand.TAG_PREFIX
                + "poo pee", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, FindCommand.TAG_PREFIX
                + "poo \n \t pee" , expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindRemarkCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new RemarkContainsKeywordPredicate(Arrays.asList("poo",
                        "pee")));
        assertParseSuccess(parser , FindCommand.REMARK_PREFIX
                + "poo pee", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, FindCommand.REMARK_PREFIX
                + "poo \n \t pee" , expectedFindCommand);
    }

}
