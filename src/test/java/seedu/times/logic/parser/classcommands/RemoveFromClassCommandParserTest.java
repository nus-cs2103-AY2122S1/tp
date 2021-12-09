package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.commands.classcommands.RemoveFromClassCommand.NO_STUDENT_INDEX_PROVIDED_MESSAGE;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.classcommands.RemoveFromClassCommand;
import seedu.times.logic.parser.classcommandparsers.RemoveFromClassCommandParser;

public class RemoveFromClassCommandParserTest {
    private RemoveFromClassCommandParser parser = new RemoveFromClassCommandParser();

    @Test
    public void parse_allValidInputs_success() {
        Index validIndex = Index.fromOneBased(1);
        Index validIndex2 = Index.fromOneBased(2);
        Index validIndex3 = Index.fromOneBased(3);

        // remove single student
        RemoveFromClassCommand removeFromClassCommand =
                new RemoveFromClassCommand(Arrays.asList(validIndex, validIndex));
        assertParseSuccess(parser, "1 1", removeFromClassCommand);

        // remove single student
        RemoveFromClassCommand removeFromClassCommand2 =
                new RemoveFromClassCommand(Arrays.asList(validIndex, validIndex, validIndex2, validIndex3));
        assertParseSuccess(parser, "1 1 2 3", removeFromClassCommand2);
    }
    @Test
    public void parse_missingArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1", String.format(NO_STUDENT_INDEX_PROVIDED_MESSAGE));
    }

    @Test
    public void parse_negativeStudentIndex_throwsParseException() {
        assertParseFailure(parser, "1 -1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1 -1 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroStudentIndex_throwsParseException() {
        assertParseFailure(parser, "1 0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1 0 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_zeroClassIndex_throwsParseException() {
        assertParseFailure(parser, "0 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0 1 3 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeClassIndex_throwsParseException() {
        assertParseFailure(parser, "-1 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-3 1 3 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_nonIntegerArguments_throwsParseException() {
        //floats
        assertParseFailure(parser, "1.1 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1.3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));

        //other chars
        assertParseFailure(parser, "asdf 1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 asdf", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 1 asdf 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1! 2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2 @#2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveFromClassCommand.MESSAGE_USAGE));
    }
}
