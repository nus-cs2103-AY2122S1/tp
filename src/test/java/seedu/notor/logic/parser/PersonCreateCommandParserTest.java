package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_NAME_JAMES;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.notor.logic.commands.CommandTestUtil.TAG_MULTIPLE_TAGS;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.notor.testutil.TypicalPersons.BOB;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Note;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Person;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;
import seedu.notor.testutil.PersonBuilder;

public class PersonCreateCommandParserTest {
    private final NotorParser notorParser = new NotorParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        // Default Bob has note but no tags; create can create tags but not note.
        Person expectedPerson = new PersonBuilder(BOB).withNote(Note.EMPTY_NOTE).withTags(VALID_TAG_FRIEND).build();

        // multiple phones - last phone accepted
        String multiplePhones = String.format("person %s /create%s%s%s%s", VALID_NAME_BOB,
                PHONE_DESC_AMY, PHONE_DESC_BOB, EMAIL_DESC_BOB, TAG_DESC_FRIEND);
        assertParseSuccess(notorParser.parseCommand(multiplePhones), new PersonCreateCommand(null, expectedPerson));

        // multiple emails - last email accepted
        String multipleEmails = String.format("person %s /create%s%s%s%s", VALID_NAME_BOB,
                PHONE_DESC_BOB, EMAIL_DESC_AMY, EMAIL_DESC_BOB, TAG_DESC_FRIEND);
        assertParseSuccess(notorParser.parseCommand(multipleEmails), new PersonCreateCommand(null, expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withNote(Note.EMPTY_NOTE).withTags(VALID_TAG_FRIEND,
                        VALID_TAG_HUSBAND)
                .build();
        String multipleTags = String.format("person %s /create%s%s%s", VALID_NAME_BOB,
                PHONE_DESC_BOB, EMAIL_DESC_BOB, TAG_MULTIPLE_TAGS);
        assertParseSuccess(notorParser.parseCommand(multipleTags),
                new PersonCreateCommand(null, expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() throws ParseException {
        // zero tags
        String noTag = PersonCommand.COMMAND_WORD + " " + VALID_NAME_BOB + " /" + PersonCreateCommand.COMMAND_WORD
                + PHONE_DESC_BOB + EMAIL_DESC_BOB;
        Person expectedPerson = new Person(BOB.getName(), BOB.getPhone(), BOB.getEmail(), Note.EMPTY_NOTE,
                new HashSet<>());
        assertParseSuccess(notorParser.parseCommand(noTag), new PersonCreateCommand(null, expectedPerson));
    }

    //    @Test
    //    public void parse_compulsoryFieldMissing_failure() {
    //        // missing name prefix
    //        String noName = String.format("person /create %s%s%s", VALID_NAME_BOB,
    //                PHONE_DESC_BOB, EMAIL_DESC_BOB);
    //        assertParseFailure(notorParser, noName, MESSAGE_UNKNOWN_COMMAND);
    //    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        String invalidCommand = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);

        String invalidName = String.format("person %s /create %s%s%s%s", INVALID_NAME_JAMES,
                PHONE_DESC_AMY, PHONE_DESC_BOB, EMAIL_DESC_BOB, TAG_DESC_FRIEND);
        assertParseFailure(notorParser, invalidName, invalidCommand);

        // invalid phone
        String invalidPhone = String.format("person %s /create %s%s%s%s", VALID_NAME_BOB,
                PHONE_DESC_AMY, INVALID_PHONE_DESC, EMAIL_DESC_BOB, TAG_DESC_FRIEND);
        assertParseFailure(notorParser, invalidPhone, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        String invalidEmail = String.format("person %s /create %s%s%s%s", VALID_NAME_BOB,
                PHONE_DESC_AMY, PHONE_DESC_BOB, INVALID_EMAIL_DESC, TAG_DESC_FRIEND);
        assertParseFailure(notorParser, invalidEmail, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        String invalidTag = String.format("person %s /create %s%s%s%s", VALID_NAME_BOB,
                PHONE_DESC_AMY, PHONE_DESC_BOB, EMAIL_DESC_BOB, INVALID_TAG_DESC);
        assertParseFailure(notorParser, invalidTag, Tag.MESSAGE_CONSTRAINTS);
    }
}
