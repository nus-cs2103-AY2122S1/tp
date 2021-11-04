package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.GITHUB_USERNAME_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.GITHUB_USERNAME_DESC_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.ID_DESC_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.modulink.logic.commands.CommandTestUtil.INVALID_GITHUB_USERNAME_DESC;
import static seedu.modulink.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.modulink.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.modulink.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.modulink.logic.commands.CommandTestUtil.INVALID_TELEGRAM_HANDLE_DESC;
import static seedu.modulink.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.TAG_DESC_CS2100;
import static seedu.modulink.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.TELEGRAM_HANDLE_DESC_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_GITHUB_USERNAME_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_AMY;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_ID_DESC, StudentId.MESSAGE_CONSTRAINTS); // invalid student ID
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_GITHUB_USERNAME_DESC,
                GitHubUsername.MESSAGE_CONSTRAINTS); // invalid GitHub username
        assertParseFailure(parser, INVALID_TELEGRAM_HANDLE_DESC,
                TelegramHandle.MESSAGE_CONSTRAINTS); // invalid Telegram handle

        // invalid phone followed by valid email
        assertParseFailure(parser, INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB
                + EMAIL_DESC_AMY + NAME_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // student ID
        userInput = ID_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withStudentId(VALID_ID_AMY).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // GitHub username
        userInput = GITHUB_USERNAME_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGitHubUsername(VALID_GITHUB_USERNAME_AMY).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Telegram handle
        userInput = TELEGRAM_HANDLE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withTelegramHandle(VALID_TELEGRAM_HANDLE_AMY).build();
        expectedCommand = new EditCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleFieldsSpecified_success() {

        // multiple names - not accepted
        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GITHUB_USERNAME_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_CS2100, String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT, "n/ ", EditCommand.MESSAGE_USAGE));

        // multiple student IDs - not accepted
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_AMY + ID_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GITHUB_USERNAME_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_CS2100, String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT, "id/ ", EditCommand.MESSAGE_USAGE));

        // multiple phone numbers - not accepted
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GITHUB_USERNAME_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_CS2100, String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT, "p/ ", EditCommand.MESSAGE_USAGE));

        // multiple emails - not accepted
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + EMAIL_DESC_BOB + GITHUB_USERNAME_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_CS2100, String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT, "e/ ", EditCommand.MESSAGE_USAGE));

        // multiple github usernames - not accepted
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GITHUB_USERNAME_DESC_AMY + GITHUB_USERNAME_DESC_BOB + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_CS2100, String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT,
                "github/ ", EditCommand.MESSAGE_USAGE));

        // multiple telegram handles - not accepted
        assertParseFailure(parser, NAME_DESC_BOB + ID_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + GITHUB_USERNAME_DESC_BOB + TELEGRAM_HANDLE_DESC_AMY + TELEGRAM_HANDLE_DESC_BOB
                + TAG_DESC_CS2100, String.format(MESSAGE_DUPLICATE_PREFIX_FORMAT,
                "tele/ ", EditCommand.MESSAGE_USAGE));

    }

}
