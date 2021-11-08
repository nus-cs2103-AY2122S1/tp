package seedu.plannermd.logic.parser.apptcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_ANOTHER_DOCTOR_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_ANOTHER_PATIENT_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_DOCTOR_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_DURATION_THIRTY_MIN_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_DURATION_TWO_HOUR_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_PATIENT_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_REMARK_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_START_THIRTY_MIN_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_START_TWO_HOUR_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_DOCTOR_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_PATIENT_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DEFAULT_DURATION;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_REMARK;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_DOCTOR_INDEX;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PATIENT_INDEX;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.apptcommand.AddAppointmentCommand;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.testutil.appointment.AddAppointmentDescriptorBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
            + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        Index patientIndex = INDEX_FIRST_PERSON;
        Index doctorIndex = INDEX_SECOND_PERSON;

        AddAppointmentCommand.AddAppointmentDescriptor addAppointmentDescriptor =
                new AddAppointmentDescriptorBuilder()
                        .withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                        .withSession(VALID_APPT_TIME_THIRTY_MIN, VALID_APPT_DURATION_THIRTY_MIN)
                        .withRemark(VALID_APPT_REMARK).build();

        assertParseSuccess(parser, userInput, new AddAppointmentCommand(patientIndex,
                doctorIndex, addAppointmentDescriptor));

        // multiple patient indexes - last index accepted
        userInput = APPT_ANOTHER_PATIENT_INDEX_DESC + APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC
                + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseSuccess(parser,
                userInput,
                new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor));

        // multiple doctor indexes - last index accepted
        userInput = APPT_PATIENT_INDEX_DESC + APPT_ANOTHER_DOCTOR_INDEX_DESC + APPT_DOCTOR_INDEX_DESC
                + APPT_START_THIRTY_MIN_DESC + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseSuccess(parser,
                userInput,
                new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor));

        // multiple start date time - last date time accepted
        userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_TWO_HOUR_DESC
                + APPT_START_THIRTY_MIN_DESC + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseSuccess(parser,
                userInput,
                new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor));

        // multiple durations - last duration accepted
        userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_TWO_HOUR_DESC + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseSuccess(parser,
                userInput,
                new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor));
        // multiple remarks - last remark accepted
        userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_TWO_HOUR_DESC + APPT_DURATION_THIRTY_MIN_DESC + REMARK_DESC_AMY + APPT_REMARK_DESC;
        assertParseSuccess(parser,
                userInput,
                new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Index patientIndex = INDEX_FIRST_PERSON;
        Index doctorIndex = INDEX_SECOND_PERSON;

        // No Duration
        String userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_REMARK_DESC;
        AddAppointmentCommand.AddAppointmentDescriptor addAppointmentDescriptor =
                new AddAppointmentDescriptorBuilder()
                        .withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                        .withSession(VALID_APPT_TIME_THIRTY_MIN, VALID_APPT_DEFAULT_DURATION)
                        .withRemark(VALID_APPT_REMARK).build();
        assertParseSuccess(parser,
                userInput,
                new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor));
        // No remark
        userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_THIRTY_MIN_DESC;

        addAppointmentDescriptor =
                new AddAppointmentDescriptorBuilder()
                        .withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                        .withSession(VALID_APPT_TIME_THIRTY_MIN, VALID_APPT_DURATION_THIRTY_MIN)
                        .withRemark("").build();
        assertParseSuccess(parser,
                userInput,
                new AddAppointmentCommand(patientIndex, doctorIndex, addAppointmentDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing patient prefix
        assertParseFailure(parser, VALID_PATIENT_INDEX + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_THIRTY_MIN_DESC, expectedMessage);

        // missing doctor prefix
        assertParseFailure(parser, APPT_PATIENT_INDEX_DESC + VALID_DOCTOR_INDEX + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_THIRTY_MIN_DESC, expectedMessage);

        // missing start prefix
        assertParseFailure(parser, APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC
                + VALID_APPT_DATE_THIRTY_MIN + APPT_DURATION_THIRTY_MIN_DESC, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_PATIENT_INDEX + VALID_DOCTOR_INDEX
                + VALID_APPT_DATE_THIRTY_MIN, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        AddAppointmentCommand.AddAppointmentDescriptor addAppointmentDescriptor =
                new AddAppointmentDescriptorBuilder()
                        .withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                        .withSession(VALID_APPT_TIME_THIRTY_MIN, VALID_APPT_DEFAULT_DURATION)
                        .withRemark(VALID_APPT_REMARK).build();

        // invalid patient index
        String userInput = INVALID_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseFailure(parser,
                userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid doctor index
        userInput = APPT_PATIENT_INDEX_DESC + INVALID_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseFailure(parser,
                userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid start date
        userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + INVALID_START_DATE_DESC
                + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseFailure(parser,
                userInput,
                AddAppointmentCommand.MESSAGE_WRONG_DATE_TIME);

        // invalid start time
        userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + INVALID_START_TIME_DESC
                + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;
        assertParseFailure(parser,
                userInput,
                AddAppointmentCommand.MESSAGE_WRONG_DATE_TIME);

        // invalid duration
        userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + INVALID_START_TIME_DESC
                + INVALID_DURATION_DESC + APPT_REMARK_DESC;
        assertParseFailure(parser,
                userInput,
                AddAppointmentCommand.MESSAGE_WRONG_DATE_TIME);
    }

    @Test
    public void parse_sessionSpansTwoDays_failure() {
        String userInput = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_DURATION_TWO_HOUR_DESC + APPT_REMARK_DESC; // session 22:35-00:35
        assertParseFailure(parser, userInput, Session.MESSAGE_END_WITHIN_SAME_DAY);

        String userInput2 = APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + " " + PREFIX_DURATION + "85" + APPT_REMARK_DESC; // session 22:35-00:00
        assertParseFailure(parser, userInput2, Session.MESSAGE_END_WITHIN_SAME_DAY);
    }
}
