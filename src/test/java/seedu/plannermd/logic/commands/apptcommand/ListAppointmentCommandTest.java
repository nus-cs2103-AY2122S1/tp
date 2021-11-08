package seedu.plannermd.logic.commands.apptcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_CARL;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.AppointmentDate;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;

public class ListAppointmentCommandTest {

    private Model model;
    private Model expectedModel;
    private final Appointment todayAppointment = new AppointmentBuilder().withPatient(ALICE)
            .withDoctor(DR_CARL)
            .withDate(LocalDate.now().format(AppointmentDate.DATE_FORMATTER))
            .withSession("20:00", 10).build();

    private final Appointment nextDayAppointment = new AppointmentBuilder(todayAppointment)
            .withDate(LocalDate.now().plusDays(1).format(AppointmentDate.DATE_FORMATTER)).build();

    private final Appointment yesterdayAppointment = new AppointmentBuilder(todayAppointment)
            .withDate(LocalDate.now().minusDays(1).format(AppointmentDate.DATE_FORMATTER)).build();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new PlannerMd(), new UserPrefs());
        expectedModel = new ModelManager(new PlannerMd(), new UserPrefs());

        model.addAppointment(nextDayAppointment);
        model.addAppointment(yesterdayAppointment);

        expectedModel.addAppointment(nextDayAppointment);
        expectedModel.addAppointment(yesterdayAppointment);
    }

    @Test
    public void execute_hasTodayAppointment_successful() {
        model.addAppointment(todayAppointment);
        expectedModel.addAppointment(todayAppointment);

        expectedModel.updateFilteredAppointmentList(AppointmentFilters.todayAppointmentFilter().collectAllFilters());
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Collections.singletonList(todayAppointment), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_doesNotHasTodayAppointment_successful() {
        expectedModel.updateFilteredAppointmentList(AppointmentFilters.todayAppointmentFilter().collectAllFilters());
        assertCommandSuccess(new ListAppointmentCommand(), model,
                ListAppointmentCommand.MESSAGE_NO_APPOINTMENTS_TODAY, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

}
