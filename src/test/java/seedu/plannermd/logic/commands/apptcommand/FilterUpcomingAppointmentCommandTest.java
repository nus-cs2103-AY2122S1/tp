package seedu.plannermd.logic.commands.apptcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.plannermd.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_CARL;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.appointment.AppointmentFiltersBuilder;

public class FilterUpcomingAppointmentCommandTest {

    private final Appointment upcomingAppointment = new AppointmentBuilder().withPatient(ALICE)
            .withDoctor(DR_CARL)
            .withDate(LocalDate.now().plusDays(1).format(AppointmentDate.DATE_FORMATTER))
            .withSession("18:00", 10).build();

    private final Appointment oldAppointment = new AppointmentBuilder(upcomingAppointment)
            .withDate(LocalDate.now().minusDays(5).format(AppointmentDate.DATE_FORMATTER)).build();

    private Model model;
    private Model expectedModel;

    private void reinitialiseModel() {
        model = new ModelManager(new PlannerMd(), new UserPrefs());
        expectedModel = new ModelManager(new PlannerMd(), new UserPrefs());
        model.addAppointment(upcomingAppointment);
        expectedModel.addAppointment(upcomingAppointment);
        model.addAppointment(oldAppointment);
        expectedModel.addAppointment(oldAppointment);
    }

    @Test
    public void execute_noParameterSpecified_allUpcomingAppointmentsMatch() {
        reinitialiseModel();
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        AppointmentFilters filters = new AppointmentFiltersBuilder().withUpcoming().build();
        FilterUpcomingAppointmentCommand command = new FilterUpcomingAppointmentCommand(filters);
        expectedModel.updateFilteredAppointmentList(filters.collectAllFilters());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(upcomingAppointment), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_allParameterSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Results found
        AppointmentFilters filter = new AppointmentFiltersBuilder().withUpcoming().withPatientKeywords("Alice")
                .withDoctorKeywords("Carl").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterUpcomingAppointmentCommand command = new FilterUpcomingAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(upcomingAppointment), model.getFilteredAppointmentList());

        // No results found (starting dates do not match)
        filter = new AppointmentFiltersBuilder(filter).withPatientKeywords("John").withDoctorKeywords("Pete").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterUpcomingAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_patientKeywordSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Results found
        AppointmentFilters filter = new AppointmentFiltersBuilder().withUpcoming()
                .withPatientKeywords("Alice").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterUpcomingAppointmentCommand command = new FilterUpcomingAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(upcomingAppointment), model.getFilteredAppointmentList());

        // No results found
        filter = new AppointmentFiltersBuilder(filter).withPatientKeywords("Bruce").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterUpcomingAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_doctorKeywordSpecified_successfullyFiltered() {
        reinitialiseModel();

        // Results found
        AppointmentFilters filter = new AppointmentFiltersBuilder().withUpcoming().withDoctorKeywords("Carl").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        FilterUpcomingAppointmentCommand command = new FilterUpcomingAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(upcomingAppointment), model.getFilteredAppointmentList());

        // No results found
        filter = new AppointmentFiltersBuilder(filter).withDoctorKeywords("Karl").build();
        expectedModel.updateFilteredAppointmentList(filter.collectAllFilters());
        expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        command = new FilterUpcomingAppointmentCommand(filter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void equals() {
        AppointmentFilters firstFilter = new AppointmentFiltersBuilder().withUpcoming()
                .withPatientKeywords("Alice").build();
        AppointmentFilters secondFilter = new AppointmentFiltersBuilder().withUpcoming()
                .withPatientKeywords("Ali").build();

        FilterUpcomingAppointmentCommand firstCommand = new FilterUpcomingAppointmentCommand(firstFilter);
        FilterUpcomingAppointmentCommand secondCommand = new FilterUpcomingAppointmentCommand(secondFilter);

        // same object -> equals
        assertEquals(firstCommand, firstCommand);

        // same values -> equals
        FilterUpcomingAppointmentCommand firstCommandCopy = new FilterUpcomingAppointmentCommand(firstFilter);
        assertEquals(firstCommandCopy, firstCommand);

        // different types -> not equals
        assertNotEquals(firstCommand, 1);
        assertNotEquals(firstCommand, new FilterAppointmentCommand(AppointmentFilters.allAppointmentsFilter()));

        // null -> not equals
        assertNotEquals(firstCommand, null);

        // different person -> not equals
        assertNotEquals(secondCommand, firstCommand);
    }
}
