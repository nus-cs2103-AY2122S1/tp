package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.plannermd.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.apptcommand.AddAppointmentCommand;
import seedu.plannermd.logic.commands.apptcommand.EditAppointmentCommand;
import seedu.plannermd.logic.commands.editcommand.EditDoctorCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentFieldsContainKeywordsPredicate;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.testutil.appointment.AddAppointmentDescriptorBuilder;
import seedu.plannermd.testutil.appointment.EditAppointmentDescriptorBuilder;
import seedu.plannermd.testutil.doctor.EditDoctorDescriptorBuilder;
import seedu.plannermd.testutil.patient.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BIRTH_DATE_AMY = "02/01/1965";
    public static final String VALID_BIRTH_DATE_BOB = "2/2/1955";
    public static final String VALID_REMARK_AMY = "Orange CHAS card";
    public static final String VALID_REMARK_BOB = "Company X coverage";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_PATIENT = "Immunocompromised";
    public static final String VALID_TAG_DOCTOR = "Pediatrician";
    public static final String VALID_RISK_AMY = "LOW";
    public static final String VALID_RISK_BOB = "MEDIUM";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BIRTH_DATE_DESC_AMY = " " + PREFIX_BIRTH_DATE + VALID_BIRTH_DATE_AMY;
    public static final String BIRTH_DATE_DESC_BOB = " " + PREFIX_BIRTH_DATE + VALID_BIRTH_DATE_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String RISK_DESC_AMY = " " + PREFIX_RISK + VALID_RISK_AMY;
    public static final String RISK_DESC_BOB = " " + PREFIX_RISK + VALID_RISK_BOB;

    public static final LocalDate FILTER_VALID_START_DATE = LocalDate.of(2020, 10, 20);
    public static final LocalDate FILTER_VALID_END_DATE = LocalDate.of(2020, 11, 20);
    public static final String VALID_STRING_START_DATE = "20/10/2020";
    public static final String VALID_STRING_END_DATE = "20/11/2020";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_BIRTH_DATE_DESC = " "
            + PREFIX_BIRTH_DATE + "2020/02/11"; // not DD/MM/YYYY format
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPatientCommand.EditPatientDescriptor DESC_AMY;
    public static final EditPatientCommand.EditPatientDescriptor DESC_BOB;

    public static final EditDoctorCommand.EditDoctorDescriptor DESC_DR_AMY;
    public static final EditDoctorCommand.EditDoctorDescriptor DESC_DR_BOB;

    public static final String VALID_APPT_DATE_THIRTY_MIN = "2/2/2022";
    public static final String VALID_APPT_DATE_TWO_HOUR = "12/12/2022";
    public static final String VALID_APPT_REMARK = "Patient wants a blood test";
    public static final String ANOTHER_VALID_APPT_REMARK = "Patient wants a Covid test";
    public static final int VALID_APPT_DURATION = 30;
    public static final String VALID_APPT_TIME = "23:59";
    public static final String VALID_APPT_TIME_THIRTY_MIN = "22:35";
    public static final String VALID_APPT_TIME_TWO_HOUR = "12:35";
    public static final int VALID_APPT_DEFAULT_DURATION = 10;
    public static final int VALID_APPT_DURATION_THIRTY_MIN = 30;
    public static final int VALID_APPT_DURATION_TWO_HOUR = 120;
    public static final String VALID_APPT_DURATION_THIRTY_MIN_STR = "30";
    public static final String VALID_APPT_DURATION_TWO_HOUR_STR = "120";
    public static final String VALID_PATIENT_INDEX = "1";
    public static final String ANOTHER_VALID_PATIENT_INDEX = "2";
    public static final String VALID_DOCTOR_INDEX = "2";
    public static final String ANOTHER_VALID_DOCTOR_INDEX = "3";

    public static final String APPT_START_THIRTY_MIN_DESC = " " + PREFIX_START + VALID_APPT_DATE_THIRTY_MIN
            + " " + VALID_APPT_TIME_THIRTY_MIN;
    public static final String APPT_DURATION_THIRTY_MIN_DESC = " " + PREFIX_DURATION
            + VALID_APPT_DURATION_THIRTY_MIN;
    public static final String APPT_START_TWO_HOUR_DESC = " " + PREFIX_START + VALID_APPT_DATE_TWO_HOUR
            + " " + VALID_APPT_TIME_TWO_HOUR;
    public static final String APPT_DURATION_TWO_HOUR_DESC = " " + PREFIX_DURATION
            + VALID_APPT_DURATION_TWO_HOUR;
    public static final String APPT_PATIENT_INDEX_DESC = " " + PREFIX_PATIENT + VALID_PATIENT_INDEX;
    public static final String APPT_ANOTHER_PATIENT_INDEX_DESC = " " + PREFIX_PATIENT + ANOTHER_VALID_PATIENT_INDEX;
    public static final String APPT_DOCTOR_INDEX_DESC = " " + PREFIX_DOCTOR + VALID_DOCTOR_INDEX;
    public static final String APPT_ANOTHER_DOCTOR_INDEX_DESC = " " + PREFIX_DOCTOR + ANOTHER_VALID_DOCTOR_INDEX;
    public static final String APPT_REMARK_DESC = " " + PREFIX_REMARK + VALID_APPT_REMARK;
    public static final String APPT_ANOTHER_REMARK_DESC = " " + PREFIX_REMARK + ANOTHER_VALID_APPT_REMARK;

    public static final String INVALID_PATIENT_INDEX = "WAT";
    public static final String INVALID_DOCTOR_INDEX = "!!!!";
    public static final String INVALID_START_DATE = "2-2-2022";
    public static final String INVALID_START_TIME = "1211";
    public static final String INVALID_DURATION = "forty-five minutes";
    public static final String INVALID_DURATION_THREE_HOURS = "180";
    public static final String INVALID_START = "2-2-2022 1000";

    public static final String INVALID_PATIENT_INDEX_DESC = " " + PREFIX_PATIENT + INVALID_PATIENT_INDEX;
    public static final String INVALID_DOCTOR_INDEX_DESC = " " + PREFIX_DOCTOR + INVALID_DOCTOR_INDEX;

    public static final String INVALID_START_DATE_DESC = " " + PREFIX_START + INVALID_START_DATE
            + " " + VALID_APPT_TIME_TWO_HOUR;
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_START + VALID_APPT_DATE_THIRTY_MIN
            + " " + INVALID_START_TIME;
    public static final String INVALID_DURATION_DESC = " " + PREFIX_DURATION
            + INVALID_DURATION;
    public static final String INVALID_DURATION_THREE_HOURS_DESC = " " + PREFIX_DURATION + INVALID_DURATION_THREE_HOURS;
    public static final String INVALID_START_DESC = " " + PREFIX_START + INVALID_START;

    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_EDIT_THIRTY_MIN_APPT;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_EDIT_TWO_HOUR_APPT;

    public static final AddAppointmentCommand.AddAppointmentDescriptor DESC_THIRTY_MIN_APPT;
    public static final AddAppointmentCommand.AddAppointmentDescriptor DESC_TWO_HOUR_APPT;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withBirthDate(VALID_BIRTH_DATE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthDate(VALID_BIRTH_DATE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_DR_AMY = new EditDoctorDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withBirthDate(VALID_BIRTH_DATE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_DR_BOB = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthDate(VALID_BIRTH_DATE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_EDIT_THIRTY_MIN_APPT = new EditAppointmentDescriptorBuilder().withPatientIndex(VALID_PATIENT_INDEX)
                .withDoctorIndex(VALID_DOCTOR_INDEX).withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                .withStartTime(VALID_APPT_TIME_THIRTY_MIN).withDuration(VALID_APPT_DURATION_THIRTY_MIN_STR)
                .withRemark(VALID_APPT_REMARK).build();
        DESC_EDIT_TWO_HOUR_APPT = new EditAppointmentDescriptorBuilder().withPatientIndex(ANOTHER_VALID_PATIENT_INDEX)
                .withDoctorIndex(ANOTHER_VALID_DOCTOR_INDEX).withAppointmentDate(VALID_APPT_DATE_TWO_HOUR)
                .withStartTime(VALID_APPT_TIME_TWO_HOUR).withDuration(VALID_APPT_DURATION_TWO_HOUR_STR)
                .withRemark(VALID_APPT_REMARK).build();
        DESC_THIRTY_MIN_APPT = new AddAppointmentDescriptorBuilder().withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                .withSession(VALID_APPT_TIME_THIRTY_MIN, VALID_APPT_DURATION_THIRTY_MIN)
                .withRemark(VALID_APPT_REMARK).build();
        DESC_TWO_HOUR_APPT = new AddAppointmentDescriptorBuilder().withAppointmentDate(VALID_APPT_DATE_TWO_HOUR)
                .withSession(VALID_APPT_TIME_TWO_HOUR, VALID_APPT_DURATION_TWO_HOUR)
                .withRemark(VALID_APPT_REMARK).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)} that
     * takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the plannermd, filtered person list and selected person in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PlannerMd expectedPlannerMd = new PlannerMd(actualModel.getPlannerMd());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPlannerMd, actualModel.getPlannerMd());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }

    /**
     * Updates {@code model}'s patient filtered list to show only the patient at the given
     * {@code targetIndex} in the {@code model}'s plannermd.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Updates {@code model}'s doctor filtered list to show only the doctor at the given
     * {@code targetIndex} in the {@code model}'s plannermd.
     */
    public static void showDoctorAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDoctorList().size());

        Doctor doctor = model.getFilteredDoctorList().get(targetIndex.getZeroBased());
        final String[] splitName = doctor.getName().fullName.split("\\s+");
        model.updateFilteredDoctorList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDoctorList().size());
    }

    /**
     * Updates {@code model}'s appointment filtered list to show only the appointment at the given
     * {@code targetIndex} in the {@code model}'s plannermd.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        final String[] splitPatientName = appointment.getPatient().getName().fullName.split("\\s+");
        model.updateFilteredAppointmentList(
                new AppointmentFieldsContainKeywordsPredicate(Arrays.asList(splitPatientName[0])));
        System.out.println(model.getFilteredAppointmentList());
        assertEquals(1, model.getFilteredAppointmentList().size());
    }

}
