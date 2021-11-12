package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.teachbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.teachbook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.teachbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.teachbook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.teachbook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.teachbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.teachbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.teachbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.teachbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.teachbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.teachbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.teachbook.testutil.TypicalStudents.AMY;
import static seedu.teachbook.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.teachbook.logic.commands.AddCommand;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.tag.Tag;
import seedu.teachbook.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Student expectedStudent = new StudentBuilder(BOB, true).withTags(VALID_TAG_FRIEND).withRemark("").build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple addresses - last teachbook accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB, true)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).withRemark("").build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY, true).withTags().withGrade("")
                .withRemark("").build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
