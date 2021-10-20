package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.plannermd.commons.core.Messages.MESSAGE_DOCTORS_LISTED_OVERVIEW;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import org.junit.jupiter.api.Test;
import seedu.plannermd.logic.commands.apptcommand.AppointmentFilters;
import seedu.plannermd.logic.commands.apptcommand.FilterAppointmentCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterAppointmentCommand.
 */
public class FilterAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    public void execute_noParameter_allAppointmentsMatch() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size());
        FilterAppointmentCommand command = new FilterAppointmentCommand(AppointmentFilters.allAppointmentsFilter());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}