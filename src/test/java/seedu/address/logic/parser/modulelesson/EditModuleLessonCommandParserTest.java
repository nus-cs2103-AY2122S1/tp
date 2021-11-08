package seedu.address.logic.parser.modulelesson;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_DAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DAY_DESC_TUES;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_DAY_DESC_WED;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TIME_DESC_11_12;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_MODULES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_TUES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_DAY_WED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TIME_11_12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040S_B05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_REMARK;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_NO_FIELD_PROVIDED;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.parseLessonTime;
import static seedu.address.model.util.SampleDataUtil.parseModuleCode;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.EditLessonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;
import seedu.address.testutil.EditLessonDescriptorBuilder;

public class EditModuleLessonCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

    private EditModuleLessonCommandParser p = new EditModuleLessonCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(p, VALID_MODULE_CODE_CS2040S_B05, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(p, "1", MESSAGE_NO_FIELD_PROVIDED);

        // no index and no field specified
        assertParseFailure(p, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(p, "-1" + MODULE_CODE_DESC_CS2030S_T12, MESSAGE_INVALID_FORMAT);

        // zero
        assertParseFailure(p, "0" + MODULE_CODE_DESC_CS2030S_T12, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(p, "0 DEBUGGING SUCKS" + MODULE_CODE_DESC_CS2030S_T12,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(p, "0 i/DEBUGGING SUCKS" + MODULE_CODE_DESC_CS2030S_T12,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(p, "1" + INVALID_MODULE_CODE_DESC, ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(p, "1" + INVALID_LESSON_DAY_DESC, LessonDay.MESSAGE_CONSTRAINTS);
        assertParseFailure(p, "1" + INVALID_LESSON_TIME_DESC, LessonTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(p, "1" + INVALID_REMARK_DESC, Remark.MESSAGE_CONSTRAINTS);

        // invalid module code followed by valid day
        assertParseFailure(p, "1" + INVALID_MODULE_CODE_DESC + REMARK_DESC_MODULES,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // valid module code followed by invalid module code
        assertParseFailure(p, "1" + MODULE_CODE_DESC_CS2030S_T12 + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(p,
                "1" + INVALID_MODULE_CODE_DESC + INVALID_LESSON_DAY_DESC + REMARK_DESC_MODULES,
                ModuleCode.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_FIRST;
        StringBuilder sb = new StringBuilder(targetIndex.getOneBased() + "");
        sb.append(MODULE_CODE_DESC_CS2030S_T12)
                .append(LESSON_DAY_DESC_TUES)
                .append(LESSON_TIME_DESC_11_12)
                .append(REMARK_DESC_MODULES);

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12))
                .withLessonDay(new LessonDay(VALID_LESSON_DAY_TUES))
                .withLessonStartTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(0))
                .withLessonEndTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(1))
                .withRemark(new Remark(VALID_MODULE_LESSON_REMARK)).build();
        EditModuleLessonCommand expectedCommand = new EditModuleLessonCommand(targetIndex, descriptor);

        assertParseSuccess(p, sb.toString(), expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        StringBuilder sb = new StringBuilder(targetIndex.getOneBased() + "");
        sb.append(MODULE_CODE_DESC_CS2030S_T12)
                .append(LESSON_DAY_DESC_TUES);

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12))
                .withLessonDay(new LessonDay("2")).build();
        EditModuleLessonCommand expectedCommand = new EditModuleLessonCommand(targetIndex, descriptor);

        assertParseSuccess(p, sb.toString(), expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() throws ParseException {
        Index targetIndex = INDEX_FIRST;
        String arg = targetIndex.getOneBased() + "";
        EditLessonDescriptor descriptor;
        EditModuleLessonCommand expectedCommand;

        // ModuleCode
        descriptor = new EditLessonDescriptorBuilder()
                .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12)).build();
        expectedCommand = new EditModuleLessonCommand(targetIndex, descriptor);
        assertParseSuccess(p, arg + MODULE_CODE_DESC_CS2030S_T12, expectedCommand);

        // day
        descriptor = new EditLessonDescriptorBuilder()
                .withLessonDay(new LessonDay(VALID_LESSON_DAY_TUES)).build();
        expectedCommand = new EditModuleLessonCommand(targetIndex, descriptor);
        assertParseSuccess(p, arg + LESSON_DAY_DESC_TUES, expectedCommand);

        // time
        descriptor = new EditLessonDescriptorBuilder()
                .withLessonStartTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(0))
                .withLessonEndTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(1)).build();
        expectedCommand = new EditModuleLessonCommand(targetIndex, descriptor);
        assertParseSuccess(p, arg + LESSON_TIME_DESC_11_12, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() throws ParseException {
        Index ind = INDEX_FIRST;
        StringBuilder sb = new StringBuilder(ind.getOneBased() + "");
        sb.append(MODULE_CODE_DESC_CS2030S_T12).append(LESSON_DAY_DESC_TUES)
                .append(MODULE_CODE_DESC_CS2040).append(LESSON_DAY_DESC_TUES)
                .append(MODULE_CODE_DESC_CS2030S_T12).append(LESSON_DAY_DESC_WED)
                .append(LESSON_TIME_DESC_11_12).append(REMARK_DESC_MODULES);

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12))
                .withLessonDay(new LessonDay(VALID_LESSON_DAY_WED))
                .withLessonStartTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(0))
                .withLessonEndTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(1))
                .withRemark(new Remark(VALID_MODULE_LESSON_REMARK)).build();
        EditModuleLessonCommand expectedCommand = new EditModuleLessonCommand(ind, descriptor);

        assertParseSuccess(p, sb.toString(), expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() throws ParseException {
        Index ind = INDEX_FIRST;
        String arg = ind.getOneBased() + "";
        EditLessonDescriptor descriptor;
        EditModuleLessonCommand expectedCommand;

        // no other valid values specified
        descriptor = new EditLessonDescriptorBuilder().withLessonDay(new LessonDay(VALID_LESSON_DAY_WED)).build();
        expectedCommand = new EditModuleLessonCommand(ind, descriptor);
        assertParseSuccess(p, arg + INVALID_LESSON_DAY_DESC + LESSON_DAY_DESC_WED, expectedCommand);

        // other valid values specified
        descriptor = new EditLessonDescriptorBuilder().withLessonDay(new LessonDay(VALID_LESSON_DAY_WED))
                .withLessonStartTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(0))
                .withLessonEndTime(parseLessonTime(VALID_LESSON_TIME_11_12).get(1)).build();
        expectedCommand = new EditModuleLessonCommand(ind, descriptor);
        assertParseSuccess(p, arg + LESSON_TIME_DESC_11_12 + INVALID_LESSON_DAY_DESC + LESSON_DAY_DESC_WED,
                expectedCommand);
    }
}
