package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;
import seedu.address.model.tag.Tag;

public class FindCommandParserTest {

    private static final int DEFAULT_TEST_INDEX = 1;
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new PersonContainsFieldsPredicate()); //names are not in PCFP
        assertParseSuccess(parser, " " + PREFIX_DASH_NAME + " Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_DASH_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_field_test() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new PersonContainsFieldsPredicate(new Tag("big"))); //names are not in PCFP
        assertParseSuccess(parser, " " + PREFIX_DASH_NAME + " Alice Bob "
                + PREFIX_DASH_TAG + "big", expectedFindCommand);
    }

    @Test
    public void parse_index_test() {
        FindCommand expectedFindCommand = new FindCommand(DEFAULT_TEST_INDEX);
        assertParseSuccess(parser, " " + PREFIX_DASH_INDEX + " 2", expectedFindCommand);

        //EP: 0
        expectedFindCommand = new FindCommand(0);
        assertParseSuccess(parser, " " + PREFIX_DASH_INDEX + " 1", expectedFindCommand);

        //EP:1
        assertParseFailure(parser, " " + PREFIX_DASH_INDEX + " 0", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void parse_field_onlyTest() {
        FindCommand expectedFindCommand = new FindCommand(new PersonContainsFieldsPredicate(new Tag("big")));
        assertParseSuccess(parser, " " + PREFIX_DASH_TAG + " big", expectedFindCommand);
    }

}
