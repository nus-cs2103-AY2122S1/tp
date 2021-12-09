package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.times.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.times.logic.commands.CommandTestUtil.ADDRESS_DESC_NOK;
import static seedu.times.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.times.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.times.logic.commands.CommandTestUtil.EMAIL_DESC_NOK;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.times.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.times.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.times.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.times.logic.commands.CommandTestUtil.NAME_DESC_NOK;
import static seedu.times.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.times.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.times.logic.commands.CommandTestUtil.PHONE_DESC_NOK;
import static seedu.times.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.times.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.times.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.times.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.times.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.times.logic.commands.CommandTestUtil.VALID_PREFIX_NOK;
import static seedu.times.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.times.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.times.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.times.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.times.model.tag.Tag.MAX_TAG_LENGTH;
import static seedu.times.model.tag.Tag.MAX_TAG_NUMBER;
import static seedu.times.model.tag.Tag.MESSAGE_CONSTRAINTS_TOO_LONG;
import static seedu.times.model.tag.Tag.MESSAGE_CONSTRAINTS_TOO_MANY;
import static seedu.times.testutil.TypicalTimestable.AMY;
import static seedu.times.testutil.TypicalTimestable.BOB;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.AddCommand;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Phone;
import seedu.times.model.person.Student;
import seedu.times.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                        + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                new AddCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                        + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                new AddCommand(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                        + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                new AddCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                        + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                new AddCommand(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND
                        + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                new AddCommand(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                        + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK
                        + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK
                        + ADDRESS_DESC_NOK, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK
                        + ADDRESS_DESC_NOK, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + ADDRESS_DESC_BOB + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK
                        + ADDRESS_DESC_NOK, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + VALID_ADDRESS_BOB + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK
                        + ADDRESS_DESC_NOK, expectedMessage);

        // missing nok prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + VALID_ADDRESS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_ADDRESS_BOB + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK
                        + ADDRESS_DESC_NOK, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK
                        + EMAIL_DESC_NOK + ADDRESS_DESC_NOK, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK
                        + EMAIL_DESC_NOK + ADDRESS_DESC_NOK, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK
                        + EMAIL_DESC_NOK + ADDRESS_DESC_NOK, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK
                        + EMAIL_DESC_NOK + ADDRESS_DESC_NOK, Address.MESSAGE_CONSTRAINTS);

        // invalid nok
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + VALID_PREFIX_NOK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK
                + ADDRESS_DESC_NOK, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK
                        + ADDRESS_DESC_NOK + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // Too many tags
        String userInput = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB;
        for (int i = 0; i < MAX_TAG_NUMBER + 1; i++) {
            userInput += TAG_DESC_FRIEND + i;
        }
        userInput += VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK;
        assertParseFailure(parser, userInput, MESSAGE_CONSTRAINTS_TOO_MANY);

        // Tag too many characters
        String invalidTag = " " + PREFIX_TAG;
        for (int i = 0; i < MAX_TAG_LENGTH + 1; i++) {
            invalidTag += "A";
        }
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + invalidTag
                + VALID_PREFIX_NOK + NAME_DESC_NOK + PHONE_DESC_NOK + EMAIL_DESC_NOK + ADDRESS_DESC_NOK,
                MESSAGE_CONSTRAINTS_TOO_LONG);

    }
}
