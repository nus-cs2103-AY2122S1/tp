package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.NameContainsKeysPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() throws ParseException {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeysPredicate(Arrays.asList("Alice", "Bob")));
        String testInput = PREAMBLE_WHITESPACE + " " + PREFIX_NAME.getPrefix() + " Alice Bob";
        FindCommand testFindCommand = parser.parse(testInput);
        assertParseSuccess(parser, testInput, expectedFindCommand);

        // multiple whitespaces between keywords
        String testInputWithWhiteSpaces = PREAMBLE_WHITESPACE + " " + PREFIX_NAME.getPrefix()
                + " \n Alice \n \t Bob  \t";
        assertParseSuccess(parser, testInputWithWhiteSpaces, expectedFindCommand);
    }

}
