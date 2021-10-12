package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static safeforhall.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
//import static safeforhall.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VACCSTATUS_DESC_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
//import static safeforhall.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VACCSTATUS_AMY;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
//import static safeforhall.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
//import static safeforhall.logic.parser.CliSyntax.PREFIX_TAG;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static safeforhall.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static safeforhall.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static safeforhall.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.EditCommand;
import safeforhall.logic.commands.EditCommand.EditPersonDescriptor;
//import safeforhall.model.person.Address;
import safeforhall.model.person.Email;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;
//import safeforhall.model.tag.Tag;
import safeforhall.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    //private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String INVALID_INDEX = "Index is not a non-zero unsigned integer."
            + "\n" + EditCommand.MESSAGE_USAGE;
    private static final String NO_INDEX = "Missing residents' index(es)." + "\n" + EditCommand.MESSAGE_USAGE;
    private static final String MESSAGE_INVALID_INDEX =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, INVALID_INDEX);
    private static final String MESSAGE_NO_INDEX =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, NO_INDEX);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_NO_INDEX);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_NO_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_NO_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_NO_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        //assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        //assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        //assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        ArrayList<Index> targetIndexList = new ArrayList<>();
        targetIndexList.add(targetIndex);

        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY + NAME_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
             .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndexList, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        ArrayList<Index> targetIndexList = new ArrayList<>();
        targetIndexList.add(targetIndex);

        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndexList, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        ArrayList<Index> targetIndexList = new ArrayList<>();
        targetIndexList.add(targetIndex);

        // name
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //room
        userInput = targetIndex.getOneBased() + ROOM_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withRoom(VALID_ROOM_AMY).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //faculty
        userInput = targetIndex.getOneBased() + FACULTY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withFaculty(VALID_FACULTY_AMY).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //vaccStatus
        userInput = targetIndex.getOneBased() + VACCSTATUS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withVaccStatus(VALID_VACCSTATUS_AMY).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        ArrayList<Index> targetIndexList = new ArrayList<>();
        targetIndexList.add(targetIndex);

        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY
             + PHONE_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
             .withEmail(VALID_EMAIL_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndexList, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        ArrayList<Index> targetIndexList = new ArrayList<>();
        targetIndexList.add(targetIndex);

        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
             .build();
        expectedCommand = new EditCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    //TODO: Include more tests with Room, Faculty, VaccStatus, LastFetDate and LastCollectionDate

    //@Test
    //public void parse_resetTags_success() {
    //    Index targetIndex = INDEX_THIRD_PERSON;
    //    ArrayList<Index> targetIndexList = new ArrayList<>();
    //    targetIndexList.add(targetIndex);
    //    //String userInput = targetIndex.getOneBased() + TAG_EMPTY;
    //
    //    EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
    //    EditCommand expectedCommand = new EditCommand(targetIndexList, descriptor);
    //
    //    //assertParseSuccess(parser, userInput, expectedCommand);
    //}
}
