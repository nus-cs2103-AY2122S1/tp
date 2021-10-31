package seedu.plannermd.logic.parser.apptcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.logic.commands.CommandTestUtil.ANOTHER_VALID_PATIENT_INDEX;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_ANOTHER_DOCTOR_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_ANOTHER_PATIENT_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_ANOTHER_REMARK_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_DOCTOR_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_DURATION_THIRTY_MIN_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_DURATION_TWO_HOUR_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_PATIENT_INDEX_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_REMARK_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_START_THIRTY_MIN_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.APPT_START_TWO_HOUR_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_DURATION_THREE_HOURS_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.INVALID_START_DESC;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_THIRTY_MIN_STR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_TWO_HOUR_STR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_REMARK;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_DOCTOR_INDEX;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PATIENT_INDEX;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.plannermd.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_APPT;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.apptcommand.EditAppointmentCommand;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.testutil.appointment.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);

    private final EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, APPT_REMARK_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + APPT_REMARK_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + APPT_REMARK_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start
        assertParseFailure(parser, "1" + INVALID_START_DESC, EditAppointmentCommand.MESSAGE_INVALID_START);
        // invalid duration
        assertParseFailure(parser, "1" + INVALID_DURATION_THREE_HOURS_DESC, Duration.MESSAGE_CONSTRAINTS);

        // invalid start followed by valid duration
        assertParseFailure(parser, "1" + INVALID_START_DESC + APPT_DURATION_THIRTY_MIN_DESC,
                EditAppointmentCommand.MESSAGE_INVALID_START);

        // valid duration followed by invalid duration. The test case for invalid duration followed by valid duration
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + APPT_DURATION_THIRTY_MIN_DESC + INVALID_DURATION_THREE_HOURS_DESC,
                Duration.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_START_DESC + INVALID_DURATION_THREE_HOURS_DESC,
                EditAppointmentCommand.MESSAGE_INVALID_START);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPT;
        String userInput = targetIndex.getOneBased() + APPT_PATIENT_INDEX_DESC + APPT_DOCTOR_INDEX_DESC
                + APPT_START_THIRTY_MIN_DESC + APPT_DURATION_THIRTY_MIN_DESC + APPT_REMARK_DESC;

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientIndex(VALID_PATIENT_INDEX).withDoctorIndex(VALID_DOCTOR_INDEX)
                .withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN).withStartTime(VALID_APPT_TIME_THIRTY_MIN)
                .withDuration(VALID_APPT_DURATION_THIRTY_MIN_STR).withRemark(VALID_APPT_REMARK).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPT;
        String userInput = targetIndex.getOneBased() + APPT_DOCTOR_INDEX_DESC + APPT_REMARK_DESC;

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDoctorIndex(VALID_DOCTOR_INDEX).withRemark(VALID_APPT_REMARK).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // patient index
        Index targetIndex = INDEX_FIRST_APPT;
        String userInput = targetIndex.getOneBased() + APPT_PATIENT_INDEX_DESC;
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withPatientIndex(VALID_PATIENT_INDEX).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // doctor index
        userInput = targetIndex.getOneBased() + APPT_DOCTOR_INDEX_DESC;
        descriptor = new EditAppointmentDescriptorBuilder().withDoctorIndex(VALID_DOCTOR_INDEX).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start
        userInput = targetIndex.getOneBased() + APPT_START_THIRTY_MIN_DESC;
        descriptor = new EditAppointmentDescriptorBuilder().withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                .withStartTime(VALID_APPT_TIME_THIRTY_MIN).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + APPT_DURATION_THIRTY_MIN_DESC;
        descriptor = new EditAppointmentDescriptorBuilder().withDuration(VALID_APPT_DURATION_THIRTY_MIN_STR).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark
        userInput = targetIndex.getOneBased() + APPT_REMARK_DESC;
        descriptor = new EditAppointmentDescriptorBuilder().withRemark(VALID_APPT_REMARK).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPT;
        String userInput = targetIndex.getOneBased() + APPT_PATIENT_INDEX_DESC + APPT_ANOTHER_PATIENT_INDEX_DESC
                + APPT_ANOTHER_DOCTOR_INDEX_DESC + APPT_DOCTOR_INDEX_DESC + APPT_START_THIRTY_MIN_DESC
                + APPT_START_TWO_HOUR_DESC + APPT_DURATION_THIRTY_MIN_DESC + APPT_DURATION_TWO_HOUR_DESC
                + APPT_ANOTHER_REMARK_DESC + APPT_REMARK_DESC;

        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withPatientIndex(ANOTHER_VALID_PATIENT_INDEX)
                        .withDoctorIndex(VALID_DOCTOR_INDEX).withAppointmentDate(VALID_APPT_DATE_TWO_HOUR)
                        .withStartTime(VALID_APPT_TIME_TWO_HOUR).withDuration(VALID_APPT_DURATION_TWO_HOUR_STR)
                        .withRemark(VALID_APPT_REMARK).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPT;
        String userInput = targetIndex.getOneBased() + INVALID_START_DESC + APPT_START_THIRTY_MIN_DESC;
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                        .withStartTime(VALID_APPT_TIME_THIRTY_MIN).build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_START_DESC + APPT_REMARK_DESC + APPT_START_THIRTY_MIN_DESC;
        descriptor = new EditAppointmentDescriptorBuilder().withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                .withStartTime(VALID_APPT_TIME_THIRTY_MIN).withRemark(VALID_APPT_REMARK).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
