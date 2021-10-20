package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class MarkAttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndices_success() {
        MarkAttendanceCommand command = new MarkAttendanceCommand(Arrays.asList(INDEX_FIRST, INDEX_SECOND));

        String expectedMessage = MarkAttendanceCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markMembersAttendance(Arrays.asList(INDEX_FIRST, INDEX_SECOND));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndices_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkAttendanceCommand command = new MarkAttendanceCommand(Arrays.asList(outOfBoundsIndex));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkAttendanceCommand markFirstCommand = new MarkAttendanceCommand(Arrays.asList(INDEX_FIRST, INDEX_THIRD));
        MarkAttendanceCommand markSecondCommand = new MarkAttendanceCommand(Arrays.asList(INDEX_SECOND));

        assertTrue(markFirstCommand.equals(markFirstCommand));

        MarkAttendanceCommand markFirstCommandCopy = new MarkAttendanceCommand(Arrays.asList(INDEX_FIRST,
                INDEX_THIRD));
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        assertFalse(markFirstCommand.equals(markSecondCommand));

        assertFalse(markFirstCommand.equals(null));

        assertFalse(markFirstCommand.equals("1"));
    }
}
