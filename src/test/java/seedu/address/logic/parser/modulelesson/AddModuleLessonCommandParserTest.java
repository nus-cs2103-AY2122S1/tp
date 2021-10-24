package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_DAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DAY_DESC_TUES;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DAY_DESC_WED;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TIME_DESC_11_12;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TIME_DESC_12_13;
import static seedu.address.logic.commands.CommandTestUtil.MISSING_LESSON_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2040S_B05;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_MODULES;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_ONLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_TUES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_11_12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelesson.AddModuleLessonCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;
import seedu.address.testutil.ModuleLessonBuilder;

public class AddModuleLessonCommandParserTest {
    private final AddModuleLessonCommandParser parser = new AddModuleLessonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleLesson expectedModuleLesson = new ModuleLessonBuilder()
                .withModuleCode(VALID_MODULE_CODE_CS2030S_T12)
                .withLessonDay(VALID_LESSON_DAY_TUES)
                .withLessonStartTime(VALID_LESSON_TIME_11)
                .withLessonEndTime(VALID_LESSON_TIME_12)
                .withRemark(VALID_MODULE_LESSON_REMARK)
                .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MODULE_CODE_DESC_CS2030S_T12
                        + LESSON_DAY_DESC_TUES + LESSON_TIME_DESC_11_12 + REMARK_DESC_MODULES,
                new AddModuleLessonCommand(expectedModuleLesson));

        // multiple modules - last module accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2040S_B05 + MODULE_CODE_DESC_CS2030S_T12
                        + LESSON_DAY_DESC_TUES + LESSON_TIME_DESC_11_12 + REMARK_DESC_MODULES,
                new AddModuleLessonCommand(expectedModuleLesson));

        // multiple lesson days - last day accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_WED
                        + LESSON_DAY_DESC_TUES + LESSON_TIME_DESC_11_12 + REMARK_DESC_MODULES,
                new AddModuleLessonCommand(expectedModuleLesson));

        // multiple timings - last timing accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES
                        + LESSON_TIME_DESC_12_13 + LESSON_TIME_DESC_11_12 + REMARK_DESC_MODULES,
                new AddModuleLessonCommand(expectedModuleLesson));

        // multiple remark - last remark accepted
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES
                        + LESSON_TIME_DESC_11_12 + REMARK_DESC_ONLINE + REMARK_DESC_MODULES,
                new AddModuleLessonCommand(expectedModuleLesson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        ModuleLesson expectedModuleLesson = new ModuleLessonBuilder()
                .withModuleCode(VALID_MODULE_CODE_CS2030S_T12)
                .withLessonDay(VALID_LESSON_DAY_TUES)
                .withLessonStartTime(VALID_LESSON_TIME_11)
                .withLessonEndTime(VALID_LESSON_TIME_12)
                .build();

        // missing remark prefix
        assertParseSuccess(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES + LESSON_TIME_DESC_11_12,
                new AddModuleLessonCommand(expectedModuleLesson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleLessonCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser, VALID_MODULE_CODE_CS2030S_T12 + LESSON_DAY_DESC_TUES
                + LESSON_TIME_DESC_11_12, expectedMessage);

        // missing lesson day prefix
        assertParseFailure(parser, MODULE_CODE_DESC_CS2030S_T12 + VALID_LESSON_DAY_TUES
                + LESSON_TIME_DESC_11_12, expectedMessage);

        // missing lesson time prefix
        assertParseFailure(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES
                + VALID_LESSON_TIME_11_12, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module code
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC + LESSON_DAY_DESC_TUES + LESSON_TIME_DESC_11_12,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid lesson day
        assertParseFailure(parser, MODULE_CODE_DESC_CS2030S_T12 + INVALID_LESSON_DAY_DESC + LESSON_TIME_DESC_11_12,
                LessonDay.MESSAGE_CONSTRAINTS);

        // invalid lesson time - not a lesson time
        assertParseFailure(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES + INVALID_LESSON_TIME_DESC,
                LessonTime.MESSAGE_CONSTRAINTS);

        // invalid lesson time - Missing both start and end time
        assertParseFailure(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES + MISSING_LESSON_TIME_DESC,
                ParserUtil.MESSAGE_MISSING_TIME);

        // invalid lesson time - Start time is after end time
        assertParseFailure(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES + INVALID_LESSON_DURATION_DESC,
                ParserUtil.MESSAGE_INVALID_LESSON_DURATION);

        // invalid remark
        assertParseFailure(parser, MODULE_CODE_DESC_CS2030S_T12 + LESSON_DAY_DESC_TUES
                        + LESSON_TIME_DESC_11_12 + INVALID_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS);
    }
}
