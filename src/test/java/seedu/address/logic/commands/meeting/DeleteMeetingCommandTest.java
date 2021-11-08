package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBookObjects.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMeetingCommand}.
 */
public class DeleteMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexList_success() {
        Meeting meetingToDelete = model.getMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteMeetingCommand deleteCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getMeetingList().size() + 1);
        DeleteMeetingCommand deleteCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMeetingCommand deleteFirstCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        DeleteMeetingCommand deleteSecondCommand = new DeleteMeetingCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMeetingCommand deleteFirstCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
