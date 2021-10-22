package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HEALTH_CONDITION_DESC_DEMENTIA;
import static seedu.address.logic.commands.CommandTestUtil.HEALTH_CONDITION_DESC_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEALTH_CONDITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LANGUAGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LAST_VISIT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LANGUAGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LANGUAGE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_CONDITION_DEMENTIA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_CONDITION_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEALTH_CONDITION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.Address;
import seedu.address.model.person.Language;
import seedu.address.model.person.LastVisit;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String HEALTH_CONDITION_EMPTY = " " + PREFIX_HEALTH_CONDITION;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_LANGUAGE_DESC,
                Language.MESSAGE_CONSTRAINTS); // invalid LANGUAGE
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        // invalid health condition
        assertParseFailure(parser, "1" + INVALID_HEALTH_CONDITION_DESC, HealthCondition.MESSAGE_CONSTRAINTS);

        //invalid last visit
        assertParseFailure(parser, "1" + INVALID_LAST_VISIT_DESC, LastVisit.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid language
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + LANGUAGE_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_HEALTH_CONDITION} alone will reset the health conditions of the {@code Person}
        // being edited, parsing it together with a valid health condition results in error
        assertParseFailure(parser, "1" + HEALTH_CONDITION_DESC_DIABETES + HEALTH_CONDITION_DESC_DEMENTIA
                        + HEALTH_CONDITION_EMPTY,
                HealthCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + HEALTH_CONDITION_DESC_DIABETES + HEALTH_CONDITION_EMPTY
                        + HEALTH_CONDITION_DESC_DEMENTIA,
                HealthCondition.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + HEALTH_CONDITION_EMPTY + HEALTH_CONDITION_DESC_DIABETES
                        + HEALTH_CONDITION_DESC_DEMENTIA,
                HealthCondition.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_LANGUAGE_DESC
                        + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + HEALTH_CONDITION_DESC_DEMENTIA
                + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + HEALTH_CONDITION_DESC_DIABETES;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withLanguage(VALID_LANGUAGE_AMY).withAddress(VALID_ADDRESS_AMY)
                .withHealthConditions(VALID_HEALTH_CONDITION_DEMENTIA, VALID_HEALTH_CONDITION_DIABETES).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + LANGUAGE_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withLanguage(VALID_LANGUAGE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // language
        userInput = targetIndex.getOneBased() + LANGUAGE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withLanguage(VALID_LANGUAGE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // health conditions
        userInput = targetIndex.getOneBased() + HEALTH_CONDITION_DESC_DIABETES;
        descriptor = new EditPersonDescriptorBuilder().withHealthConditions(VALID_HEALTH_CONDITION_DIABETES).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + LANGUAGE_DESC_AMY
                + HEALTH_CONDITION_DESC_DIABETES + PHONE_DESC_AMY + ADDRESS_DESC_AMY + LANGUAGE_DESC_AMY
                + HEALTH_CONDITION_DESC_DIABETES + PHONE_DESC_BOB + ADDRESS_DESC_BOB + LANGUAGE_DESC_BOB
                + HEALTH_CONDITION_DESC_DEMENTIA;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withLanguage(VALID_LANGUAGE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withHealthConditions(VALID_HEALTH_CONDITION_DIABETES, VALID_HEALTH_CONDITION_DEMENTIA).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + LANGUAGE_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withLanguage(VALID_LANGUAGE_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetHealthConditions_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + HEALTH_CONDITION_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withHealthConditions().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
