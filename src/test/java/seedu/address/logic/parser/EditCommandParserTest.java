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
import static seedu.address.logic.commands.CommandTestUtil.NOTES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOTES_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENT_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMPLOYMENT_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPECTED_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPERIENCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_OF_EDUCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_OF_EDUCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 x/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid role
        assertParseFailure(parser, "1" + INVALID_EMPLOYMENT_TYPE_DESC,
                EmploymentType.MESSAGE_CONSTRAINTS); // invalid employment type
        assertParseFailure(parser, "1" + INVALID_EXPECTED_SALARY_DESC,
                ExpectedSalary.MESSAGE_CONSTRAINTS); // invalid expected salary
        assertParseFailure(parser, "1" + INVALID_LEVEL_OF_EDUCATION_DESC,
                LevelOfEducation.MESSAGE_CONSTRAINTS); // invalid level of education
        assertParseFailure(parser, "1" + INVALID_EXPERIENCE_DESC,
                Experience.MESSAGE_CONSTRAINTS); // invalid experience
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_DESC, Interview.MESSAGE_CONSTRAINTS); // invalid tag


        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND
                + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY
                + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB
                + TAG_DESC_HUSBAND + NAME_DESC_AMY + EMAIL_DESC_AMY + ROLE_DESC_AMY
                + EMPLOYMENT_TYPE_DESC_AMY + EXPECTED_SALARY_DESC_AMY
                + LEVEL_OF_EDUCATION_DESC_AMY + EXPERIENCE_DESC_AMY + TAG_DESC_FRIEND
                + INTERVIEW_DESC_AMY + NOTES_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withRole(VALID_ROLE_AMY)
                .withEmploymentType(VALID_EMPLOYMENT_TYPE_AMY)
                .withExpectedSalary(VALID_EXPECTED_SALARY_AMY)
                .withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_AMY)
                .withExperience(VALID_EXPERIENCE_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withInterview(VALID_INTERVIEW_AMY)
                .withNotes(VALID_NOTES_AMY)
                .build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withRole(VALID_ROLE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // employment type
        userInput = targetIndex.getOneBased() + EMPLOYMENT_TYPE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmploymentType(VALID_EMPLOYMENT_TYPE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expected salary
        userInput = targetIndex.getOneBased() + EXPECTED_SALARY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withExpectedSalary(VALID_EXPECTED_SALARY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // level of education
        userInput = targetIndex.getOneBased() + LEVEL_OF_EDUCATION_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // experience
        userInput = targetIndex.getOneBased() + EXPERIENCE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withExperience(VALID_EXPERIENCE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interview
        userInput = targetIndex.getOneBased() + INTERVIEW_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withInterview(VALID_INTERVIEW_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // notes
        userInput = targetIndex.getOneBased() + NOTES_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withNotes(VALID_NOTES_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND + INTERVIEW_DESC_AMY
                + ROLE_DESC_AMY + EMPLOYMENT_TYPE_DESC_AMY
                + EXPECTED_SALARY_DESC_AMY + LEVEL_OF_EDUCATION_DESC_AMY
                + EXPERIENCE_DESC_AMY + NOTES_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROLE_DESC_BOB + EMPLOYMENT_TYPE_DESC_BOB + INTERVIEW_DESC_BOB
                + EXPECTED_SALARY_DESC_BOB + LEVEL_OF_EDUCATION_DESC_BOB
                + EXPERIENCE_DESC_BOB + TAG_DESC_HUSBAND + NOTES_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withRole(VALID_ROLE_BOB)
                .withEmploymentType(VALID_EMPLOYMENT_TYPE_BOB)
                .withExpectedSalary(VALID_EXPECTED_SALARY_BOB)
                .withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_BOB)
                .withExperience(VALID_EXPERIENCE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .withInterview(VALID_INTERVIEW_BOB)
                .withNotes(VALID_NOTES_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC
                + PHONE_DESC_BOB + ROLE_DESC_BOB + LEVEL_OF_EDUCATION_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withRole(VALID_ROLE_BOB)
                .withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
