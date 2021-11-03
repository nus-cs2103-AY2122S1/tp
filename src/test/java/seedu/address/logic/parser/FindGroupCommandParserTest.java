package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class FindGroupCommandParserTest {

    private final FindGroupCommandParser parser = new FindGroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindGroupCommand expectedFindGroupCommand =
                new FindGroupCommand(new GroupContainsKeywordsPredicate(Arrays.asList("W14-3", "W14-4")));
        assertParseSuccess(parser, "W14-3 W14-4", expectedFindGroupCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n W14-3 \n \t W14-4  \t", expectedFindGroupCommand);
    }

}
