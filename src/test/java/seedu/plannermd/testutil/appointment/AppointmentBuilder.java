package seedu.plannermd.testutil.appointment;

import static seedu.plannermd.testutil.PersonBuilder.DEFAULT_REMARK;

import java.util.Set;

import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.BirthDate;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.model.tag.Tag;
import seedu.plannermd.testutil.doctor.DoctorBuilder;
import seedu.plannermd.testutil.patient.PatientBuilder;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    private static final Doctor DEFAULT_DOCTOR = new DoctorBuilder().build();
    private static final Patient DEFAULT_PATIENT = new PatientBuilder().build();
    private static final String DEFAULT_DATE = "1/1/2025";
    private static final String DEFAULT_TIME = "12:00";
    private static final Integer DEFAULT_DURATION = 30;

    private Doctor doctor;
    private Patient patient;
    private AppointmentDate date;
    private Session session;
    private Remark remark;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        doctor = DEFAULT_DOCTOR;
        patient = DEFAULT_PATIENT;
        remark = new Remark(DEFAULT_REMARK);
        date = new AppointmentDate(DEFAULT_DATE);
        session = new Session(DEFAULT_TIME, new Duration(DEFAULT_DURATION));
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code personToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        doctor = appointmentToCopy.getDoctor();
        patient = appointmentToCopy.getPatient();
        date = appointmentToCopy.getAppointmentDate();
        session = appointmentToCopy.getSession();
        remark = appointmentToCopy.getRemark();
    }

    /**
     * Sets the {@code Doctor} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctor(Name name, Phone phone, Email email, Address address, BirthDate birthDate,
                                         Remark remark, Set<Tag> tags) {
        this.doctor = new Doctor(name, phone, email, address, birthDate, remark, tags);
        return this;
    }


    /**
     * Sets the {@code Doctor} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    /**
     * Sets the {@code Patient} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatient(Name name, Phone phone, Email email, Address address, BirthDate birthDate,
                                          Remark remark, Set<Tag> tags, Risk risk) {
        this.patient = new Patient(name, phone, email, address, birthDate, remark, tags, risk);
        return this;
    }

    /**
     * Sets the {@code Patient} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    /**
     * Sets the {@code AppointmentDate} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDate(String date) {
        this.date = new AppointmentDate(date);
        return this;
    }

    /**
     * Sets the {@code Session} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withSession(String startTime, Integer duration) {
        Duration dur = new Duration(duration);
        this.session = new Session(startTime, dur);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Appointment build() {
        return new Appointment(patient, doctor, date, session, remark);
    }

}
