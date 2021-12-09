package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.FindNameCommand;
import seedu.times.model.person.predicates.NameContainsKeywordsPredicate;

public class FindNameCommandParserTest {

    private FindNameCommandParser parser = new FindNameCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNameCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindNameCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindNameCommand() {
        // no leading and trailing whitespaces
        FindNameCommand expectedFindNameCommand =
                new FindNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice, Bob", expectedFindNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice, \n \t Bob  \t", expectedFindNameCommand);
    }

}
