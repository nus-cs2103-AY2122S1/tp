package seedu.intern.logic.parser;

import static seedu.intern.logic.commands.CommandTestUtil.COURSE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.COURSE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.GRADUATION_YEARMONTH_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.INSTITUTION_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.INSTITUTION_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.JOB_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.JOB_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.intern.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.SKILL_DESC_PYTHON;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADUATION_YEARMONTH_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_INSTITUTION_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_INSTITUTION_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_JOB_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_JOB_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intern.logic.commands.FilterCommand.MESSAGE_NOT_FILTERED;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.FilterCommand;
import seedu.intern.logic.commands.FilterCommand.FilterApplicantDescriptor;
import seedu.intern.testutil.FilterApplicantDescriptorBuilder;


public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_NOT_FILTERED, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsAll_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withJobs(VALID_JOB_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB + JOB_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusGrade_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withJobs(VALID_JOB_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusInstitution_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withJobs(VALID_JOB_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusApplicationStatus_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withJobs(VALID_JOB_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + JOB_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusCourses_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withJobs(VALID_JOB_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusGradYearMonth_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withJobs(VALID_JOB_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + SKILL_DESC_PYTHON + STATUS_DESC_BOB + JOB_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusSkills_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withJobs(VALID_JOB_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + STATUS_DESC_BOB + JOB_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validArgsMinusJob_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .withInstitution(VALID_INSTITUTION_BOB)
                .withApplicationStatus(VALID_STATUS_BOB)
                .withCourses(VALID_COURSE_BOB)
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + STATUS_DESC_BOB + SKILL_DESC_PYTHON,
                new FilterCommand(expected));
    }


    @Test
    public void parse_validGrade_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGrade(VALID_GRADE_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADE_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validInstitution_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withInstitution(VALID_INSTITUTION_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INSTITUTION_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validApplicationStatus_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STATUS_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validCourses_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withCourses(VALID_COURSE_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COURSE_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validGradYearMonth_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withGraduationYearMonth(VALID_GRADUATION_YEARMONTH_BOB)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GRADUATION_YEARMONTH_DESC_BOB,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validSkill_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withSkills(VALID_SKILL_PYTHON)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SKILL_DESC_PYTHON,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiInstitution_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withInstitution(VALID_INSTITUTION_BOB, VALID_INSTITUTION_AMY)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INSTITUTION_DESC_BOB + INSTITUTION_DESC_AMY,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiApplicationStatus_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withApplicationStatus(VALID_STATUS_BOB, VALID_STATUS_AMY)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STATUS_DESC_BOB + STATUS_DESC_AMY,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiCourses_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withCourses(VALID_COURSE_BOB, VALID_COURSE_AMY)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COURSE_DESC_BOB + COURSE_DESC_AMY,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiSkill_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withSkills(VALID_SKILL_PYTHON, VALID_SKILL_JAVA)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SKILL_DESC_PYTHON + SKILL_DESC_JAVA,
                new FilterCommand(expected));
    }

    @Test
    public void parse_validMultiJob_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterApplicantDescriptor expected = new FilterApplicantDescriptorBuilder()
                .withJobs(VALID_JOB_BOB, VALID_JOB_AMY)
                .build();


        assertParseSuccess(parser, PREAMBLE_WHITESPACE + JOB_DESC_AMY + JOB_DESC_BOB,
                new FilterCommand(expected));
    }
}
