package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlySchedule;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.exceptions.AppointmentNotFoundException;
import seedu.address.model.schedule.exceptions.DuplicateAppointmentException;

/**
 * A list of Appointments that enforces uniqueness among appointments to be added
 *
 * Supports a minimal set of list operations.
 */
public class Schedule implements Iterable<Appointment>, ReadOnlySchedule {
    // Data Fields
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointmentListUnModifiable =
            FXCollections.unmodifiableObservableList(appointmentList);

    public Schedule() {};

    /**
     * Creates an Schedule using the Appointments in the {@code toBeCopied}
     */
    public Schedule(ReadOnlySchedule toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code appointments}.
     * {@code appointments} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointmentList.setAll(appointments);
    }

    /**
     * Resets the existing data of this {@code Schedule} with {@code newData}.
     */
    public void resetData(ReadOnlySchedule newData) {
        requireNonNull(newData);

        setAppointments(newData.getSchedule());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return appointmentListUnModifiable;
    }

    /**
     * Adds an Appointment to the list.
     * Appointment Must not already be in the list
     */
    public void addAppointment(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        appointmentList.add(toAdd);
        appointmentList.sort(Comparator.comparing(Appointment::getDescription));
        appointmentList.sort(Comparator.comparing(Appointment::getTime));
        appointmentList.sort(Comparator.comparing(Appointment::getDate));
    }

    public void sortAppointmentByDescription() {
        appointmentList.sort(Comparator.comparing(Appointment::getDescription));
    }

    public void sortAppointmentByDate() {
        appointmentList.sort(Comparator.comparing(Appointment::getDate));
    }

    /**
     * Removes the equivalent Appointment from the list.
     * The Appointment must exist in the list.
     */
    public void deleteAppointment(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!appointmentList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    /**
     * Returns the list of {@Code Appointment} that is related to the {@Code Person}
     */
    public List<Appointment> getRelatedAppointments(Person client) {
        return appointmentListUnModifiable
                .stream()
                .parallel()
                .filter(appointment -> appointment.hasClient(client))
                .collect(Collectors.toList());
    }

    /**
     * Check if appointmentList contains {@code Appointment}
     * @return boolean result
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return appointmentList.contains(toCheck);
    }

    @Override
    public Iterator<Appointment> iterator() {
        return appointmentList.iterator();
    }

    @Override
    public int hashCode() {
        return appointmentList.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Schedule // instanceof handles nulls
                && appointmentList.equals(((Schedule) other).appointmentList));
    }

    @Override
    public ObservableList<Appointment> getSchedule() {
        return appointmentList;
    }
}
