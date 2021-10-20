package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.plannermd.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.getTypicalAppointments;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_CARL;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterAppointmentCommand;
import seedu.plannermd.logic.commands.apptcommand.FilterUpcomingAppointmentCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.appointment.AppointmentFiltersBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterAppointmentCommand.
 */
public class FilterAppointmentCommandTest {

    private final String startDateString = "20/12/2021";
    private final LocalDate sampleStartDate = LocalDate.of(2021, 12, 20);
    private final Appointment sampleAppointment = new AppointmentBuilder().withPatient(ALICE)
            .withDoctor(DR_CARL).withDate(startDateString).withSession("18:00", 10).build();

    private Model model;
    private Model expectedModel;

    private void reinitialiseModel() {
        model = new ModelManager(new PlannerMd(), new UserPrefs());
        expectedModel = new ModelManager(new PlannerMd(), new UserPrefs());

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

        // Results found
        AppointmentFilters filter = new AppointmentFiltersBuilder().withStartDate(sampleStartDate)
                .withEndDate(sampleStartDate.plusDays(2)).withPatientKeywords("Alice")
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

        // Results found
        AppointmentFilters filter = new AppointmentFiltersBuilder().withPatientKeywords("Alice").build();
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

        // Results found
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

        // Results found
        AppointmentFilters filter = new AppointmentFiltersBuilder()
                .withStartDate(sampleStartDate.minusDays(1)).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterAppointmentCommand command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(sampleAppointment), model.getFilteredAppointmentList());

        // No results found
        filter = new AppointmentFiltersBuilder(filter)
                .withStartDateTime(sampleStartDate.atTime(20, 30)).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
        filter = new AppointmentFiltersBuilder(filter).withStartDate(sampleStartDate.plusDays(1)).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_endDateSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Results found
        AppointmentFilters filter = new AppointmentFiltersBuilder()
                .withEndDate(sampleStartDate).build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterAppointmentCommand command = new FilterAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(sampleAppointment), model.getFilteredAppointmentList());

        // No results found
        filter = new AppointmentFiltersBuilder(filter)
                .withEndDate(sampleStartDate.minusDays(1)).build();
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

        // same object -> returns true
        assertEquals(firstCommand, firstCommand);

        // same values -> returns true
        FilterAppointmentCommand firstCommandCopy = new FilterAppointmentCommand(firstFilter);
        assertEquals(firstCommandCopy, firstCommand);

        // different types -> returns false
        assertNotEquals(firstCommand, 1);
        assertNotEquals(firstCommand,
                new FilterUpcomingAppointmentCommand(AppointmentFilters.upcomingAppointmentsFilter()));

        // null -> returns false
        assertNotEquals(firstCommand, null);

        // different person -> returns false
        assertNotEquals(secondCommand, firstCommand);
    }
}
