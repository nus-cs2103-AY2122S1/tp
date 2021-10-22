package seedu.plannermd.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.plannermd.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.FIVE_MIN_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Remark;

class JsonAdaptedAppointmentTest {
    private static final String INVALID_DATE = "8-8-2022";

    private static final JsonAdaptedPatient VALID_PATIENT = new JsonAdaptedPatient(FIVE_MIN_APPOINTMENT.getPatient());
    private static final JsonAdaptedDoctor VALID_DOCTOR = new JsonAdaptedDoctor(FIVE_MIN_APPOINTMENT.getDoctor());
    private static final String VALID_DATE = FIVE_MIN_APPOINTMENT.getAppointmentDate().toInputStringFormat();
    private static final JsonAdaptedSession VALID_SESSION = new JsonAdaptedSession(FIVE_MIN_APPOINTMENT.getSession());
    private static final String VALID_REMARK = FIVE_MIN_APPOINTMENT.getRemark().toString();

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(FIVE_MIN_APPOINTMENT);
        assertEquals(FIVE_MIN_APPOINTMENT, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PATIENT, VALID_DOCTOR, INVALID_DATE,
                VALID_SESSION, VALID_REMARK);
        String expectedMessage = AppointmentDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PATIENT, VALID_DOCTOR, null,
                VALID_SESSION, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JsonAdaptedAppointment.IDENTIFIER_DATE);
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullPatient_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(null, VALID_DOCTOR, VALID_DATE,
                VALID_SESSION, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Patient.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDoctor_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PATIENT, null, VALID_DATE,
                VALID_SESSION, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Doctor.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullSession_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PATIENT, VALID_DOCTOR, VALID_DATE,
                null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Session.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PATIENT, VALID_DOCTOR, VALID_DATE,
                VALID_SESSION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }
}
