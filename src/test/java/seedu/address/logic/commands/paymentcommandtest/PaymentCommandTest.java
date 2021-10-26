package seedu.address.logic.commands.paymentcommandtest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.paymentcommand.PaymentCommand.MESSAGE_VIEW_TUTEE_PAYMENT_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TUTEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TUTEE;
import static seedu.address.testutil.TypicalTutees.getTypicalTrackO;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.paymentcommand.PaymentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exceptions.ScheduleClashException;
import seedu.address.model.tutee.Tutee;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 *  * {@code PaymentCommand}.
 */
public class PaymentCommandTest {

    private Model model;

    @Test
    public void execute_validIndexUnfilteredList_success() throws ScheduleClashException {
        model = new ModelManager(getTypicalTrackO(), new UserPrefs());
        Tutee retrievedTutee = model.getFilteredTuteeList().get(INDEX_FIRST_TUTEE.getZeroBased());
        PaymentCommand paymentCommand = new PaymentCommand(INDEX_FIRST_TUTEE);

        String expectedMessage = String.format(MESSAGE_VIEW_TUTEE_PAYMENT_SUCCESS, retrievedTutee.getName(),
                retrievedTutee.getPayment());

        ModelManager expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());

        assertCommandSuccess(paymentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() throws ScheduleClashException {
        model = new ModelManager(getTypicalTrackO(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTuteeList().size() + 1);
        PaymentCommand paymentCommand = new PaymentCommand(outOfBoundIndex);

        assertCommandFailure(paymentCommand, model, Messages.MESSAGE_INVALID_TUTEE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PaymentCommand getFirstCommand = new PaymentCommand(INDEX_FIRST_TUTEE);
        PaymentCommand getSecondCommand = new PaymentCommand(INDEX_SECOND_TUTEE);

        // same object -> returns true
        assertTrue(getFirstCommand.equals(getFirstCommand));

        // same values -> returns true
        PaymentCommand getFirstCommandCopy = new PaymentCommand(INDEX_FIRST_TUTEE);
        assertTrue(getFirstCommand.equals(getFirstCommandCopy));

        // different types -> returns false
        assertFalse(getFirstCommand.equals(1));

        // null -> returns false
        assertFalse(getFirstCommand.equals(null));

        // different tutee -> returns false
        assertFalse(getFirstCommand.equals(getSecondCommand));
    }
}
