package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.notor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.notor.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.executors.person.PersonEditExecutor.PersonEditDescriptor;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Name;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Phone;
import seedu.notor.testutil.PersonEditDescriptorBuilder;

public class PersonEditCommandParserTest {

    private final NotorParser notorParser = new NotorParser();

    //    @Test
    //    public void parse_missingParts_failure() {
    //
    //        // no index specified
    //        String invalidCommand = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
    //        String noIndex = String.format("person /edit%s", NAME_DESC_BOB);
    //
    //        assertParseFailure(notorParser, noIndex, MESSAGE_UNKNOWN_COMMAND);
    //
    //        // no field specified
    //        String noField = "person 1 /edit";
    //        assertParseFailure(notorParser, noField, PersonEditCommand.MESSAGE_NOT_EDITED);
    //
    //        // no index and no field specified
    //        String noIndexField = "person /edit";
    //        assertParseFailure(notorParser, noIndexField, MESSAGE_UNKNOWN_COMMAND);
    //    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        String invalidCommand = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        String negativeIndex = String.format("person -1 /edit%s", NAME_DESC_BOB);
        assertParseFailure(notorParser, negativeIndex, invalidCommand);

        // zero index
        String zeroIndex = String.format("person -1 /edit%s", NAME_DESC_BOB);
        assertParseFailure(notorParser, zeroIndex, invalidCommand);

        // invalid arguments being parsed as preamble
        String invalidIndex = String.format("person 1 some random string /edit%s", NAME_DESC_BOB);
        assertParseFailure(notorParser, invalidIndex, invalidCommand);

        // invalid prefix being parsed as preamble
        String invalidPrefix = String.format("person 1 n:apple /edit%s", NAME_DESC_BOB);
        assertParseFailure(notorParser, invalidPrefix, invalidCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid Name
        String invalidName = String.format("person 1 /edit%s", INVALID_NAME_DESC);
        assertParseFailure(notorParser, invalidName, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        String invalidPhone = String.format("person 1 /edit%s", INVALID_PHONE_DESC);
        assertParseFailure(notorParser, invalidPhone, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        String invalidEmail = String.format("person 1 /edit%s", INVALID_EMAIL_DESC);
        assertParseFailure(notorParser, invalidEmail, Email.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        String invalidPhoneValidTag = String.format("person 1 /edit%s%s", PHONE_DESC_BOB, VALID_EMAIL_AMY);
        assertParseFailure(notorParser, invalidPhoneValidTag, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone.
        String invalidPhoneValidPhone = String.format("person 1 /edit%s%s", PHONE_DESC_BOB, INVALID_PHONE_DESC);
        assertParseFailure(notorParser, invalidPhoneValidPhone, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        String multipleInvalid = String.format("person 1 /edit%s%s%s", INVALID_NAME_DESC, PHONE_DESC_BOB,
                INVALID_PHONE_DESC);
        assertParseFailure(notorParser, multipleInvalid, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        String allFields = String.format("person 2 /edit%s%s%s", PHONE_DESC_BOB,
                EMAIL_DESC_AMY, NAME_DESC_AMY);

        Index targetIndex = INDEX_SECOND_PERSON;

        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);

        assertParseSuccess(notorParser.parseCommand(allFields), expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() throws ParseException {
        String someFields = String.format("person 2 /edit%s%s", PHONE_DESC_BOB, NAME_DESC_AMY);
        Index targetIndex = INDEX_SECOND_PERSON;

        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);

        assertParseSuccess(notorParser.parseCommand(someFields), expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() throws ParseException {
        Index targetIndex = INDEX_THIRD_PERSON;

        // name
        String validName = String.format("person 3 /edit%s", NAME_DESC_AMY);
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withName(VALID_NAME_AMY).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(notorParser.parseCommand(validName), expectedCommand);

        // phone
        String validPhone = String.format("person 3 /edit%s", PHONE_DESC_AMY);
        descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(notorParser.parseCommand(validPhone), expectedCommand);

        // email
        String validEmail = String.format("person 3 /edit%s", EMAIL_DESC_AMY);
        descriptor = new PersonEditDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(notorParser.parseCommand(validEmail), expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() throws ParseException {
        Index targetIndex = INDEX_THIRD_PERSON;

        String validEmail = String.format("person 3 /edit%s%s%s%s",
                PHONE_DESC_BOB, PHONE_DESC_AMY, EMAIL_DESC_BOB, EMAIL_DESC_AMY);
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(notorParser.parseCommand(validEmail), expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() throws ParseException {
        Index targetIndex = INDEX_FIRST_PERSON;

        // no other valid values specified
        String invalidPhoneValidPhone = String.format("person 1 /edit%s%s", INVALID_PHONE_DESC, PHONE_DESC_BOB);
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(notorParser.parseCommand(invalidPhoneValidPhone), expectedCommand);

        // other valid values specified
        String otherValid = String.format("person 1 /edit%s%s%s", EMAIL_DESC_BOB, INVALID_PHONE_DESC, PHONE_DESC_BOB);
        descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(notorParser.parseCommand(otherValid), expectedCommand);
    }
}
