package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;


public class FilterStudentCommandParserTest {

    private FilterStudentCommandParser parser = new FilterStudentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterStudentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterStudentCommand expectedFilterCommand =
                new FilterStudentCommand(new StudentInvolvementContainsKeywordsPredicate(Arrays.asList("Alice",
                        "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFilterCommand);

        FilterStudentCommand expectedFilterCommand2 =
                new FilterStudentCommand(new StudentInvolvementContainsKeywordsPredicate(Arrays.asList("t/Alice",
                        "Bob")));

        assertParseSuccess(parser, "t/Alice t/Bob", expectedFilterCommand2);
    }

    @Test
    public void invalidArgs() {
        //involvement after tag
        assertParseFailure(parser, "Alice t/Bob Memes", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterStudentCommand.MESSAGE_USAGE));

        //space after tag
        assertParseFailure(parser, "Alice t/ Memes", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterStudentCommand.MESSAGE_USAGE));

        //non alphanumeric
        assertParseFailure(parser, "Alice t/@@@", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterStudentCommand.MESSAGE_USAGE));
    }

}
