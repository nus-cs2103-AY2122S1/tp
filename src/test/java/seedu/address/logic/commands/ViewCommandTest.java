package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showParticipantAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PARTICIPANT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PARTICIPANT;
import static seedu.address.testutil.TypicalParticipants.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.participant.Participant;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Participant participantToView = model.getFilteredParticipantList()
                .get(INDEX_FIRST_PARTICIPANT.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PARTICIPANT);
        String expectedMessage = participantToView.toString();
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredParticipantList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showParticipantAtIndex(model, INDEX_FIRST_PARTICIPANT);

        Participant participantToView = model.getFilteredParticipantList()
                .get(INDEX_FIRST_PARTICIPANT.getZeroBased());

        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PARTICIPANT);

        String expectedMessage = participantToView.toString();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showParticipantAtIndex(expectedModel, INDEX_FIRST_PARTICIPANT);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showParticipantAtIndex(model, INDEX_FIRST_PARTICIPANT);

        Index outOfBoundIndex = INDEX_SECOND_PARTICIPANT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getParticipantList().size());
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_PARTICIPANT);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_PARTICIPANT);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_PARTICIPANT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different participant -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
