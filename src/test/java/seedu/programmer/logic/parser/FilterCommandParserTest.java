package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_EMPTY_ARGUMENT;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.commons.core.Messages.MESSAGE_UNKNOWN_ARGUMENT_FLAG;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.FilterCommand;
import seedu.programmer.model.student.QueryStudentDescriptor;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyNameArg_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -n ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));

        // multiple trailing whitespace
        assertParseFailure(parser, " -n   ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyCidArg_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -cid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -cid ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));

        // multiple trailing whitespace
        assertParseFailure(parser, " -cid   ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptySidArg_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -sid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -sid ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));

        // multiple trailing whitespace
        assertParseFailure(parser, " -sid   ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyEmailArg_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -email",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -email ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));

        // multiple trailing whitespace
        assertParseFailure(parser, " -email   ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleEmptyArgs_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -n -sid -cid -email",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -n  -sid  -cid -email ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -n    -sid    -cid   -email      ",
                String.format(MESSAGE_EMPTY_ARGUMENT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefixArg_throwsParseException() {
        // single invalid prefix arg
        assertParseFailure(parser, " -s Peter",
                String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, "[-s]", FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " --n Alice",
                String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, "[--n]", FilterCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " -nn Tan",
                String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, "[-nn]", FilterCommand.MESSAGE_USAGE));

        // multiple invalid prefix arg
        assertParseFailure(parser, " -s Peter -nn Tan",
                String.format(MESSAGE_UNKNOWN_ARGUMENT_FLAG, "[-s, -nn]", FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        QueryStudentDescriptor queryFields = new QueryStudentDescriptor("Alice", null, null, null);
        StudentDetailContainsQueryPredicate queryPredicate = new StudentDetailContainsQueryPredicate(queryFields);
        FilterCommand expectedFilterCommand =
                new FilterCommand(queryPredicate);
        assertParseSuccess(parser, " -n Alice", expectedFilterCommand);

        // multiple leading and trailing whitespaces before and after keywords
        assertParseSuccess(parser, " -n \n Alice\t", expectedFilterCommand);
    }

    @Test
    public void parse_validMultipleArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        QueryStudentDescriptor queryFields = new QueryStudentDescriptor("Alice", "A1234567X", "B01", null);
        StudentDetailContainsQueryPredicate queryPredicate = new StudentDetailContainsQueryPredicate(queryFields);
        FilterCommand expectedFilterCommand =
                new FilterCommand(queryPredicate);
        assertParseSuccess(parser, " -n Alice -cid B01 -sid A1234567X", expectedFilterCommand);

        // multiple leading and trailing whitespaces before and after keywords
        assertParseSuccess(parser, " -n \n Alice\t -cid B01 -sid A1234567X  ", expectedFilterCommand);
        assertParseSuccess(parser, " -n     Alice    -cid   B01  -sid   A1234567X   ", expectedFilterCommand);
    }
}
