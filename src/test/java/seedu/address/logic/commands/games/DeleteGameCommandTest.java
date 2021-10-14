package seedu.address.logic.commands.games;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGameAtIndex;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.VALORANT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.game.Game;
import seedu.address.testutil.GameBuilder;

public class DeleteGameCommandTest {
    private final Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        // command can delete game by game id
        Game gameToDelete = model.getFilteredGamesList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteGameCommand deleteCommand = new DeleteGameCommand(gameToDelete.getGameId());

        String expectedMessage = String.format(DeleteGameCommand.MESSAGE_DELETE_GAME_SUCCESS,
                gameToDelete.getGameId());

        ModelManager expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(), new UserPrefs());
        expectedModel.deleteGame(gameToDelete.getGameId());
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentIdUnfilteredList_throwsCommandException() {
        // command fails if game id does not exist in unfiltered list
        DeleteGameCommand deleteGameCommand = new DeleteGameCommand(CSGO.getGameId());
        assertCommandFailure(deleteGameCommand, model, Messages.MESSAGE_NONEXISTENT_GAME_ID);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        // can delete game by gameid even if not in the currently filtered list
        showGameAtIndex(model, INDEX_FIRST_PERSON);
        Game gameToDelete = model.getFilteredGamesList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteGameCommand deleteCommand = new DeleteGameCommand(gameToDelete.getGameId());
        String expectedMessage = String.format(DeleteGameCommand.MESSAGE_DELETE_GAME_SUCCESS,
                gameToDelete.getGameId());
        Model expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(), new UserPrefs());

        // show no one
        showNoGame(expectedModel);

        // should still be able to delete
        expectedModel.deleteGame(gameToDelete.getGameId());
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Game gameCsgo = new GameBuilder(CSGO).build();
        Game gameValorant = new GameBuilder(VALORANT).build();
        DeleteGameCommand deleteFirstCommand = new DeleteGameCommand(gameCsgo.getGameId());
        DeleteGameCommand deleteSecondCommand = new DeleteGameCommand(gameValorant.getGameId());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteGameCommand deleteFirstCommandCopy = new DeleteGameCommand(gameCsgo.getGameId());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals("123"));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGame(Model model) {
        model.updateFilteredGamesList(g -> false);

        assertTrue(model.getFilteredGamesList().isEmpty());
    }

}
