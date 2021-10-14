package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.notor.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.notor.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.notor.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.notor.testutil.TypicalPersons.AMY;
import static seedu.notor.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.logic.parser.person.PersonCreateCommandParser;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Name;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;
import seedu.notor.testutil.PersonBuilder;

public class PersonCreateCommandParserTest {
    private final PersonCreateCommandParser parser = new PersonCreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new PersonCreateCommand(null, expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new PersonCreateCommand(null, expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new PersonCreateCommand(null, expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + TAG_DESC_FRIEND, new PersonCreateCommand(null, expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, new PersonCreateCommand(null, expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() throws ParseException {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY,
                new PersonCreateCommand(null, expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonCreateCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_TAG_DESC
                + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonCreateCommand.MESSAGE_USAGE));
    }
}
