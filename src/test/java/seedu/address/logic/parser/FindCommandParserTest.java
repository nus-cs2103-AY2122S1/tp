package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNames_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_emptyModule_throwsParseException() {
        assertParseFailure(parser, "find m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleCode.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validModule_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new ModuleCodesContainsKeywordsPredicate(
                        Arrays.asList(
                                String.format("[%s]", VALID_MODULE_CODE_CS2030S),
                                String.format("[%s]", VALID_MODULE_CODE_CS2040)
                        )
                ));
        String userInput = String.format(" m/%s m/%s",VALID_MODULE_CODE_CS2030S, VALID_MODULE_CODE_CS2040);
        assertParseSuccess(parser, userInput, expectedFindCommand);
    }
}
