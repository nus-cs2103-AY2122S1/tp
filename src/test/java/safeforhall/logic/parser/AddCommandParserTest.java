package safeforhall.logic.parser;

//import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.INVALID_ROOM_DESC;
//import static safeforhall.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
//import static safeforhall.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static safeforhall.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
//import static safeforhall.logic.commands.CommandTestUtil.INVALID__DESC;
//import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
//import static safeforhall.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
//import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
//import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static safeforhall.testutil.TypicalPersons.AMY;
//import static safeforhall.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.AddCommand;
//import safeforhall.model.person.Address;
//import safeforhall.model.person.Email;
//import safeforhall.model.person.Name;
////import safeforhall.model.person.Person;
//import safeforhall.model.person.Phone;
//import safeforhall.model.tag.Tag;
////import safeforhall.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    // TODO: Fix after add command is done
    // @Test
    // public void parse_allFieldsPresent_success() {
    //     Person expectedPerson = new PersonBuilder(BOB).build();

    //     // whitespace only preamble
    //     assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

    //     // multiple names - last name accepted
    //     assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

    //     // multiple phones - last phone accepted
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

    //     // multiple emails - last email accepted
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

    //     // multiple addresses - last address accepted
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
    //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

    //     // multiple tags - all accepted
    //     Person expectedPersonMultipleTags = new PersonBuilder(BOB)
    //             .build();
    //     assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
    //             + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    // }

    // TODO: Fix after add command is done
    // @Test
    // public void parse_optionalFieldsMissing_success() {
    //     // zero tags
    //     Person expectedPerson = new PersonBuilder(AMY).build();
    //     assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
    //             new AddCommand(expectedPerson));
    // }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        /*// missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);*/
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        /*assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));*/
    }
}
