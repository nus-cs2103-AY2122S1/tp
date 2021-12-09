package seedu.times.logic.parser.classcommands;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.classcommands.FindClassCommand;
import seedu.times.logic.parser.classcommandparsers.FindClassCommandParser;
import seedu.times.model.tuitionclass.predicates.ClassTimingContainsKeywordsPredicate;

public class FindClassCommandParserTest {

    private FindClassCommandParser parser = new FindClassCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDayArgs_returnsFindClassCommand() {
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(new ClassTimingContainsKeywordsPredicate(Arrays.asList("FRI")));
        assertParseSuccess(parser, "FRI", expectedFindClassCommand);
    }

    @Test
    public void parse_invalidDayArgs_throwsParseException() {
        assertParseFailure(parser, "FRO",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultiDayArgs_throwsParseException() {
        assertParseFailure(parser, "FRI SAN",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validMultiDayArgs_returnsFindClassCommand() {
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(new ClassTimingContainsKeywordsPredicate(Arrays.asList("FRI", "SAT")));
        assertParseSuccess(parser, "FRI SAT", expectedFindClassCommand);
    }

    @Test
    public void parse_validMultiDayArgsWithMultiSpaces_returnsFindClassCommand() {
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(new ClassTimingContainsKeywordsPredicate(Arrays.asList("FRI", "SAT")));
        assertParseSuccess(parser, "FRI                   SAT", expectedFindClassCommand);
    }

    @Test
    public void parse_validTimingArgs_returnsFindClassCommand() {
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(new ClassTimingContainsKeywordsPredicate(Arrays.asList("11:30-12:30")));
        assertParseSuccess(parser, "11:30-12:30", expectedFindClassCommand);
        expectedFindClassCommand =
                new FindClassCommand(new ClassTimingContainsKeywordsPredicate(Arrays.asList("11:30-23:59")));
        assertParseSuccess(parser, "11:30-23:59", expectedFindClassCommand);
        expectedFindClassCommand =
                new FindClassCommand(new ClassTimingContainsKeywordsPredicate(Arrays.asList("23:30-23:59")));
        assertParseSuccess(parser, "23:30-23:59", expectedFindClassCommand);
    }

    /* Check for boundary values around 23:59 */
    @Test
    public void parse_invalidTimingArgs_throwsParseException() {
        assertParseFailure(parser, "11:30-12:32",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11:30-22:59",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11:30-23:58",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "11:30-24:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validMultiTimingArgs_returnsFindClassCommand() {
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(
                        new ClassTimingContainsKeywordsPredicate(Arrays.asList("11:30-12:30", "12:30-13:30")));
        assertParseSuccess(parser, "11:30-12:30 12:30-13:30", expectedFindClassCommand);
    }

    @Test
    public void parse_validMultiTimingArgsWithMultiSpaces_returnsFindClassCommand() {
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(
                        new ClassTimingContainsKeywordsPredicate(Arrays.asList("11:30-12:30", "12:30-13:30")));
        assertParseSuccess(parser, "11:30-12:30                   12:30-13:30", expectedFindClassCommand);
    }

    @Test
    public void parse_validTimingArgsWithTrailingSpaces_returnsFindClassCommand() {
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(
                        new ClassTimingContainsKeywordsPredicate(Arrays.asList("11:30-12:30", "12:30-13:30")));
        assertParseSuccess(parser, "                        11:30-12:30 12:30-13:30", expectedFindClassCommand);
    }

}
