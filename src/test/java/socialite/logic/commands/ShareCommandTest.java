package socialite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.GraphicsEnvironment;

import org.junit.jupiter.api.Test;

import socialite.commons.core.Messages;
import socialite.commons.core.index.Index;
import socialite.model.CommandHistory;
import socialite.model.Model;
import socialite.model.ModelManager;
import socialite.model.UserPrefs;
import socialite.model.person.Person;
import socialite.testutil.TypicalIndexes;
import socialite.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the model) and unit tests for {@code ShareCommand}.
 */
public class ShareCommandTest {

    private Model model =
            new ModelManager(TypicalPersons.getTypicalContactList(), new UserPrefs(), new CommandHistory());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        if (GraphicsEnvironment.isHeadless()) {
            return; // for environment without keyboard/mouse
        }
        Person personToShare = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        ShareCommand shareCommand = new ShareCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(ShareCommand.MESSAGE_SHARE_PERSON_SUCCESS, personToShare.toSharingString());

        ModelManager expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new CommandHistory());

        CommandTestUtil.assertCommandSuccess(shareCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        if (GraphicsEnvironment.isHeadless()) {
            return; // for environment without keyboard/mouse
        }
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personToShare = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        ShareCommand shareCommand = new ShareCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(ShareCommand.MESSAGE_SHARE_PERSON_SUCCESS, personToShare.toSharingString());

        Model expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new CommandHistory());
        CommandTestUtil.showPersonAtIndex(expectedModel, TypicalIndexes.INDEX_FIRST_PERSON);

        CommandTestUtil.assertCommandSuccess(shareCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ShareCommand shareCommand = new ShareCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(shareCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredLIst_throwsCommandException() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of the contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getPersonList().size());

        ShareCommand shareCommand = new ShareCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(shareCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void equals() {
        ShareCommand shareFirstCommand = new ShareCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        ShareCommand shareSecondCommand = new ShareCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(shareFirstCommand, shareFirstCommand);

        // same values -> returns true
        ShareCommand shareFirstCommandCopy = new ShareCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(shareFirstCommand, shareFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, shareFirstCommand);

        // null -> returns false
        assertNotEquals(null, shareFirstCommand);

        // different person -> returns false
        assertNotEquals(shareSecondCommand, shareFirstCommand);
    }
}
