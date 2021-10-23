package seedu.address.logic.parser.modulelesson;

import static seedu.address.logic.commands.CommandTestUtil.LESSON_DAY_DESC_TUES;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DAY_DESC_WED;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TIME_DESC_11_12;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TIME_DESC_12_13;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2040S_B05;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_MODULES;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_ONLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_TUES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelesson.AddModuleLessonCommand;
import seedu.address.model.modulelesson.ModuleLesson;
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

        // multiple days - last day accepted
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
}
