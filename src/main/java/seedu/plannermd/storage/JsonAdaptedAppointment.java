package seedu.plannermd.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Remark;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    public static final String IDENTIFIER_DATE = "date";

    private final JsonAdaptedPatient patient;
    private final JsonAdaptedDoctor doctor;
    private final String date;
    private final JsonAdaptedSession session;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patient") JsonAdaptedPatient patient,
                                  @JsonProperty("doctor") JsonAdaptedDoctor doctor,
                                  @JsonProperty("date") String date,
                                  @JsonProperty("session") JsonAdaptedSession session,
                                  @JsonProperty("remark") String remark) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.session = session;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        this.patient = new JsonAdaptedPatient(source.getPatient());
        this.doctor = new JsonAdaptedDoctor(source.getDoctor());
        this.date = source.getAppointmentDate().toInputStringFormat();
        this.session = new JsonAdaptedSession(source.getSession());
        this.remark = source.getRemark().value;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's
     * {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (patient == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Patient.class.getSimpleName()));
        }
        Patient modelPatient = patient.toModelType();

        if (doctor == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Doctor.class.getSimpleName()));
        }
        Doctor modelDoctor = doctor.toModelType();

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IDENTIFIER_DATE));
        }
        if (!AppointmentDate.isValidAppointmentDate(date)) {
            throw new IllegalValueException(AppointmentDate.MESSAGE_CONSTRAINTS);
        }
        final AppointmentDate modelAppointmentDate = new AppointmentDate(date);

        if (session == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Session.class.getSimpleName()));
        }
        Session modelSession = session.toModelType();

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        return new Appointment(modelPatient, modelDoctor, modelAppointmentDate, modelSession, modelRemark);
    }

}
