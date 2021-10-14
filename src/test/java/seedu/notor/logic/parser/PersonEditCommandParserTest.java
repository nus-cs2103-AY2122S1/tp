package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.notor.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.notor.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.notor.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.notor.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.notor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.notor.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.executors.person.PersonEditExecutor.PersonEditDescriptor;
import seedu.notor.logic.parser.person.PersonEditCommandParser;
import seedu.notor.model.person.Email;
import seedu.notor.model.person.Name;
import seedu.notor.model.person.Phone;
import seedu.notor.model.tag.Tag;
import seedu.notor.testutil.PersonEditDescriptorBuilder;

public class PersonEditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonEditCommand.MESSAGE_USAGE);

    private final PersonEditCommandParser parser = new PersonEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", PersonEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_PERSON;

        // name
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withName(VALID_NAME_AMY).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new PersonEditDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new PersonEditDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND;

        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_PERSON;

        // no other valid values specified
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new PersonEditDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new PersonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        PersonEditDescriptor descriptor = new PersonEditDescriptorBuilder().withTags().build();
        PersonEditCommand expectedCommand = new PersonEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
