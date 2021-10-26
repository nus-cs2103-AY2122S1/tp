package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAppointment.getTypicalSchedule;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
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
        EditAppCommand editAppCommand = new EditAppCommand(outOfBoundIndex, new Address(VALID_ADDRESS_AMY),
                VALID_DATE, VALID_TIME, "description");

        assertCommandFailure(editAppCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

}
