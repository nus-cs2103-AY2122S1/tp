package seedu.plannermd.logic.commands.apptcommand;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;
import seedu.plannermd.model.appointment.AppointmentIsAfterPredicate;
import seedu.plannermd.model.appointment.AppointmentIsBeforePredicate;

/**
 * Stores the details of all the filters to filter the appointment list in plannermd with.
 */
public class AppointmentFilters {

    /**
     * Appointment starts after a certain point in time.
     */
    private Predicate<Appointment> startAfter;
    /**
     * Appointment starts before a certain point in time.
     */
    private Predicate<Appointment> startBefore;
    /**
     * Appointment contains a patient whose names matches a given set of keywords.
     */
    private Predicate<Appointment> hasPatient;
    /**
     * Appointment contains a doctor whose names matches a given set of keywords.
     */
    private Predicate<Appointment> hasDoctor;

    private AppointmentFilters() {}

    /**
     * Initialises a {@code AppointmentFilters} that filters through all appointments in the
     * plannermd.
     */
    public static AppointmentFilters allAppointmentsFilter() {
        return new AppointmentFilters();
    }

    /**
     * Initialises a {@code AppointmentFilters} that filters through all upcoming appointments in
     * the plannermd.
     */
    public static AppointmentFilters upcomingAppointmentsFilter() {
        AppointmentFilters filter = new AppointmentFilters();
        filter.setStartAfter(new AppointmentIsAfterPredicate(LocalDateTime.now()));
        return filter;
    }

    /**
     * Initialises a {@code AppointmentFilters} that only shows appointments for the current day.
     */
    public static AppointmentFilters todayAppointmentFilter() {
        AppointmentFilters filter = new AppointmentFilters();
        filter.setStartAfter(new AppointmentIsAfterPredicate(LocalDate.now()));
        filter.setStartBefore(new AppointmentIsBeforePredicate(LocalDate.now()));
        return filter;
    }

    /**
     * Initialises a {@code AppointmentFilters} that only shows appointments on the {@code localDate}.
     */
    public static AppointmentFilters appointmentFiltersAtDate(LocalDate localDate) {
        AppointmentFilters filters = new AppointmentFilters();
        filters.setStartAfter(new AppointmentIsAfterPredicate(localDate));
        filters.setStartBefore(new AppointmentIsBeforePredicate(localDate));
        return filters;
    }

    /**
     * Copy constructor.
     */
    public static AppointmentFilters copyAppointmentFilters(AppointmentFilters filterToCopy) {
        AppointmentFilters filters = new AppointmentFilters();
        filters.startAfter = filterToCopy.startAfter;
        filters.startBefore = filterToCopy.startBefore;
        filters.hasPatient = filterToCopy.hasPatient;
        filters.hasDoctor = filterToCopy.hasDoctor;
        return filters;
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

        return allPredicates.stream().map(x -> Optional.ofNullable(x).orElse(y -> true))
                .reduce(Predicate::and).orElse(x -> true);
    }

    /**
     * Converts the filters into a String which when parsed with a {@code FilterAppointmentCommandParser}
     * gives the same filter.
     */
    public String getFilterDetails() {
        List<Predicate<Appointment>> allPredicates = new ArrayList<>(
                Arrays.asList(startAfter, startBefore, hasPatient, hasDoctor));

        return allPredicates.stream().map(x -> Optional.ofNullable(x).map(Object::toString).orElse(""))
                .collect(Collectors.joining()).trim();
    }

    /**
     * Converts the filters into a String which when parsed with a {@code FilterUpcomingAppointmentCommandParser}
     * gives the same filter.
     */
    public String getUpcomingFilterDetails() {
        List<Predicate<Appointment>> allPredicates = new ArrayList<>(Arrays.asList(hasPatient, hasDoctor));

        return allPredicates.stream().map(x -> Optional.ofNullable(x).map(Object::toString).orElse(""))
                .collect(Collectors.joining()).trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppointmentFilters filters = (AppointmentFilters) o;
        return Objects.equals(startAfter, filters.startAfter) && Objects.equals(startBefore, filters.startBefore)
                && Objects.equals(hasPatient, filters.hasPatient) && Objects.equals(hasDoctor, filters.hasDoctor);
    }

}
