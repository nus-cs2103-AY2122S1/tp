package seedu.plannermd.logic.commands.apptcommand;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;
import seedu.plannermd.model.appointment.AppointmentIsAfterPredicate;
import seedu.plannermd.model.appointment.AppointmentIsBeforePredicate;

public class AppointmentFilters {

    private Predicate<Appointment> startAfter = x -> true;
    private Predicate<Appointment> startBefore = x -> true;
    private Predicate<Appointment> hasPatient = x -> true;
    private Predicate<Appointment> hasDoctor = x -> true;

    private AppointmentFilters() {}

    public static  AppointmentFilters allAppointmentsFilter() {
        return new AppointmentFilters();
    }

    public static AppointmentFilters upcomingAppointmentsFilter() {
        AppointmentFilters filter = new AppointmentFilters();
        filter.setStartAfter(new AppointmentIsAfterPredicate(LocalDateTime.now()));
        return filter;
    }

    public void setHasPatient(AppointmentContainsPatientPredicate predicate) {
        requireNonNull(predicate);
        hasPatient = predicate;
    }

    public void setHasDoctor(AppointmentContainsDoctorPredicate predicate) {
        requireNonNull(predicate);
        hasDoctor = predicate;
    }

    public void setStartAfter(AppointmentIsAfterPredicate predicate) {
        requireNonNull(predicate);
        startAfter = predicate;
    }

    public void setStartBefore(AppointmentIsBeforePredicate predicate) {
        requireNonNull(predicate);
        startBefore = predicate;
    }

    /**
     * Collects all the given filters and convert them into a single predicate
     * that can be used to filter the {@code Appointment} list.
     */
    public Predicate<Appointment> collectAllFilters() {
        List<Predicate<Appointment>> allPredicates = new ArrayList<>(
                Arrays.asList(startAfter, startBefore, hasPatient, hasDoctor));

        return allPredicates.stream().reduce(Predicate::and).orElse(x -> true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentFilters that = (AppointmentFilters) o;
        return startAfter.equals(that.startAfter) && startBefore.equals(that.startBefore)
                && hasPatient.equals(that.hasPatient) && hasDoctor.equals(that.hasDoctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startAfter, startBefore, hasPatient, hasDoctor);
    }
}
