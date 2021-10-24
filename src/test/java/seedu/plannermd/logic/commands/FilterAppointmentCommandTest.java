package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.plannermd.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.getTypicalAppointments;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_CARL;
import static seedu.plannermd.testutil.patient.TypicalPatients.FIONA;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterAppointmentCommand;
import seedu.plannermd.logic.commands.apptcommand.FilterUpcomingAppointmentCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.appointment.AppointmentFiltersBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterAppointmentCommand.
 */
public class FilterAppointmentCommandTest {

    private final String startDateString = "20/12/2024";
    private final LocalDate sampleStartDate = LocalDate.of(2024, 12, 20);
    private final Appointment sampleAppointment = new AppointmentBuilder().withPatient(FIONA)
            .withDoctor(DR_CARL).withDate(startDateString).withSession("18:00", 10).build();

    private Model model;
    private Model expectedModel;

    private void reinitialiseModel() {
        model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

        model.addAppointment(sampleAppointment);
        expectedModel.addAppointment(sampleAppointment);
    }

    @Test
    public void execute_noParameterSpecified_allAppointmentsMatch() {
        Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPlannerMd(), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size());
        FilterAppointmentCommand command = new FilterAppointmentCommand(AppointmentFilters.allAppointmentsFilter());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalAppointments(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_allParameterSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Results found match sample appointments
        AppointmentFilters filter = new AppointmentFiltersBuilder().withStartDate(sampleStartDate)
                .withEndDate(sampleStartDate.plusDays(2)).withPatientKeywords("Fiona")
                .withDoctorKeywords("Carl").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterAppointmentCommand command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(sampleAppointment), model.getFilteredAppointmentList());

        // No results found (starting dates do not match)
        filter = new AppointmentFiltersBuilder(filter).withStartDate(sampleStartDate.plusDays(1)).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_patientKeywordSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Results found match sample appointment
        AppointmentFilters filter = new AppointmentFiltersBuilder().withPatientKeywords("Fiona").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterAppointmentCommand command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(sampleAppointment), model.getFilteredAppointmentList());

        // No results found
        filter = new AppointmentFiltersBuilder(filter).withPatientKeywords("Bruce").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_doctorKeywordSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Results found match sample appointment
        AppointmentFilters filter = new AppointmentFiltersBuilder().withDoctorKeywords("carl").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterAppointmentCommand command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(sampleAppointment), model.getFilteredAppointmentList());

        // No results found
        filter = new AppointmentFiltersBuilder(filter).withDoctorKeywords("Karl").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_startDateSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Start date is after typical appointments but before sample appointment
        AppointmentFilters filter = new AppointmentFiltersBuilder()
                .withStartDate(sampleStartDate.minusDays(1)).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterAppointmentCommand command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(sampleAppointment), model.getFilteredAppointmentList());

        // // Start date is after typical appointments and sample appointment
        filter = new AppointmentFiltersBuilder(filter)
                .withStartDateTime(sampleStartDate.atTime(20, 30)).build(); // With date time
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
        filter = new AppointmentFiltersBuilder(filter).withStartDate(sampleStartDate.plusDays(1)).build(); // With date
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_endDateSpecified_successfullyFiltered() {
        reinitialiseModel();

        // End date is after sample appointment and typical appointments
        AppointmentFilters filter = new AppointmentFiltersBuilder()
                .withEndDate(sampleStartDate).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size());
        FilterAppointmentCommand command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Appointment> expectedFilteredList = getTypicalAppointments();
        expectedFilteredList.add(sampleAppointment);
        assertEquals(expectedFilteredList, model.getFilteredAppointmentList());

        // End date is before sample appointment but after typical appointment
        filter = new AppointmentFiltersBuilder(filter)
                .withEndDate(sampleStartDate.minusDays(1)).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size() - 1);
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalAppointments(), model.getFilteredAppointmentList());

        // End date is before all appointments
        filter = new AppointmentFiltersBuilder(filter)
                .withEndDate(sampleStartDate.minusYears(20)).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void equals() {
        AppointmentFilters firstFilter = new AppointmentFiltersBuilder().withPatientKeywords("Alice").build();
        AppointmentFilters secondFilter = new AppointmentFiltersBuilder().withDoctorKeywords("Bob").build();

        FilterAppointmentCommand firstCommand = new FilterAppointmentCommand(firstFilter);
        FilterAppointmentCommand secondCommand = new FilterAppointmentCommand(secondFilter);

        // same object -> equals
        assertEquals(firstCommand, firstCommand);

        // same values -> not equals
        FilterAppointmentCommand firstCommandCopy = new FilterAppointmentCommand(firstFilter);
        assertEquals(firstCommandCopy, firstCommand);

        // different types -> not equals
        assertNotEquals(firstCommand, 1);
        assertNotEquals(firstCommand,
                new FilterUpcomingAppointmentCommand(AppointmentFilters.upcomingAppointmentsFilter()));

        // null -> not equals
        assertNotEquals(firstCommand, null);

        // different person -> not equals
        assertNotEquals(secondCommand, firstCommand);
    }
}
