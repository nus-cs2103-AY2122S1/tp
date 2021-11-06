package safeforhall.logic.parser.edit;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.commands.CommandTestUtil.COLLECTION_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.FET_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static safeforhall.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static safeforhall.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static safeforhall.logic.commands.CommandTestUtil.ROOM_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VACCSTATUS_DESC_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_COLLECTIONDATE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FETDATE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VACCSTATUS_AMY;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseFailure;
import static safeforhall.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static safeforhall.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static safeforhall.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static safeforhall.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.edit.EditPersonCommand;
import safeforhall.logic.commands.edit.EditPersonCommand.EditPersonDescriptor;
import safeforhall.model.person.Email;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;
import safeforhall.testutil.EditPersonDescriptorBuilder;

public class EditPersonCommandParserTest {

    private static final String INVALID_PERSON_INDEX = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX + "\n" + EditPersonCommand.MESSAGE_USAGE);

    private EditPersonCommandParser parser = new EditPersonCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, INVALID_PERSON_INDEX);

        // no field specified
        assertParseFailure(parser, "1", EditPersonCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", INVALID_PERSON_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, INVALID_PERSON_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, INVALID_PERSON_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", INVALID_PERSON_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", INVALID_PERSON_INDEX);
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
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndexList, descriptor);

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
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndexList, descriptor);

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
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //room
        userInput = targetIndex.getOneBased() + ROOM_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withRoom(VALID_ROOM_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //faculty
        userInput = targetIndex.getOneBased() + FACULTY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withFaculty(VALID_FACULTY_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //vaccStatus
        userInput = targetIndex.getOneBased() + VACCSTATUS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withVaccStatus(VALID_VACCSTATUS_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //lastFetDate
        userInput = targetIndex.getOneBased() + FET_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withLastFetDate(VALID_FETDATE_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //lastCollectionDate
        userInput = targetIndex.getOneBased() + COLLECTION_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withLastCollectionDate(VALID_COLLECTIONDATE_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
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
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndexList, descriptor);

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
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
             .build();
        expectedCommand = new EditPersonCommand(targetIndexList, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
