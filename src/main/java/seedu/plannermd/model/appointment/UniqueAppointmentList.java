package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.plannermd.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.plannermd.model.appointment.exceptions.ClashingAppointmentException;
import seedu.plannermd.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;


/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * An appointment is considered unique by comparing using {@code Appointment#isSameAppointment(Appointment)}.
 * As such, adding and updating of appointments uses Appointment#isSameAppointment(Appointment) for equality
 * so as to ensure that the appointment being added or updated is unique in terms of identity
 * in the UniqueAppointmentList.
 * However, the removal of an appointment uses Appointment#equals(Object) so as to ensure that the
 * appointment with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueAppointmentList implements Iterable<Appointment> {
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final SortedList<Appointment> sortedList = internalList.sorted();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(sortedList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Returns true if the given appointment clashes with any in the list.
     */
    public boolean isClash(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isClash);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        if (isClash(toAdd)) {
            throw new ClashingAppointmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment identity of {@code editedAppointment} must not be the same
     * as another existing appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.isSameAppointment(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
    }

    /**
     * Deletes {@code Appointments} with {@code person} from appointment list
     *
     * @param person person whose appointments are to be deleted
     * @param <T> subtype of Person
     */
    public <T extends Person> void deleteAppointmentsWithPerson(T person) {
        if (person instanceof Patient) {
            deleteAppointmentsWithPatient((Patient) person);
        } else if (person instanceof Doctor) {
            deleteAppointmentsWithDoctor((Doctor) person);
        }
    }

    /**
     * Replaces {@code person} with {@code editedPerson} for {@code Appointments} with {@code person}.
     *
     * @param person person whose appointments are to be updated
     * @param editedPerson person replacing {@code person}
     * @param <T> subtype of Person
     */
    public <T extends Person> void editAppointmentsWithPerson(T person, T editedPerson) {
        if (person instanceof Patient) {
            editAppointmentsWithPatient((Patient) person, (Patient) editedPerson);
        } else if (person instanceof Doctor) {
            editAppointmentsWithDoctor((Doctor) person, (Doctor) editedPerson);
        }
    }

    /**
     * Iterates though list of {@code Appointments} and delete those with {@code patient}
     *
     * @param patient person whose appointments are to be deleted
     */
    public void deleteAppointmentsWithPatient(Patient patient) {
        internalList.removeIf(appointment -> appointment.getPatient().isSamePerson(patient));
    }

    /**
     * Iterates though list of {@code Appointments} and delete those with {@code doctor}
     *
     * @param doctor person whose appointments are to be deleted
     */
    public void deleteAppointmentsWithDoctor(Doctor doctor) {
        internalList.removeIf(appointment -> appointment.getDoctor().isSamePerson(doctor));
    }

    /**
     * Iterates though list of {@code Appointments} and update those with {@code patient} to {@code editedPatient}
     *
     * @param patient patient whose appointments are to be updated
     * @param editedPatient patient replacing {@code patient}
     */
    public void editAppointmentsWithPatient(Patient patient, Patient editedPatient) {
        for (Appointment appointment : internalList) {
            if (appointment.getPatient().isSamePerson(patient)) {
                Appointment editedAppointment =
                        new Appointment(editedPatient, appointment.getDoctor(),
                        appointment.getAppointmentDate(), appointment.getSession(), appointment.getRemark());
                setAppointment(appointment, editedAppointment);
            }
        }
    }

    /**
     * Iterates though list of {@code Appointments} and update those with {@code doctor} to {@code editedDoctor}
     *
     * @param doctor doctor whose appointments are to be updated
     * @param editedDoctor doctor replacing {@code doctor}
     */
    public void editAppointmentsWithDoctor(Doctor doctor, Doctor editedDoctor) {
        for (Appointment appointment : internalList) {
            if (appointment.getDoctor().isSamePerson(doctor)) {
                Appointment editedAppointment =
                        new Appointment(appointment.getPatient(), editedDoctor,
                        appointment.getAppointmentDate(), appointment.getSession(), appointment.getRemark());
                setAppointment(appointment, editedAppointment);
            }
        }
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentList // instanceof handles nulls
                && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code appointments} contains only unique appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointment(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
