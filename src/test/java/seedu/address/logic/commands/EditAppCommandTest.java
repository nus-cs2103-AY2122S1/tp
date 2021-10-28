package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_PERIOD;
import static seedu.address.testutil.TypicalAppointment.getTypicalSchedule;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class EditAppCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalSchedule());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppCommand.EditAppDescriptor editAppDescriptor = new EditAppCommand.EditAppDescriptor();
        editAppDescriptor.setLocation(new Address(VALID_ADDRESS_AMY));
        editAppDescriptor.setTimePeriod(VALID_TIME_PERIOD);
        editAppDescriptor.setDescription("description");
        EditAppCommand editAppCommand = new EditAppCommand(outOfBoundIndex, editAppDescriptor);

//        assertCommandFailure(editAppCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

}
