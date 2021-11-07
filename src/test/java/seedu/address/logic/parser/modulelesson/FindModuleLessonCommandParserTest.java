package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_TUES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.logic.commands.modulelesson.FindModuleLessonCommand.MESSAGE_INVALID_DAY;
import static seedu.address.logic.commands.modulelesson.FindModuleLessonCommand.MESSAGE_INVALID_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelesson.FindModuleLessonCommand;
import seedu.address.model.modulelesson.LessonDayContainsKeywordsPredicate;
import seedu.address.model.modulelesson.LessonTimeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;

public class FindModuleLessonCommandParserTest {

    private FindModuleLessonCommandParser parser = new FindModuleLessonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyModule_throwsParseException() {
        assertParseFailure(parser, "find " + PREFIX_MODULE_CODE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validModule_returnsFindCommand() {
        FindModuleLessonCommand expectedFindModuleLessonCommand = new FindModuleLessonCommand(
                new ModuleCodeContainsKeywordsPredicate(Arrays.asList(String.format("%s", VALID_MODULE_CODE_CS2040)))
        );
        String userInput = String.format(" %s%s", PREFIX_MODULE_CODE, VALID_MODULE_CODE_CS2040);
        assertParseSuccess(parser, userInput, expectedFindModuleLessonCommand);
    }

    @Test
    public void parse_emptyDay_throwsParseException() {
        assertParseFailure(parser, "find " + PREFIX_LESSON_DAY,
                String.format(MESSAGE_INVALID_DAY, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDay_returnsFindCommand() {
        FindModuleLessonCommand expectedFindModuleLessonCommand =
                new FindModuleLessonCommand(new LessonDayContainsKeywordsPredicate(
                        Arrays.asList(
                                String.format("%s", VALID_LESSON_DAY_TUES)
                        )
                ));
        String userInput = String.format(" %s%s", PREFIX_LESSON_DAY, VALID_LESSON_DAY_TUES);
        assertParseSuccess(parser, userInput, expectedFindModuleLessonCommand);
    }

    @Test
    public void parse_emptyTime_throwsParseException() {
        assertParseFailure(parser, "find " + PREFIX_LESSON_TIME,
                String.format(MESSAGE_INVALID_TIME, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTime_returnsFindCommand() {
        FindModuleLessonCommand expectedFindModuleLessonCommand =
                new FindModuleLessonCommand(new LessonTimeContainsKeywordsPredicate(
                        Arrays.asList(
                                String.format("%s", VALID_LESSON_TIME_11)
                        )
                ));
        String userInput = String.format(" %s%s", PREFIX_LESSON_TIME, VALID_LESSON_TIME_11);
        assertParseSuccess(parser, userInput, expectedFindModuleLessonCommand);
    }

    @Test
    public void parse_twoPrefixesModuleAndDay_throwsParseException() {
        assertParseFailure(parser, String.format("find %scs2100 %s2", PREFIX_MODULE_CODE, PREFIX_LESSON_DAY),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH));
    }

    @Test
    public void parse_twoPrefixesTimeAndDay_throwsParseException() {
        assertParseFailure(parser, String.format("find %s11:00 %s2", PREFIX_LESSON_TIME, PREFIX_LESSON_DAY),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH));
    }

    @Test
    public void parse_threePrefixesModuleAndTimeAndDay_throwsParseException() {
        assertParseFailure(parser,
                String.format("find %scs2100 %s2 %s11:00", PREFIX_MODULE_CODE, PREFIX_LESSON_DAY, PREFIX_LESSON_TIME),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH)
        );
    }
}
