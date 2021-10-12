package safeforhall.logic.parser;

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
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.FindCommand;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

public class FindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new FindCommand.FindCompositePredicate(preparePredicate("Alice Bob",
                        null, null, null, null, null)));
        CommandParserTestUtil.assertParseSuccess(parser,
                FindCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser,
                FindCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "  Alice   Bob  ",
                expectedFindCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", FindCommand.MESSAGE_NOT_FILTERED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments
        assertParseFailure(parser, "some random string", FindCommand.MESSAGE_NOT_FILTERED);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "i/ string", FindCommand.MESSAGE_NOT_FILTERED);
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

        FindCommand.FindCompositePredicate predicate = new FindCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        predicate.setRoom(new Room(VALID_ROOM_AMY));
        predicate.setPhone(new Phone(VALID_PHONE_BOB));
        predicate.setEmail(new Email(VALID_EMAIL_AMY));
        predicate.setFaculty(new Faculty(VALID_FACULTY_AMY));
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));

        FindCommand expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = NAME_DESC_AMY + ROOM_DESC_AMY + VACCSTATUS_DESC_AMY;

        FindCommand.FindCompositePredicate predicate = new FindCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        predicate.setRoom(new Room(VALID_ROOM_AMY));
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));

        FindCommand expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AMY;
        FindCommand.FindCompositePredicate predicate = new FindCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        FindCommand expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);

        // room
        userInput = ROOM_DESC_AMY;
        predicate = new FindCommand.FindCompositePredicate();
        predicate.setRoom(new Room(VALID_ROOM_AMY));
        expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);

        // vaccination
        userInput = VACCSTATUS_DESC_AMY;
        predicate = new FindCommand.FindCompositePredicate();
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));
        expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = NAME_DESC_AMY + ROOM_DESC_AMY + VACCSTATUS_DESC_AMY + ROOM_DESC_BOB;

        FindCommand.FindCompositePredicate predicate = new FindCommand.FindCompositePredicate();
        predicate.setName(new Name(VALID_NAME_AMY));
        predicate.setRoom(new Room(VALID_ROOM_BOB));
        predicate.setVaccStatus(new VaccStatus(VALID_VACCSTATUS_AMY));

        FindCommand expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_PHONE_DESC + PHONE_DESC_BOB;

        FindCommand.FindCompositePredicate predicate = new FindCommand.FindCompositePredicate();
        predicate.setPhone(new Phone(VALID_PHONE_BOB));
        FindCommand expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Parses {@code userInput} into a {@code FindCompositePredicate}.
     */
    private FindCommand.FindCompositePredicate preparePredicate(String name, String room , String phone, String email,
                                                                String vaccStatus, String faculty) {
        FindCommand.FindCompositePredicate f = new FindCommand.FindCompositePredicate();

        if (name != null) {
            f.setName(new Name(name));
        }
        if (room != null) {
            f.setRoom(new Room(room));
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
