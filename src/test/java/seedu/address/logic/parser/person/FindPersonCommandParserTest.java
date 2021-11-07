package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.FindPersonCommand;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNames_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "find n/Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "find n/ \n Alice \t Bob  \t", expectedFindPersonCommand);
    }

    @Test
    public void parse_emptyName_throwsParseException() {
        assertParseFailure(parser, "find n/ ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyModule_throwsParseException() {
        assertParseFailure(parser, "find m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validModule_returnsFindCommand() {
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new ModuleCodesContainsKeywordsPredicate(
                        Arrays.asList(
                                String.format("%s", VALID_MODULE_CODE_CS2040)
                        )
                ));
        String userInput = String.format(" m/%s", VALID_MODULE_CODE_CS2040);
        assertParseSuccess(parser, userInput, expectedFindPersonCommand);
    }

    @Test
    public void parse_emptyTag_throwsParseException() {
        assertParseFailure(parser, "find t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_twoPrefixesNameAndModule_throwsParseException() {
        assertParseFailure(parser, "find n/ben m/cs2100",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_SINGLE_PREFIX_SEARCH));
    }

    @Test
    public void parse_threePrefixesNameAndModule_throwsParseException() {
        assertParseFailure(parser, "find n/ben m/cs2100 t/UwU",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_SINGLE_PREFIX_SEARCH));
    }
}
