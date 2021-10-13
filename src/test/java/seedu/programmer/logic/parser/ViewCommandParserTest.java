package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.ViewCommand;
import seedu.programmer.model.student.QueryStudentDescriptor;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyNameArg_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -n ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // multiple trailing whitespace
        assertParseFailure(parser, " -n   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyCidArg_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -cid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -cid ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // multiple trailing whitespace
        assertParseFailure(parser, " -cid   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptySidArg_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -sid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -sid ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // multiple trailing whitespace
        assertParseFailure(parser, " -sid   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleEmptyArgs_throwsParseException() {
        // no trailing whitespace
        assertParseFailure(parser, " -n -sid -cid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -n  -sid  -cid ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // single trailing whitespace
        assertParseFailure(parser, " -n    -sid    -cid   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefixArg_throwsParseException() {
        // single invalid prefix arg
        assertParseFailure(parser, " -s Peter",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " --n Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " -nn Tan",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // multiple invalid prefix arg
        assertParseFailure(parser, " -s Peter -nn Tan",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        QueryStudentDescriptor queryFields = new QueryStudentDescriptor();
        queryFields.setName("Alice");
        StudentDetailContainsQueryPredicate queryPredicate = new StudentDetailContainsQueryPredicate(queryFields);
        ViewCommand expectedViewCommand =
                new ViewCommand(queryPredicate);
        assertParseSuccess(parser, " -n Alice", expectedViewCommand);

        // multiple leading and trailing whitespaces before and after keywords
        assertParseSuccess(parser, " -n \n Alice\t", expectedViewCommand);
    }

    @Test
    public void parse_validMultipleArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        QueryStudentDescriptor queryFields = new QueryStudentDescriptor();
        queryFields.setName("Alice");
        queryFields.setClassId("B01");
        queryFields.setStudentId("A1234567X");
        StudentDetailContainsQueryPredicate queryPredicate = new StudentDetailContainsQueryPredicate(queryFields);
        ViewCommand expectedViewCommand =
                new ViewCommand(queryPredicate);
        assertParseSuccess(parser, " -n Alice -cid B01 -sid A1234567X", expectedViewCommand);

        // multiple leading and trailing whitespaces before and after keywords
        assertParseSuccess(parser, " -n \n Alice\t -cid \nB01 -sid A1234567X  ", expectedViewCommand);
        assertParseSuccess(parser, " -n     Alice    -cid   B01  -sid   A1234567X   ", expectedViewCommand);
    }
}
