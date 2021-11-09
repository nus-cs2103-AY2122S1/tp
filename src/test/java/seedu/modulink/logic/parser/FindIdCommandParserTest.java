package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.FindIdCommand;
import seedu.modulink.model.person.StudentIdContainsKeywordsPredicate;

public class FindIdCommandParserTest {

    private FindIdCommandParser parser = new FindIdCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIdCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindIdCommand() {
        // no leading and trailing whitespaces
        FindIdCommand expectedFindIdCommand =
                new FindIdCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList("A1234567X", "A1234678X")));
        assertParseSuccess(parser, "A1234567X A1234678X", expectedFindIdCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n A1234567X \n \t A1234678X  \t", expectedFindIdCommand);
    }

}
