package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.plannermd.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.plannermd.logic.commands.CommandTestUtil.RISK_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.logic.commands.CommandTestUtil.BIRTH_DATE_DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.BIRTH_DATE_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_BIRTH_DATE_DESC;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.patient.TypicalPatients.AMY;
import static seedu.plannermd.testutil.patient.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.addcommand.AddPatientCommand;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.tag.Tag;
import seedu.plannermd.testutil.patient.PatientBuilder;

public class AddCommandParserTest {
        private AddCommandParser parser = new AddCommandParser();

    @Test
        public void parse_allFieldsPresent_success() {
                Patient expectedPatient = new PatientBuilder(BOB).withTags(VALID_TAG_FRIEND)
                                .withRisk(Risk.getUnclassifiedRisk().toString()).build();

                // whitespace only preamble
                assertParseSuccess(
                                parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                                                + ADDRESS_DESC_BOB + BIRTH_DATE_DESC_BOB + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatient));

                // multiple names - last name accepted
                assertParseSuccess(parser,
                                NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                                + BIRTH_DATE_DESC_BOB + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatient));

                // multiple phones - last phone accepted
                assertParseSuccess(parser,
                                NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                                + BIRTH_DATE_DESC_BOB + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatient));

                // multiple emails - last email accepted
                assertParseSuccess(parser,
                                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                                + BIRTH_DATE_DESC_BOB + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatient));

                // multiple addresses - last address accepted
                assertParseSuccess(parser,
                                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB
                                                + BIRTH_DATE_DESC_BOB + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatient));
                // multiple birth dates - last birth date accepted
                assertParseSuccess(parser,
                                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BIRTH_DATE_DESC_AMY
                                                + BIRTH_DATE_DESC_BOB + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatient));

                // multiple tags - all accepted
                Patient expectedPatientMultipleTags = new PatientBuilder(BOB)
                                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                                .withRisk(Risk.getUnclassifiedRisk().toString()).build();
                assertParseSuccess(parser,
                                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + BIRTH_DATE_DESC_BOB
                                                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatientMultipleTags));
        }

        @Test
        public void parse_optionalFieldsMissing_success() {
                // zero tags
                Patient expectedPatient = new PatientBuilder(AMY).withTags().build();
                assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                                                + BIRTH_DATE_DESC_AMY + RISK_DESC_AMY,
                                new AddPatientCommand(expectedPatient));

                // no risk
                expectedPatient = new PatientBuilder(AMY).withRisk(Risk.getUnclassifiedRisk().toString()).build();
                assertParseSuccess(parser,
                                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + BIRTH_DATE_DESC_AMY
                                        + TAG_DESC_FRIEND,
                                new AddPatientCommand(expectedPatient));
        }

        @Test
        public void parse_compulsoryFieldMissing_failure() {
                String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE);

                // missing name prefix
                assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                + BIRTH_DATE_DESC_BOB, expectedMessage);

                // missing phone prefix
                assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                + BIRTH_DATE_DESC_BOB, expectedMessage);

                // missing email prefix
                assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                                + BIRTH_DATE_DESC_BOB, expectedMessage);

                // missing address prefix
                assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                                + BIRTH_DATE_DESC_BOB, expectedMessage);

                // missing birth date prefix
                assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                + VALID_BIRTH_DATE_BOB, expectedMessage);

                // all prefixes missing
                assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                                + VALID_BIRTH_DATE_BOB, expectedMessage);
        }

        @Test
        public void parse_invalidValue_failure() {
                // invalid name
                assertParseFailure(parser,
                                INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                        + BIRTH_DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                Name.MESSAGE_CONSTRAINTS);

                // invalid phone
                assertParseFailure(parser,
                                NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                        + BIRTH_DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                Phone.MESSAGE_CONSTRAINTS);

                // invalid email
                assertParseFailure(parser,
                                NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                                        + BIRTH_DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                Email.MESSAGE_CONSTRAINTS);

                // invalid address
                assertParseFailure(parser,
                                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                                        + BIRTH_DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                Address.MESSAGE_CONSTRAINTS);

                // invalid birth date
                assertParseFailure(parser,
                                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                        + INVALID_BIRTH_DATE_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                BirthDate.MESSAGE_CONSTRAINTS);

                // invalid tag
                assertParseFailure(
                                parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                + BIRTH_DATE_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                                Tag.MESSAGE_CONSTRAINTS);

                // two invalid values, only first invalid value reported
                assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                                + BIRTH_DATE_DESC_BOB
                        , Name.MESSAGE_CONSTRAINTS);

                // non-empty preamble
                assertParseFailure(parser,
                                PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                                        + BIRTH_DATE_DESC_BOB +  TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
        }
}
