package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.testutil.TypicalGroups.REPONAME1;
import static seedu.address.testutil.TypicalGroups.REPONAME2;
import static seedu.address.testutil.TypicalGroups.YEAR1;
import static seedu.address.testutil.TypicalGroups.YEAR2;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GROUP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class AddGithubGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    // Pass
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddGithubGroupCommand addGithubGroupCommand = new AddGithubGroupCommand(outOfBoundIndex, YEAR1, REPONAME1);

        assertCommandFailure(addGithubGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST_GROUP);

        Index outOfBoundIndex = INDEX_SECOND_GROUP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        AddGithubGroupCommand addGithubGroupCommand = new AddGithubGroupCommand(outOfBoundIndex, YEAR1, REPONAME1);

        assertCommandFailure(addGithubGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void equals() {
        AddGithubGroupCommand addGithubGroupCommand = new AddGithubGroupCommand(INDEX_FIRST_GROUP, YEAR1, REPONAME1);
        AddGithubGroupCommand addSecondCommand = new AddGithubGroupCommand(INDEX_SECOND_GROUP, YEAR2, REPONAME2);

        // same object -> returns true
        assertTrue(addGithubGroupCommand.equals(addGithubGroupCommand));

        // same values -> returns true
        AddGithubGroupCommand addFirstCommandCopy = new AddGithubGroupCommand(INDEX_FIRST_GROUP, YEAR1, REPONAME1);
        assertTrue(addGithubGroupCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(addGithubGroupCommand.equals(1));

        // null -> returns false
        assertFalse(addGithubGroupCommand.equals(null));

        // different group -> returns false
        assertFalse(addGithubGroupCommand.equals(addSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGroup(Model model) {
        model.updateFilteredGroupList(p -> false);

        assertTrue(model.getFilteredGroupList().isEmpty());
    }
}
