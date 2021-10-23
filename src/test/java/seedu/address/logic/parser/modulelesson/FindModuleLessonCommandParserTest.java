package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_TUES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
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
        assertParseFailure(parser, "find m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validModule_returnsFindCommand() {
        FindModuleLessonCommand expectedFindModuleLessonCommand =
                new FindModuleLessonCommand(new ModuleCodeContainsKeywordsPredicate(
                        Arrays.asList(
                                String.format("%s", VALID_MODULE_CODE_CS2040)
                        )
                ));
        String userInput = String.format(" m/%s", VALID_MODULE_CODE_CS2040);
        assertParseSuccess(parser, userInput, expectedFindModuleLessonCommand);
    }

    @Test
    public void parse_emptyDay_throwsParseException() {
        assertParseFailure(parser, "find d/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDay_returnsFindCommand() {
        FindModuleLessonCommand expectedFindModuleLessonCommand =
                new FindModuleLessonCommand(new LessonDayContainsKeywordsPredicate(
                        Arrays.asList(
                                String.format("%s", VALID_LESSON_DAY_TUES)
                        )
                ));
        String userInput = String.format(" d/%s", VALID_LESSON_DAY_TUES);
        assertParseSuccess(parser, userInput, expectedFindModuleLessonCommand);
    }

    @Test
    public void parse_emptyTime_throwsParseException() {
        assertParseFailure(parser, "find t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validTime_returnsFindCommand() {
        FindModuleLessonCommand expectedFindModuleLessonCommand =
                new FindModuleLessonCommand(new LessonTimeContainsKeywordsPredicate(
                        Arrays.asList(
                                String.format("%s", VALID_LESSON_TIME_11)
                        )
                ));
        String userInput = String.format(" t/%s", VALID_LESSON_TIME_11);
        assertParseSuccess(parser, userInput, expectedFindModuleLessonCommand);
    }

    @Test
    public void parse_twoPrefixesModuleAndDay_throwsParseException() {
        assertParseFailure(parser, "find m/cs2100 d/2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH));
    }

    @Test
    public void parse_twoPrefixesTimeAndDay_throwsParseException() {
        assertParseFailure(parser, "find t/11:00 d/2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH));
    }

    @Test
    public void parse_threePrefixesModuleAndTimeAndDay_throwsParseException() {
        assertParseFailure(parser, "find m/cs2100 d/2 t/11:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleLessonCommand.MESSAGE_SINGLE_PREFIX_SEARCH));
    }
}
