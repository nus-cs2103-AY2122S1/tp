package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.commons.core.index.Index.fromOneBased;
import static seedu.times.logic.commands.classcommands.AddToClassCommand.NO_STUDENT_INDEX_PROVIDED_MESSAGE;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.AddToClassCommand;
import seedu.times.logic.parser.classcommandparsers.AddToClassCommandParser;

public class AddToClassCommandParserTest {
    private Index validIndex1 = fromOneBased(1);
    private Index validIndex2 = fromOneBased(2);
    private Index validIndex3 = fromOneBased(3);

    private AddToClassCommandParser parser = new AddToClassCommandParser();

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE)); //nothing
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE)); //whitespace
    }

    @Test
    public void parse_noStudentIndex_failure() {
        assertParseFailure(parser, "1", NO_STUDENT_INDEX_PROVIDED_MESSAGE); //without trailing space
        assertParseFailure(parser, "2  ", NO_STUDENT_INDEX_PROVIDED_MESSAGE); //with trailing space
    }

    @Test
    public void parse_invalidClassIndex_failure() {
        //non-digit with no student index
        assertParseFailure(parser, "ds", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
        //non-digit with student index
        assertParseFailure(parser, "dsajkdha 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
        //negative
        assertParseFailure(parser, "-3 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
        //zero
        assertParseFailure(parser, "0 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStudentIndex_failure() {
        //non-digit
        assertParseFailure(parser, "3 dsa", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
        //negative
        assertParseFailure(parser, "3 -3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
        //zero
        assertParseFailure(parser, "3 0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
        //first valid second invalid
        assertParseFailure(parser, "3 2 0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddToClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_all_success() {
        //add single student
        List<Index> indexArray1 = Arrays.asList(validIndex1, validIndex2);
        AddToClassCommand expectedCommand1 = new AddToClassCommand(indexArray1);
        assertParseSuccess(parser, "1 2", expectedCommand1);

        //add multiple students
        List<Index> indexArray2 = Arrays.asList(validIndex1, validIndex1, validIndex2, validIndex3);
        AddToClassCommand expectedCommand2 = new AddToClassCommand(indexArray2);
        assertParseSuccess(parser, "1 1 2 3", expectedCommand2);

        //duplicate student indices in command will parse sucessfully with only one index in result
        List<Index> indexArray3 = Arrays.asList(validIndex1, validIndex1, validIndex3);
        AddToClassCommand expectedCommand3 = new AddToClassCommand(indexArray3);
        assertParseSuccess(parser, "1 1 1 3", expectedCommand3);
    }
}
