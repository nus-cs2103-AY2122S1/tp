package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.getMessageFoldersListedOverview;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFolders.CCA;
import static seedu.address.testutil.TypicalFolders.CS2103;
import static seedu.address.testutil.TypicalFolders.TEAM_PROJECT;
import static seedu.address.testutil.TypicalFolders.getTypicalAddressBookWithFolders;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.folder.FolderNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindFoldersCommand}.
 */
public class FindFoldersCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithFolders(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithFolders(), new UserPrefs());

    @Test
    public void equals() {
        FolderNameContainsKeywordsPredicate firstPredicate =
                new FolderNameContainsKeywordsPredicate(Collections.singletonList("first"));
        FolderNameContainsKeywordsPredicate secondPredicate =
                new FolderNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindFoldersCommand findFirstCommand = new FindFoldersCommand(firstPredicate);
        FindFoldersCommand findSecondCommand = new FindFoldersCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindFoldersCommand findFirstCommandCopy = new FindFoldersCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different folder -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFolderFound() {
        String expectedMessage = getMessageFoldersListedOverview(0);
        FolderNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindFoldersCommand command = new FindFoldersCommand(predicate);
        expectedModel.updateFilteredFolderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFolderList());
    }

    @Test
    public void execute_multipleKeywords_multipleFoldersFound() {
        String expectedMessage = getMessageFoldersListedOverview(3);
        FolderNameContainsKeywordsPredicate predicate = preparePredicate("CS2103 Project CCA");
        FindFoldersCommand command = new FindFoldersCommand(predicate);
        expectedModel.updateFilteredFolderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103, TEAM_PROJECT, CCA), model.getFilteredFolderList());
    }

    /**
     * Parses {@code userInput} into a {@code FolderNameContainsKeywordsPredicate}.
     */
    private FolderNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new FolderNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
