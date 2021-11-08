package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMPLOYMENT_TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMPLOYMENT_TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_SALARY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EXPECTED_SALARY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EXPERIENCE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EXPERIENCE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEW_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMPLOYMENT_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPECTED_SALARY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXPERIENCE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERVIEW_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LEVEL_OF_EDUCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_OF_EDUCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_OF_EDUCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NOTES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOTES_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_OF_EDUCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND)
                .withNotes(VALID_NOTES_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ROLE_DESC_BOB
                + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple roles - last role accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_AMY + ROLE_DESC_BOB
                + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple employment types - last employment type accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_AMY
                + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple expected salaries - last expected salary accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB
                + EXPECTED_SALARY_DESC_AMY + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple levels of education - last level of education accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB
                + EXPECTED_SALARY_DESC_BOB + LEVEL_OF_EDUCATION_DESC_AMY
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple experiences - last experience accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB
                + EXPECTED_SALARY_DESC_BOB + LEVEL_OF_EDUCATION_DESC_BOB
                + EXPERIENCE_DESC_AMY + EXPERIENCE_DESC_BOB
                + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB
                + EXPECTED_SALARY_DESC_BOB + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_BOB,
                new AddCommand(expectedPersonMultipleTags));

        // multiple interviews - last interview accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB
                + EXPECTED_SALARY_DESC_BOB + LEVEL_OF_EDUCATION_DESC_BOB
                + EXPERIENCE_DESC_BOB + TAG_DESC_FRIEND + INTERVIEW_DESC_AMY + INTERVIEW_DESC_BOB
                + NOTES_DESC_BOB, new AddCommand(expectedPerson));

        // multiple notes - last notes accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB
                + EXPECTED_SALARY_DESC_BOB + LEVEL_OF_EDUCATION_DESC_BOB
                + EXPERIENCE_DESC_BOB + TAG_DESC_FRIEND + INTERVIEW_DESC_BOB + NOTES_DESC_AMY
                + NOTES_DESC_BOB, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPersonNoTag = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ROLE_DESC_AMY + EMPLOYMENT_TYPE_DESC_AMY + EXPECTED_SALARY_DESC_AMY
                + LEVEL_OF_EDUCATION_DESC_AMY + EXPERIENCE_DESC_AMY + INTERVIEW_DESC_AMY + NOTES_DESC_AMY,
                new AddCommand(expectedPersonNoTag));

        // zero interview
        Person expectedPersonNoInterview = new PersonBuilder(AMY).withInterview("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ROLE_DESC_AMY + EMPLOYMENT_TYPE_DESC_AMY + EXPECTED_SALARY_DESC_AMY
                        + LEVEL_OF_EDUCATION_DESC_AMY + EXPERIENCE_DESC_AMY + TAG_DESC_FRIEND + NOTES_DESC_AMY,
                new AddCommand(expectedPersonNoInterview));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing role prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ROLE_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing employmentType prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + VALID_EMPLOYMENT_TYPE_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing expected salary prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + VALID_EXPECTED_SALARY_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing level of education prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + VALID_LEVEL_OF_EDUCATION_BOB + EXPERIENCE_DESC_BOB, expectedMessage);

        // missing experience prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + VALID_EXPERIENCE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                + VALID_ROLE_BOB + VALID_EMPLOYMENT_TYPE_BOB + VALID_EXPECTED_SALARY_BOB
                + VALID_LEVEL_OF_EDUCATION_BOB + VALID_EXPERIENCE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid role
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ROLE_DESC + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Role.MESSAGE_CONSTRAINTS);

        // invalid employment type
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + INVALID_EMPLOYMENT_TYPE_DESC + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, EmploymentType.MESSAGE_CONSTRAINTS);

        // invalid expected salary
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + INVALID_EXPECTED_SALARY_DESC
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ExpectedSalary.MESSAGE_CONSTRAINTS);

        // invalid level of education
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + INVALID_LEVEL_OF_EDUCATION_DESC + EXPERIENCE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, LevelOfEducation.MESSAGE_CONSTRAINTS);

        // invalid experience
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + INVALID_EXPERIENCE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Experience.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB
                + INVALID_TAG_DESC + TAG_DESC_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // invalid interview
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB + INVALID_INTERVIEW_DESC
                + TAG_DESC_FRIEND + TAG_DESC_FRIEND, Interview.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + EXPERIENCE_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + EXPECTED_SALARY_DESC_BOB
                + LEVEL_OF_EDUCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
