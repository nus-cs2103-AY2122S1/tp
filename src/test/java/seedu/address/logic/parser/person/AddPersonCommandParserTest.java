package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELE_HANDLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.AddPersonCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.TeleHandle;
import seedu.address.testutil.PersonBuilder;

public class AddPersonCommandParserTest {
    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB)
                .withPhone(VALID_PHONE_BOB)
                .withTeleHandle(VALID_TELE_HANDLE_BOB)
                .withRemark(VALID_REMARK_BOB)
                .build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                new AddPersonCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                new AddPersonCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                new AddPersonCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                new AddPersonCommand(expectedPerson));

        // multiple telegram handles - last handle accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_AMY + TELE_HANDLE_DESC_BOB
                + REMARK_DESC_BOB, new AddPersonCommand(expectedPerson));

        // multiple remark - last remark accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB
                + REMARK_DESC_AMY + REMARK_DESC_BOB, new AddPersonCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Person expectedPerson = new PersonBuilder(AMY).build();

        // missing phone prefix, telegram handle prefix and zero lessonCodes
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + MODULE_CODE_DESC_CS2040,
                new AddPersonCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB
                        + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_EMAIL_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040, expectedMessage);

        // missing module code prefix
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB
                + VALID_MODULE_CODE_CS2030S_T12 + VALID_MODULE_CODE_CS2040, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_EMAIL_BOB
                + VALID_MODULE_CODE_CS2030S_T12 + VALID_MODULE_CODE_CS2040, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid telegram handle
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + INVALID_TELE_HANDLE_DESC + REMARK_DESC_BOB,
                TeleHandle.MESSAGE_CONSTRAINTS);

        // invalid module code
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_MODULE_CODE_DESC + MODULE_CODE_DESC_CS2030S_T12 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid remark
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + TELE_HANDLE_DESC_BOB + INVALID_REMARK_DESC,
                Remark.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_MODULE_CODE_DESC + MODULE_CODE_DESC_CS2030S_T12 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + MODULE_CODE_DESC_CS2030S_T12 + MODULE_CODE_DESC_CS2040 + TELE_HANDLE_DESC_BOB + REMARK_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }
}
