package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;

//import java.util.Arrays;
import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.FindCommand;
//import safeforhall.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    // TODO: Find command test
    /*@Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new FindCommand.FindCompositePredicate(Arrays.asList("Alice", "Bob")));
        CommandParserTestUtil.assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }*/

}
