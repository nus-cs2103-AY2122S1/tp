package seedu.address.logic.parser.guest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSPORT_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DELUXE_ROOM;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_VIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DELUXE_ROOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_VIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.guest.EditGuestCommand;
import seedu.address.logic.commands.guest.EditGuestCommand.EditGuestDescriptor;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.guest.EditGuestDescriptorBuilder;

public class EditGuestCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGuestCommand.MESSAGE_USAGE);

    private EditGuestCommandParser parser = new EditGuestCommandParser();

    @Test
    public void parse_validPassportNumberArgs_returnsEditCommand() {
        EditGuestDescriptor editGuestDescriptor = new EditGuestDescriptor();
        editGuestDescriptor.setPassportNumber(new PassportNumber(VALID_PASSPORT_NUMBER_ALICE));
        editGuestDescriptor.setRoomNumber(new RoomNumber(VALID_ROOM_NUMBER_ALICE));
        assertParseSuccess(parser,
                EditGuestCommand.COMMAND_WORD
                        + PASSPORT_NUMBER_DESC_ALICE
                        + ROOM_NUMBER_DESC_ALICE,
                new EditGuestCommand(new PassportNumber(VALID_PASSPORT_NUMBER_ALICE), editGuestDescriptor));
    }

    @Test
    public void parse_invalidValueForGuest_failure() {
        String partialUserInput = EditGuestCommand.COMMAND_WORD
                + " "
                + PREFIX_PASSPORT_NUMBER
                + PASSPORT_NUMBER_FIRST_PERSON;

        assertParseFailure(parser, "editguest pn/ n/zulu",
                PassportNumber.MESSAGE_CONSTRAINTS); // invalid passport number
        assertParseFailure(parser, partialUserInput + INVALID_PASSPORT_NUMBER_DESC,
                PassportNumber.MESSAGE_CONSTRAINTS); // invalid room number
        assertParseFailure(parser, partialUserInput + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, partialUserInput + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, partialUserInput + INVALID_ROOM_NUMBER_DESC,
                RoomNumber.MESSAGE_CONSTRAINTS); // invalid room number
        assertParseFailure(parser, partialUserInput + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid room number followed by valid email
        assertParseFailure(parser, partialUserInput + INVALID_ROOM_NUMBER_DESC + EMAIL_DESC_ALICE,
                RoomNumber.MESSAGE_CONSTRAINTS);

        // valid room number followed by invalid room number. The test case for invalid room number followed
        // by valid room number
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, partialUserInput + ROOM_NUMBER_DESC_ALICE + INVALID_ROOM_NUMBER_DESC,
                RoomNumber.MESSAGE_CONSTRAINTS);


        assertParseFailure(parser, partialUserInput + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM
                        + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, partialUserInput + TAG_DESC_VIP + TAG_EMPTY + TAG_DESC_DELUXE_ROOM,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, partialUserInput + TAG_EMPTY + TAG_DESC_VIP + TAG_DESC_DELUXE_ROOM,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, partialUserInput + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ROOM_NUMBER_ALICE
                        + VALID_PASSPORT_NUMBER_ALICE,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecifiedForGuest_success() {
        PassportNumber targetPassportNumber = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditGuestCommand.COMMAND_WORD
                + PASSPORT_NUMBER_DESC_ALICE
                + TAG_DESC_DELUXE_ROOM
                + NAME_DESC_ALICE
                + EMAIL_DESC_ALICE
                + TAG_DESC_VIP
                + ROOM_NUMBER_DESC_ALICE;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withName(VALID_NAME_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withTags(VALID_TAG_VIP, VALID_TAG_DELUXE_ROOM)
                .build();

        EditGuestCommand expectedCommand = new EditGuestCommand(targetPassportNumber, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecifiedForGuest_success() {
        PassportNumber targetPassportNumber = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditGuestCommand.COMMAND_WORD
                + PASSPORT_NUMBER_DESC_ALICE
                + ROOM_NUMBER_DESC_ALICE;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .build();

        EditGuestCommand expectedCommand = new EditGuestCommand(targetPassportNumber, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFieldsForGuest_acceptsLast() {
        PassportNumber targetPassportNumber = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditGuestCommand.COMMAND_WORD
                + PASSPORT_NUMBER_DESC_BENSON
                + NAME_DESC_BENSON
                + ROOM_NUMBER_DESC_BENSON
                + PASSPORT_NUMBER_DESC_ALICE
                + TAG_DESC_DELUXE_ROOM
                + NAME_DESC_ALICE
                + EMAIL_DESC_ALICE
                + TAG_DESC_VIP
                + ROOM_NUMBER_DESC_ALICE;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withName(VALID_NAME_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withTags(VALID_TAG_VIP, VALID_TAG_DELUXE_ROOM)
                .build();

        EditGuestCommand expectedCommand = new EditGuestCommand(targetPassportNumber, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValueForGuest_success() {
        PassportNumber targetPassportNumber = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput =
                EditGuestCommand.COMMAND_WORD + PASSPORT_NUMBER_DESC_ALICE + INVALID_EMAIL_DESC + ROOM_NUMBER_DESC_ALICE
                        + EMAIL_DESC_ALICE;
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .build();
        EditGuestCommand expectedCommand = new EditGuestCommand(targetPassportNumber, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTagsForGuest_success() {
        PassportNumber targetPassportNumber = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditGuestCommand.COMMAND_WORD + PASSPORT_NUMBER_DESC_ALICE + TAG_EMPTY;

        EditGuestDescriptor descriptor =
                new EditGuestDescriptorBuilder().withPassportNumber(VALID_PASSPORT_NUMBER_ALICE).withTags().build();
        EditGuestCommand expectedCommand = new EditGuestCommand(targetPassportNumber, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
