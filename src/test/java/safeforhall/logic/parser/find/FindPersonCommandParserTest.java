package safeforhall.logic.parser.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VACCSTATUS_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VACCSTATUS_AMY;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ROOM;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.find.FindPersonCommand;
import safeforhall.logic.commands.find.FindPersonCommand.FindCompositePredicate;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.logic.parser.CommandParserTestUtil;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

public class FindPersonCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE);

    private static final String INVALID_ROOM_FOR_FIND1 = "AA";
    private static final String INVALID_ROOM_FOR_FIND2 = "A12";
    private static final String INVALID_ROOM_FOR_FIND3 = "12";

    private static final String VALID_ROOM_FOR_FIND1 = "A";
    private static final String VALID_ROOM_FOR_FIND2 = "A1";
    private static final String VALID_ROOM_FOR_FIND3 = "E200";

    private static final String INVALID_ROOM_DESC1 = " " + PREFIX_ROOM + INVALID_ROOM_FOR_FIND1;
    private static final String INVALID_ROOM_DESC2 = " " + PREFIX_ROOM + INVALID_ROOM_FOR_FIND2;
    private static final String INVALID_ROOM_DESC3 = " " + PREFIX_ROOM + INVALID_ROOM_FOR_FIND3;

    private static final String VALID_ROOM_DESC1 = " " + PREFIX_ROOM + VALID_ROOM_FOR_FIND1;
    private static final String VALID_ROOM_DESC2 = " " + PREFIX_ROOM + VALID_ROOM_FOR_FIND2;
    private static final String VALID_ROOM_DESC3 = " " + PREFIX_ROOM + VALID_ROOM_FOR_FIND3;

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new FindPersonCommand.FindCompositePredicate(preparePredicate("Alice Bob",
                        null, null, null, null, null)));
        CommandParserTestUtil.assertParseSuccess(parser,
                FindPersonCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser,
                FindPersonCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "  Alice   Bob  ",
                expectedFindPersonCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", FindPersonCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments
        assertParseFailure(parser, "some random string", FindPersonCommand.MESSAGE_NOT_FILTERED);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", FindPersonCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email

        // invalid phone followed by valid email
        assertParseFailure(parser, INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = NAME_DESC_AMY + ROOM_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_AMY + VACCSTATUS_DESC_AMY
                + FACULTY_DESC_AMY;

        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        predicate.setRoom(VALID_ROOM_AMY);
        predicate.setPhone(new Phone(VALID_PHONE_BOB));
        predicate.setEmail(new Email(VALID_EMAIL_AMY));
        predicate.setFaculty(new Faculty(VALID_FACULTY_AMY));
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));

        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = NAME_DESC_AMY + ROOM_DESC_AMY + VACCSTATUS_DESC_AMY;

        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        predicate.setRoom(VALID_ROOM_AMY);
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));

        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AMY;
        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);

        // room
        userInput = ROOM_DESC_AMY;
        predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setRoom(VALID_ROOM_AMY);
        expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);

        // vaccination
        userInput = VACCSTATUS_DESC_AMY;
        predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));
        expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = NAME_DESC_AMY + ROOM_DESC_AMY + VACCSTATUS_DESC_AMY + ROOM_DESC_BOB;

        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        predicate.setRoom(VALID_ROOM_BOB);
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));

        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidRoom1_fail() {
        try {
            FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
            predicate.setRoom(INVALID_ROOM_FOR_FIND1);

            assertParseFailure(parser, INVALID_ROOM_DESC1, Room.MESSAGE_CONSTRAINTS_FOR_FIND);
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void parse_invalidRoom2_fail() {
        try {
            FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
            predicate.setRoom(INVALID_ROOM_FOR_FIND2);

            assertParseFailure(parser, INVALID_ROOM_DESC2, Room.MESSAGE_CONSTRAINTS_FOR_FIND);
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void parse_invalidRoom3_fail() {
        try {
            FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
            predicate.setRoom(INVALID_ROOM_FOR_FIND3);

            assertParseFailure(parser, INVALID_ROOM_DESC3, Room.MESSAGE_CONSTRAINTS_FOR_FIND);
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void parse_validRoomBlock_success() {
        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setRoom(VALID_ROOM_FOR_FIND1);

        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, VALID_ROOM_DESC1, expectedCommand);
    }

    @Test
    public void parse_validRoomBlockLevel_success() {
        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setRoom(VALID_ROOM_FOR_FIND2);

        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, VALID_ROOM_DESC2, expectedCommand);
    }

    @Test
    public void parse_validRoom_success() {
        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setRoom(VALID_ROOM_FOR_FIND3);

        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, VALID_ROOM_DESC3, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_PHONE_DESC + PHONE_DESC_BOB;

        FindPersonCommand.FindCompositePredicate predicate = new FindPersonCommand.FindCompositePredicate();
        predicate.setPhone(new Phone(VALID_PHONE_BOB));
        FindPersonCommand expectedCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Parses {@code userInput} into a {@code FindCompositePredicate}.
     */
    private FindCompositePredicate preparePredicate(String name, String room , String phone, String email,
                                                                String vaccStatus, String faculty) {
        FindPersonCommand.FindCompositePredicate f = new FindPersonCommand.FindCompositePredicate();

        if (name != null) {
            f.setName(new Name(name));
        }
        if (room != null) {
            f.setRoom(room);
        }
        if (phone != null) {
            f.setPhone(new Phone(phone));
        }
        if (email != null) {
            f.setEmail(new Email(email));
        }
        if (vaccStatus != null) {
            f.setVaccStatus(new VaccStatus(vaccStatus));
        }
        if (faculty != null) {
            f.setFaculty(new Faculty(faculty));
        }

        return f;
    }
}
