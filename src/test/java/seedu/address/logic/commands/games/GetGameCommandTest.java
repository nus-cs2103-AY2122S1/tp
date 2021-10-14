package seedu.address.logic.commands.games;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.VALORANT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.BitSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.game.Game;
import seedu.address.testutil.GameBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code GetGameCommand}.
 */
public class GetGameCommandTest {

    private final Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        // command can get game by game id
        Game gameToGet = model.getFilteredGamesList().get(INDEX_FIRST_ITEM.getZeroBased());
        GetGameCommand getCommand = new GetGameCommand(gameToGet.getGameId());

        String expectedMessage = String.format(GetGameCommand.MESSAGE_GAME_FULL_INFORMATION,
                gameToGet.getGameId());
        ModelManager expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandType.GAME_GET, gameToGet);

        assertCommandSuccess(getCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonExistentIdUnfilteredList_throwsCommandException() {
        // command fails if game id does not exist in unfiltered list
        GetGameCommand getGameCommand = new GetGameCommand(CSGO.getGameId());
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_GAME_ID, CSGO.getGameId());

        assertCommandFailure(getGameCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        // can get game by gameId even if not in the currently filtered list
        Game gameToGet = model.getFilteredGamesList().get(INDEX_FIRST_ITEM.getZeroBased());
        GetGameCommand getCommand = new GetGameCommand(gameToGet.getGameId());
        showNoGame(model);

        String expectedMessage = String.format(GetGameCommand.MESSAGE_GAME_FULL_INFORMATION, gameToGet.getGameId());
        Model expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(), new UserPrefs());
        // show no one
        showNoGame(expectedModel);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandType.GAME_GET, gameToGet);

        assertCommandSuccess(getCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        Game gameMinecraft = new GameBuilder(MINECRAFT).build();
        Game gameValorant = new GameBuilder(VALORANT).build();
        GetGameCommand getFirstCommand = new GetGameCommand(gameMinecraft.getGameId());
        GetGameCommand getSecondCommand = new GetGameCommand(gameValorant.getGameId());

        // same object -> returns true
        assertTrue(getFirstCommand.equals(getFirstCommand));

        // same values -> returns true
        GetGameCommand getFirstCommandCopy = new GetGameCommand(gameMinecraft.getGameId());
        assertTrue(getFirstCommand.equals(getFirstCommandCopy));

        // different types -> returns false
        assertFalse(getFirstCommand.equals(new BitSet()));

        // null -> returns false
        assertFalse(getFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(getFirstCommand.equals(getSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered game list to show no one.
     */
    private void showNoGame(Model model) {
        model.updateFilteredGamesList(p -> false);

        assertTrue(model.getFilteredGamesList().isEmpty());
    }
}
