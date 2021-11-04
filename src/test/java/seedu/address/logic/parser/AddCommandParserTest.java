package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FREQUENCY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FREQUENCY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HEALTH_CONDITION_DESC_DEMENTIA;
import static seedu.address.logic.commands.CommandTestUtil.HEALTH_CONDITION_DESC_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEALTH_CONDITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LANGUAGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LANGUAGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LANGUAGE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.LAST_VISIT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LAST_VISIT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.OCCURRENCE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.OCCURRENCE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FREQUENCY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_CONDITION_DEMENTIA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEALTH_CONDITION_DIABETES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_VISIT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISIT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VISIT_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.healthcondition.HealthCondition;
import seedu.address.model.person.Address;
import seedu.address.model.person.Language;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withHealthConditions(VALID_HEALTH_CONDITION_DIABETES).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB
                + ADDRESS_DESC_BOB + LAST_VISIT_DESC_BOB + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB
                + ADDRESS_DESC_BOB + LAST_VISIT_DESC_BOB + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + LANGUAGE_DESC_BOB
                + ADDRESS_DESC_BOB + LAST_VISIT_DESC_BOB + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPerson));

        // multiple languages - last language accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_AMY + LANGUAGE_DESC_BOB
                + ADDRESS_DESC_BOB + LAST_VISIT_DESC_BOB + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + LAST_VISIT_DESC_BOB + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPerson));

        // multiple frequencies - last frequency accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB
                        + LAST_VISIT_DESC_BOB + FREQUENCY_DESC_AMY + FREQUENCY_DESC_BOB + OCCURRENCE_DESC_BOB
                        + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPerson));

        // multiple occurrences - last occurrence accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB
                        + LAST_VISIT_DESC_BOB + FREQUENCY_DESC_BOB + OCCURRENCE_DESC_AMY + OCCURRENCE_DESC_BOB
                        + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPerson));


        // multiple healthConditions - all accepted
        Person expectedPersonMultipleHealthConditions = new PersonBuilder(BOB)
                .withHealthConditions(VALID_HEALTH_CONDITION_DIABETES, VALID_HEALTH_CONDITION_DEMENTIA).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB
                + LAST_VISIT_DESC_BOB + HEALTH_CONDITION_DESC_DEMENTIA + HEALTH_CONDITION_DESC_DIABETES,
                new AddCommand(expectedPersonMultipleHealthConditions));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero health condition, no visit, no last visit
        Person expectedPerson = new PersonBuilder(AMY).withVisit("").withLastVisit("").withHealthConditions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalVisitPresent_success() {
        Person expectedPerson = new PersonBuilder(AMY).withVisit(VALID_VISIT_AMY).withLastVisit("")
                .withHealthConditions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY
                        + VISIT_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalLastVisitPresent_success() {
        Person expectedPerson = new PersonBuilder(AMY).withVisit("").withLastVisit(VALID_LAST_VISIT_AMY)
                .withHealthConditions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY
                        + LAST_VISIT_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_visitAndLastVisitPresent_success() {
        Person expectedPerson = new PersonBuilder(AMY).withVisit(VALID_VISIT_AMY).withLastVisit(VALID_LAST_VISIT_AMY)
                .withHealthConditions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY
                + VISIT_DESC_AMY + LAST_VISIT_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_visitAndFrequencyAndOccurrence_success() {
        Person expectedPerson = new PersonBuilder(AMY).withVisit(VALID_VISIT_AMY).withLastVisit("")
                .withFrequency(VALID_FREQUENCY_AMY).withOccurrence(2).withHealthConditions().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY
                + VISIT_DESC_AMY + FREQUENCY_DESC_AMY + OCCURRENCE_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_occurrenceFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY
                        + FREQUENCY_DESC_AMY,
                expectedMessage);
    }

    @Test
    public void parse_frequencyFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + LANGUAGE_DESC_AMY + ADDRESS_DESC_AMY
                        + OCCURRENCE_DESC_AMY,
                expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing language prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_LANGUAGE_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_LANGUAGE_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB
                + HEALTH_CONDITION_DESC_DEMENTIA + HEALTH_CONDITION_DESC_DIABETES, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB
                + HEALTH_CONDITION_DESC_DEMENTIA + HEALTH_CONDITION_DESC_DIABETES, Phone.MESSAGE_CONSTRAINTS);

        // invalid language
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_LANGUAGE_DESC + ADDRESS_DESC_BOB
                + HEALTH_CONDITION_DESC_DEMENTIA + HEALTH_CONDITION_DESC_DIABETES, Language.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + INVALID_ADDRESS_DESC
                + HEALTH_CONDITION_DESC_DEMENTIA + HEALTH_CONDITION_DESC_DIABETES, Address.MESSAGE_CONSTRAINTS);

        // invalid health condition
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_HEALTH_CONDITION_DESC + VALID_HEALTH_CONDITION_DIABETES, HealthCondition.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + LANGUAGE_DESC_BOB
                + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + LANGUAGE_DESC_BOB
                + ADDRESS_DESC_BOB + HEALTH_CONDITION_DESC_DEMENTIA + HEALTH_CONDITION_DESC_DIABETES,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
