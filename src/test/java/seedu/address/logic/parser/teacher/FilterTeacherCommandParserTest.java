package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.teacher.FilterTeacherCommand;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;

public class FilterTeacherCommandParserTest {
    private FilterTeacherCommandParser parser = new FilterTeacherCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTeacherCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterTeacherCommand expectedFilterCommand =
                new FilterTeacherCommand(new TeacherInvolvementContainsKeywordsPredicate(Arrays.asList("Alice",
                        "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFilterCommand);

        FilterTeacherCommand expectedFilterCommand2 =
                new FilterTeacherCommand(new TeacherInvolvementContainsKeywordsPredicate(Arrays.asList("t/Alice",
                        "Bob")));

        assertParseSuccess(parser, "t/Alice t/Bob", expectedFilterCommand2);
    }

    @Test
    public void invalidArgs() {
        //involvement after tag
        assertParseFailure(parser, "Alice t/Bob Memes", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTeacherCommand.MESSAGE_USAGE));

        //space after tag
        assertParseFailure(parser, "Alice t/ Memes", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTeacherCommand.MESSAGE_USAGE));

        //non alphanumeric
        assertParseFailure(parser, "Alice t/@@@", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTeacherCommand.MESSAGE_USAGE));
    }
}
