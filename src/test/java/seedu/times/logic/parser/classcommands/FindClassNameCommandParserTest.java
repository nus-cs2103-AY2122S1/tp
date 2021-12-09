package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.commons.core.Messages.MESSAGE_NO_SEARCH_TERMS_ENTERED;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.classcommands.FindClassNameCommand;
import seedu.times.logic.parser.classcommandparsers.FindClassNameCommandParser;
import seedu.times.model.tuitionclass.predicates.ClassNameContainsKeywordsPredicate;

public class FindClassNameCommandParserTest {
    private FindClassNameCommandParser parser = new FindClassNameCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //empty args
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassNameCommand.MESSAGE_USAGE));

        //empty args with spaces
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassNameCommand.MESSAGE_USAGE));

        //with only delimiter
        assertParseFailure(parser, ",",
                String.format(MESSAGE_NO_SEARCH_TERMS_ENTERED));

        //with delimiter and spaces
        assertParseFailure(parser, "   ,   ",
                String.format(MESSAGE_NO_SEARCH_TERMS_ENTERED));
    }



    @Test
    public void parse_validArgs_returnsFindClassNameCommand() {
        //single keywords, no leading and trailing whitespaces
        FindClassNameCommand expectedCommand =
                new FindClassNameCommand(new ClassNameContainsKeywordsPredicate(Arrays.asList("Physics", "Maths")));
        assertParseSuccess(parser, "Physics, Maths", expectedCommand);

        //single keywords, whitespaces between search terms
        assertParseSuccess(parser, "    Physics, Maths     ", expectedCommand);

        //multiple keywords in each search term
        expectedCommand = new FindClassNameCommand(new ClassNameContainsKeywordsPredicate(Arrays.asList("JC Physics",
                "JC Chemistry")));
        assertParseSuccess(parser, "JC Physics, JC Chemistry", expectedCommand);
    }
}
