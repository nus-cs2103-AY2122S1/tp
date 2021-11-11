package seedu.intern.logic.parser;

import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.commands.CommandTestUtil.COURSE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.COURSE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.GRADE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.GRADE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.GRADUATION_YEARMONTH_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.GRADUATION_YEARMONTH_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.INSTITUTION_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.INSTITUTION_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_COURSE_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_GRADUATION_YEARMONTH_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_INSTITUTION_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_JOB_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.JOB_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.JOB_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.intern.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.intern.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.SKILL_DESC_PYTHON;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_GRADUATION_YEARMONTH_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_INSTITUTION_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.testutil.TypicalApplicants.AMY;
import static seedu.intern.testutil.TypicalApplicants.BOB;

import org.junit.jupiter.api.Test;

import seedu.intern.logic.commands.AddCommand;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Job;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;
import seedu.intern.model.skills.Skill;
import seedu.intern.testutil.ApplicantBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new ApplicantBuilder(BOB).withSkills(VALID_SKILL_PYTHON).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple grade - last grade accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_AMY + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON + JOB_DESC_BOB,
                new AddCommand(expectedApplicant));

        // multiple institutions - last institution accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_AMY + INSTITUTION_DESC_BOB + JOB_DESC_BOB
                + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple graduation_year_month - last graduation_year_month accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_AMY + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple courses - last course accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_AMY + COURSE_DESC_BOB + JOB_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple jobs - last job accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_AMY + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicant));

        // multiple SKILLs - all accepted
        Applicant expectedApplicantMultipleSkills = new ApplicantBuilder(BOB)
                .withSkills(VALID_SKILL_PYTHON, VALID_SKILL_JAVA)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                        + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_JAVA + SKILL_DESC_PYTHON,
                new AddCommand(expectedApplicantMultipleSkills));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero SKILLs
        Applicant expectedApplicant = new ApplicantBuilder(AMY).withSkills().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + GRADE_DESC_AMY + INSTITUTION_DESC_AMY + COURSE_DESC_AMY + JOB_DESC_AMY
                + GRADUATION_YEARMONTH_DESC_AMY, new AddCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing grade prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_GRADE_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing institution prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + VALID_INSTITUTION_BOB + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing course prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB + INSTITUTION_DESC_BOB
                + VALID_COURSE_BOB + GRADUATION_YEARMONTH_DESC_BOB, expectedMessage);

        // missing graduation_year_month prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GRADE_DESC_BOB
                + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + VALID_GRADUATION_YEARMONTH_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_GRADE_BOB
                + VALID_INSTITUTION_BOB + VALID_COURSE_BOB + VALID_GRADUATION_YEARMONTH_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_JAVA + JOB_DESC_BOB
                + SKILL_DESC_PYTHON, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_JAVA + JOB_DESC_BOB
                + SKILL_DESC_PYTHON, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + GRADE_DESC_BOB + GRADE_DESC_BOB + INSTITUTION_DESC_BOB
                + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB + JOB_DESC_BOB
                + SKILL_DESC_JAVA + SKILL_DESC_PYTHON, Email.MESSAGE_CONSTRAINTS);

        // invalid institution
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + JOB_DESC_BOB
                + GRADE_DESC_BOB + INVALID_INSTITUTION_DESC + COURSE_DESC_BOB + GRADUATION_YEARMONTH_DESC_BOB
                + SKILL_DESC_PYTHON, Institution.MESSAGE_CONSTRAINTS);

        // invalid SKILL
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + INVALID_SKILL_DESC
                + VALID_SKILL_PYTHON, Skill.MESSAGE_CONSTRAINTS);

        // invalid course
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + JOB_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + INVALID_COURSE_DESC + GRADUATION_YEARMONTH_DESC_BOB
                + SKILL_DESC_PYTHON, Course.MESSAGE_CONSTRAINTS);

        // invalid graduation year month
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + JOB_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + INVALID_GRADUATION_YEARMONTH_DESC
                + SKILL_DESC_PYTHON, GraduationYearMonth.MESSAGE_CONSTRAINTS);

        // invalid grade
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_GRADE_DESC + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_JAVA
                + VALID_SKILL_PYTHON, Grade.MESSAGE_CONSTRAINTS);

        // invalid job
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + INVALID_JOB_DESC
                + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_JAVA
                + VALID_SKILL_PYTHON, Job.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + GRADE_DESC_BOB + INSTITUTION_DESC_BOB + COURSE_DESC_BOB + JOB_DESC_BOB
                + GRADUATION_YEARMONTH_DESC_BOB + SKILL_DESC_JAVA + SKILL_DESC_PYTHON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
