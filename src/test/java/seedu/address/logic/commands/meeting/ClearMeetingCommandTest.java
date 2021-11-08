package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBookObjects.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class ClearMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_emptyMeetingList_success() {
        Model model = new ModelManager();

        assertCommandFailure(new ClearMeetingCommand(), model, ClearMeetingCommand.NOTHING_TO_CLEAR);
    }

    @Test
    public void execute_nonEmptyMeetingList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.clearMeetings();

        assertCommandSuccess(new ClearMeetingCommand(), model, ClearMeetingCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
