package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NUS_NETWORK_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NUS_NETWORK_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NUS_NETWORK_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NUS_NETWORK_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.GitHubId;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetworkId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TutorialId;
import seedu.address.model.person.Type;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPersonMultipleTags));

        // multiple gitHubIds - last gitHubId accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND + GITHUB_ID_DESC_AMY + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple nusNetworkIds - last nusNetworkId accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_AMY + NUS_NETWORK_ID_DESC_BOB
                + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple types - last type accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_AMY + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple studentIds - last studentId accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_AMY
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));

        // multiple tutorialIds - last tutorialId accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB
                + TUTORIAL_ID_DESC_AMY + TUTORIAL_ID_DESC_BOB, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                        + GITHUB_ID_DESC_AMY + NUS_NETWORK_ID_DESC_AMY + TYPE_DESC_AMY + STUDENT_ID_DESC_AMY
                        + TUTORIAL_ID_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB
                        + TUTORIAL_ID_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + ADDRESS_DESC_BOB
                        + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB
                        + TUTORIAL_ID_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_ADDRESS_BOB
                        + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB
                        + TUTORIAL_ID_DESC_BOB,
                expectedMessage);

        // missing gitHubId prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_GITHUB_ID_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB
                        + TUTORIAL_ID_DESC_BOB,
                expectedMessage);

        // missing nusNetworkId prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + GITHUB_ID_DESC_BOB + VALID_NUS_NETWORK_ID_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB
                        + TUTORIAL_ID_DESC_BOB,
                expectedMessage);

        // missing type prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + VALID_TYPE_BOB + STUDENT_ID_DESC_BOB
                        + TUTORIAL_ID_DESC_BOB,
                expectedMessage);

        // missing studentId prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + VALID_STUDENT_ID_BOB
                        + TUTORIAL_ID_DESC_BOB,
                expectedMessage);

        // missing tutorialId prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB
                        + VALID_TUTORIAL_ID_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                        + VALID_GITHUB_ID_BOB + VALID_NUS_NETWORK_ID_BOB + VALID_TYPE_BOB + VALID_STUDENT_ID_BOB
                        + VALID_TUTORIAL_ID_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid gitHubId
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + INVALID_GITHUB_ID_DESC + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, GitHubId.MESSAGE_CONSTRAINTS);

        // invalid nusNetworkId
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + INVALID_NUS_NETWORK_ID_DESC + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, NusNetworkId.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + INVALID_TYPE_DESC
                + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB, Type.MESSAGE_CONSTRAINTS);

        // invalid studentId
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + INVALID_STUDENT_ID_DESC + TUTORIAL_ID_DESC_BOB, StudentId.MESSAGE_CONSTRAINTS);

        // invalid tutorialId
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + INVALID_TUTORIAL_ID_DESC, TutorialId.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB + TYPE_DESC_BOB
                + STUDENT_ID_DESC_BOB + INVALID_TUTORIAL_ID_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + GITHUB_ID_DESC_BOB + NUS_NETWORK_ID_DESC_BOB
                + TYPE_DESC_BOB + STUDENT_ID_DESC_BOB + TUTORIAL_ID_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
