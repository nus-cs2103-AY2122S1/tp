package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REPONAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTNUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REPONAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REPONAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTNUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTNUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTNUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.commons.RepoName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private final AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + EMAIL_DESC_BOB
                + STUDENTNUMBER_DESC_BOB + USERNAME_DESC_BOB + REPONAME_DESC_BOB + TAG_DESC_FRIEND,
                new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB
                + USERNAME_DESC_BOB + REPONAME_DESC_BOB + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB
                + USERNAME_DESC_BOB + REPONAME_DESC_BOB + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple usernames - last username accepted
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB + USERNAME_DESC_AMY
                + USERNAME_DESC_BOB + REPONAME_DESC_BOB + TAG_DESC_FRIEND, new AddStudentCommand(expectedStudent));

        // multiple reponames - last reponame accepted
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB + REPONAME_DESC_AMY
                + REPONAME_DESC_BOB + USERNAME_DESC_BOB + TAG_DESC_FRIEND,
                new AddStudentCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB + REPONAME_DESC_BOB
                + USERNAME_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddStudentCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + STUDENTNUMBER_DESC_AMY + USERNAME_DESC_AMY
                + REPONAME_DESC_AMY, new AddStudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_EMAIL_BOB + STUDENTNUMBER_DESC_BOB,
                expectedMessage);

        // missing studentNumber prefix
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + VALID_STUDENTNUMBER_BOB,
                expectedMessage);

        // missing repoName prefix
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + VALID_STUDENTNUMBER_BOB
                        + VALID_REPONAME_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_EMAIL_BOB + VALID_STUDENTNUMBER_BOB
                        + VALID_USERNAME_BOB + VALID_REPONAME_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_EMAIL_DESC + STUDENTNUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // invalid studentNumber
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + INVALID_STUDENTNUMBER_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, StudentNumber.MESSAGE_CONSTRAINTS);

        // invalid userName
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB
                + INVALID_USERNAME_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, UserName.MESSAGE_CONSTRAINTS);

        // invalid repoName
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB
                + INVALID_REPONAME_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, RepoName.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + EMAIL_DESC_BOB + STUDENTNUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
