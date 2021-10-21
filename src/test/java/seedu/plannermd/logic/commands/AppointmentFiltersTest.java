package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.plannermd.logic.commands.CommandTestUtil.FILTER_VALID_END_DATE;
import static seedu.plannermd.logic.commands.CommandTestUtil.FILTER_VALID_START_DATE;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_STRING_END_DATE;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_STRING_START_DATE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_START;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.testutil.appointment.AppointmentFiltersBuilder;

class AppointmentFiltersTest {

    @Test
    public void equals() {
        AppointmentFilters sampleFilter = new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE)
                .withEndDate(FILTER_VALID_END_DATE).withPatientKeywords(Collections.singletonList("Alice"))
                .withDoctorKeywords(Collections.singletonList("Bob")).build();

        // same values -> returns true
        AppointmentFilters copiedFilter = new AppointmentFiltersBuilder(sampleFilter).build();
        assertEquals(copiedFilter, sampleFilter);
        assertEquals(AppointmentFilters.allAppointmentsFilter(), AppointmentFilters.allAppointmentsFilter());

        // Same object -> returns true
        assertEquals(sampleFilter, sampleFilter);

        // null -> returns false
        assertNotEquals(sampleFilter, null);

        // Different types -> return false
        assertNotEquals(sampleFilter, "test");

        // Filter all appointments vs filter upcoming appointments -> returns false
        assertNotEquals(AppointmentFilters.allAppointmentsFilter(), AppointmentFilters.upcomingAppointmentsFilter());

        // AppointmentFilter with all default values vs appointment
        // Different patient keywords -> returns false
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter)
                .withPatientKeywords(Arrays.asList("Peter", "Steve")).build();
        assertNotEquals(sampleFilter, copiedFilter);
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter)
                .withPatientKeywords(Collections.emptyList()).build();
        assertNotEquals(sampleFilter, copiedFilter);
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter)
                .withPatientKeywords(Collections.singletonList("Bob")).build();
        assertNotEquals(sampleFilter, copiedFilter);

        // Different doctor keywords -> returns false
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter)
                .withDoctorKeywords(Arrays.asList("Peter", "Steve")).build();
        assertNotEquals(sampleFilter, copiedFilter);
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter)
                .withDoctorKeywords(Collections.emptyList()).build();
        assertNotEquals(sampleFilter, copiedFilter);
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter)
                .withDoctorKeywords(Collections.singletonList("Alice")).build();
        assertNotEquals(sampleFilter, copiedFilter);

        // Different start dates
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter).withStartDate(FILTER_VALID_END_DATE).build();
        assertNotEquals(sampleFilter, copiedFilter);
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter).withStartDateTime(LocalDateTime.now()).build();
        assertNotEquals(sampleFilter, copiedFilter);

        // Different end dates
        copiedFilter = new AppointmentFiltersBuilder(sampleFilter).withEndDate(FILTER_VALID_START_DATE).build();
        assertNotEquals(sampleFilter, copiedFilter);

        // Same start date, default values for the rest
        assertEquals(new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE).build(),
                new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE).build());
    }

    @Test
    public void test_setNullPredicates_exceptionThrown() {
        AppointmentFilters sampleFilter = AppointmentFilters.allAppointmentsFilter();
        assertThrows(NullPointerException.class, () -> sampleFilter.setStartBefore(null));
        assertThrows(NullPointerException.class, () -> sampleFilter.setStartAfter(null));
        assertThrows(NullPointerException.class, () -> sampleFilter.setHasPatient(null));
        assertThrows(NullPointerException.class, () -> sampleFilter.setHasDoctor(null));
    }

    @Test
    public void testFilterDetails() {
        String userInput = PREFIX_START + VALID_STRING_START_DATE + " " + PREFIX_END + VALID_STRING_END_DATE + " "
                + PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "John";
        AppointmentFilters filters = new AppointmentFiltersBuilder().withPatientKeywords("Alice")
                .withDoctorKeywords("John").withStartDate(FILTER_VALID_START_DATE)
                .withEndDate(FILTER_VALID_END_DATE).build();
        assertEquals(userInput, filters.getFilterDetails());

        userInput = PREFIX_START + VALID_STRING_START_DATE + " " + PREFIX_END + VALID_STRING_END_DATE;
        filters = new AppointmentFiltersBuilder().withStartDate(FILTER_VALID_START_DATE)
                .withEndDate(FILTER_VALID_END_DATE).build();
        assertEquals(userInput, filters.getFilterDetails());
    }

    @Test
    public void testUpcomingFilterDetails() {
        String userInput = PREFIX_PATIENT + "Alice " + PREFIX_DOCTOR + "John";
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming().withPatientKeywords("Alice")
                .withDoctorKeywords("John").build();
        assertEquals(userInput, filters.getUpcomingFilterDetails());

        userInput = PREFIX_PATIENT + "Alice";
        filters = new AppointmentFiltersBuilder().withUpcoming().withPatientKeywords("Alice").build();
        assertEquals(userInput, filters.getUpcomingFilterDetails());
    }
}
