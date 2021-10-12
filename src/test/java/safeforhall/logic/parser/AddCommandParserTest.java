package safeforhall.logic.parser;

import static safeforhall.logic.commands.CommandTestUtil.COLLECTION_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.COLLECTION_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.FACULTY_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.FET_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.FET_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_COLLECTIONDATE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_FETDATE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_ROOM_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_VACCSTATUS_DESC;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VACCSTATUS_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VACCSTATUS_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VACCSTATUS_BOB;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static safeforhall.testutil.TypicalPersons.AMY;
import static safeforhall.testutil.TypicalPersons.BOB;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.AddCommand;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;
import safeforhall.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple rooms - last room accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_AMY
                + ROOM_DESC_BOB + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple faculties - last faculty accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_AMY + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple vaccination status - last vaccStatus accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_AMY + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple fet dates - last fet date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_AMY + VACCSTATUS_DESC_BOB
                + FET_DESC_AMY + FET_DESC_BOB + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));

        // multiple collection dates - last collection date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_AMY + VACCSTATUS_DESC_BOB
                + FET_DESC_BOB + COLLECTION_DESC_AMY + COLLECTION_DESC_BOB, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Person expectedPerson = new PersonBuilder(AMY).build();

        // missing lastFetDate
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ROOM_DESC_AMY
                + VACCSTATUS_DESC_AMY + FACULTY_DESC_AMY + COLLECTION_DESC_AMY, new AddCommand(expectedPerson));

        // missing lastCollectionDate
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ROOM_DESC_AMY
                + VACCSTATUS_DESC_AMY + FACULTY_DESC_AMY + FET_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                        + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ROOM_DESC_BOB
                        + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB, expectedMessage);

        // missing room prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ROOM_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB, expectedMessage);

        // missing faculty prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + VALID_FACULTY_BOB + VACCSTATUS_DESC_BOB, expectedMessage);

        // missing vaccination prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VALID_VACCSTATUS_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ROOM_BOB
                + VALID_FACULTY_BOB + VALID_VACCSTATUS_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB + FET_DESC_BOB
                + COLLECTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB + FET_DESC_BOB
                + COLLECTION_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB + FET_DESC_BOB
                + COLLECTION_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid room
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ROOM_DESC
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB + FET_DESC_BOB
                + COLLECTION_DESC_BOB, Room.MESSAGE_CONSTRAINTS);

        // invalid faculty
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + INVALID_FACULTY_DESC + VACCSTATUS_DESC_BOB + FET_DESC_BOB
                + COLLECTION_DESC_BOB, Faculty.MESSAGE_CONSTRAINTS);

        // invalid vaccination status
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + INVALID_VACCSTATUS_DESC + FET_DESC_BOB
                + COLLECTION_DESC_BOB, VaccStatus.MESSAGE_CONSTRAINTS);

        // invalid FET date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + INVALID_VACCSTATUS_DESC + INVALID_FETDATE_DESC
                + COLLECTION_DESC_BOB, LastDate.MESSAGE_CONSTRAINTS);

        // invalid collection date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ROOM_DESC_BOB
                + FACULTY_DESC_BOB + INVALID_VACCSTATUS_DESC + FET_DESC_BOB
                + INVALID_COLLECTIONDATE_DESC, LastDate.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ROOM_DESC
                + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB + FET_DESC_BOB
                + COLLECTION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ROOM_DESC_BOB + FACULTY_DESC_BOB + VACCSTATUS_DESC_BOB + FET_DESC_BOB + COLLECTION_DESC_BOB,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
