package seedu.plannermd.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;

/**
 * An Immutable PlannerMd that is serializable to JSON format.
 */
@JsonRootName(value = "plannermd")
class JsonSerializablePlannerMd {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "Doctors list contains duplicate doctor(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENTS = "Appointments list contains duplicate appointment(s).";
    public static final String MESSAGE_CLASHING_APPOINTMENTS = "Appointments list contains clashing appointment(s).";
    public static final String MESSAGE_MISSING_PATIENT_APPOINTMENT =
            "Appointments list contains patient(s) which do not exist.";
    public static final String MESSAGE_MISSING_DOCTOR_APPOINTMENT =
            "Appointments list contains doctor(s) which do not exist.";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();
    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePlannerMd} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePlannerMd(@JsonProperty("patients") List<JsonAdaptedPatient> patients,
                                     @JsonProperty("doctors") List<JsonAdaptedDoctor> doctors,
                                     @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.patients.addAll(patients);
        this.doctors.addAll(doctors);
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyPlannerMd} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePlannerMd}.
     */
    public JsonSerializablePlannerMd(ReadOnlyPlannerMd source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
        doctors.addAll(source.getDoctorList().stream().map(JsonAdaptedDoctor::new).collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this plannermd into the model's {@code PlannerMd} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PlannerMd toModelType() throws IllegalValueException {
        PlannerMd plannerMd = new PlannerMd();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (plannerMd.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            plannerMd.addPatient(patient);
        }
        for (JsonAdaptedDoctor jsonAdaptedDoctor : doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();
            if (plannerMd.hasDoctor(doctor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DOCTOR);
            }
            plannerMd.addDoctor(doctor);
        }
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (plannerMd.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENTS);
            }
            if (plannerMd.isClashAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_CLASHING_APPOINTMENTS);
            }

            // To be defensive here, we ensure that the exact patients and doctors specified
            // in the appointment does exist in PlannerMD
            Optional<Patient> appointmentPatient = plannerMd.getExactPatient(appointment.getPatient());
            if (appointmentPatient.isEmpty()) {
                throw new IllegalValueException(MESSAGE_MISSING_PATIENT_APPOINTMENT);
            }
            Optional<Doctor> appointmentDoctor = plannerMd.getExactDoctor(appointment.getDoctor());
            if (appointmentDoctor.isEmpty()) {
                throw new IllegalValueException(MESSAGE_MISSING_DOCTOR_APPOINTMENT);
            }

            // Create a new Appointment with the exact Patient and Doctor objects in PlannerMd
            // This Appointment will then be added into PlannerMd
            Appointment linkedAppointment = new Appointment(appointmentPatient.get(), appointmentDoctor.get(),
                    appointment.getAppointmentDate(), appointment.getSession(),
                    appointment.getRemark());

            plannerMd.addAppointment(linkedAppointment);
        }

        return plannerMd;
    }
}

