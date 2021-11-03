package seedu.plannermd.testutil.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.model.appointment.AppointmentContainsDoctorPredicate;
import seedu.plannermd.model.appointment.AppointmentContainsPatientPredicate;
import seedu.plannermd.model.appointment.AppointmentIsAfterPredicate;
import seedu.plannermd.model.appointment.AppointmentIsBeforePredicate;

/**
 * A utility class to help with building Appointment Filter objects.
 */
public class AppointmentFiltersBuilder {

    private AppointmentFilters filters;

    /**
     * Initializes the AppointmentFilterBuilder with the default values.
     */
    public AppointmentFiltersBuilder() {
        filters = AppointmentFilters.allAppointmentsFilter();
    }

    /**
     * Initializes the AppointmentFilterBuilder with the data of
     * {@code appointmentFilterToCopy}
     */
    public AppointmentFiltersBuilder(AppointmentFilters appointmentFilterToCopy) {
        requireNonNull(appointmentFilterToCopy);
        filters = AppointmentFilters.copyAppointmentFilters(appointmentFilterToCopy);
    }

    /**
     * Sets the {@code AppointmentFilters} that we are building to filter for upcoming appointments.
     */
    public AppointmentFiltersBuilder withUpcoming() {
        filters.setStartAfter(new AppointmentIsAfterPredicate(LocalDateTime.now()));
        return this;
    }

    /**
     * Sets the {@code startAfter} of the {@code AppointmentFilters} that we are building.
     */
    public AppointmentFiltersBuilder withStartDate(LocalDate date) {
        filters.setStartAfter(new AppointmentIsAfterPredicate(date));
        return this;
    }

    /**
     * Sets the {@code startAfter} of the {@code AppointmentFilters} that we are building.
     */
    public AppointmentFiltersBuilder withStartDateTime(LocalDateTime dateTime) {
        filters.setStartAfter(new AppointmentIsAfterPredicate(dateTime));
        return this;
    }

    /**
     * Sets the {@code startBefore} of the {@code AppointmentFilters} that we are building.
     */
    public AppointmentFiltersBuilder withEndDate(LocalDate date) {
        filters.setStartBefore(new AppointmentIsBeforePredicate(date));
        return this;
    }

    /**
     * Sets the {@code hasPatient} of the {@code AppointmentFilters} that we are building.
     */
    public AppointmentFiltersBuilder withPatientKeywords(List<String> keywords) {
        filters.setHasPatient(new AppointmentContainsPatientPredicate(keywords));
        return this;
    }

    /**
     * Sets the {@code hasPatient} of the {@code AppointmentFilters} that we are building.
     */
    public AppointmentFiltersBuilder withPatientKeywords(String... keywords) {
        filters.setHasPatient(new AppointmentContainsPatientPredicate(Arrays.asList(keywords)));
        return this;
    }

    /**
     * Sets the {@code hasDoctor} of the {@code AppointmentFilters} that we are building.
     */
    public AppointmentFiltersBuilder withDoctorKeywords(List<String> keywords) {
        filters.setHasDoctor(new AppointmentContainsDoctorPredicate(keywords));
        return this;
    }

    /**
     * Sets the {@code hasDoctor} of the {@code AppointmentFilters} that we are building.
     */
    public AppointmentFiltersBuilder withDoctorKeywords(String... keywords) {
        filters.setHasDoctor(new AppointmentContainsDoctorPredicate(Arrays.asList(keywords)));
        return this;
    }

    /**
     * Builds the {@code AppointmentFilter} object.
     */
    public AppointmentFilters build() {
        return filters;
    }
}
