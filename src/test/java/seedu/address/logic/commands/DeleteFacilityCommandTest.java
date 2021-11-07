package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFacilityAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPa;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facility.Facility;

/**
 * Contains integration tests (interaction with the Mode) and unit tests for {@code DeleteFacilityCommand}.
 */
public class DeleteFacilityCommandTest {

    private Model model = new ModelManager(getTypicalSportsPa(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Facility facilityToDelete = model.getFilteredFacilityList().get(INDEX_FIRST.getZeroBased());
        DeleteFacilityCommand command = new DeleteFacilityCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFacilityCommand.MESSAGE_DELETE_FACILITY_SUCCESS, facilityToDelete);

        ModelManager expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.deleteFacility(facilityToDelete);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFacilityList().size() + 1);
        DeleteFacilityCommand command = new DeleteFacilityCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_FACILITY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFacilityAtIndex(model, INDEX_FIRST);

        Facility facilityToDelete = model.getFilteredFacilityList().get(INDEX_FIRST.getZeroBased());
        DeleteFacilityCommand command = new DeleteFacilityCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFacilityCommand.MESSAGE_DELETE_FACILITY_SUCCESS, facilityToDelete);

        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.deleteFacility(facilityToDelete);
        showNoFacility(expectedModel);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFacilityAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex still in bounds of SportsPA facility list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSportsPa().getFacilityList().size());

        DeleteFacilityCommand command = new DeleteFacilityCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_FACILITY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteFacilityCommand deleteFacilityFirstCommand = new DeleteFacilityCommand(INDEX_FIRST);
        DeleteFacilityCommand deleteFacilitySecondCommand = new DeleteFacilityCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFacilityFirstCommand.equals(deleteFacilityFirstCommand));

        // same values -> returns true
        DeleteFacilityCommand deleteFacilityFirstCommandCopy = new DeleteFacilityCommand(INDEX_FIRST);
        assertTrue(deleteFacilityFirstCommand.equals(deleteFacilityFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFacilityFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFacilityFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFacilityFirstCommand.equals(deleteFacilitySecondCommand));
    }

    /**
     * Updates {@code model}'s filtered facility list to show no one.
     */
    private void showNoFacility(Model model) {
        model.updateFilteredFacilityList(f -> false);
        assertTrue(model.getFilteredFacilityList().isEmpty());
    }
}
