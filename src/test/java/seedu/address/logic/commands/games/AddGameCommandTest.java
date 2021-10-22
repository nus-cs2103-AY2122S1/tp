package seedu.address.logic.commands.games;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.VALORANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GamesList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.testutil.GameBuilder;
import seedu.address.testutil.ModelStub;

public class AddGameCommandTest {
    @Test
    public void constructor_nullGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddGameCommand(null));
    }

    @Test
    public void execute_gameAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingGameAdded modelStub = new ModelStubAcceptingGameAdded();
        Game expectedGameValorant = new GameBuilder(VALORANT).build();

        CommandResult commandResult = new AddGameCommand(expectedGameValorant).execute(modelStub);
        assertEquals(String.format(AddGameCommand.MESSAGE_SUCCESS_ADD_GAME, expectedGameValorant),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(expectedGameValorant), modelStub.gamesAdded);

        Game validGameMinecraft = new GameBuilder(MINECRAFT).build();
        CommandResult commandResultAddAnother = new AddGameCommand(validGameMinecraft).execute(modelStub);
        assertEquals(String.format(AddGameCommand.MESSAGE_SUCCESS_ADD_GAME, validGameMinecraft),
                commandResultAddAnother.getFeedbackToUser());
        assertEquals(Arrays.asList(expectedGameValorant, validGameMinecraft), modelStub.gamesAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Game expectedGameCsgo = new GameBuilder(CSGO).build();
        AddGameCommand addFriendCommand = new AddGameCommand(expectedGameCsgo);
        ModelStub modelStub = new ModelStubWithGame(expectedGameCsgo);

        assertThrows(CommandException.class, AddGameCommand.MESSAGE_DUPLICATE_GAME, () ->
                addFriendCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Game expectedGameCsgo = new GameBuilder(CSGO).build();
        Game expectedGameValorant = new GameBuilder(VALORANT).build();

        AddGameCommand addCsgoCommand = new AddGameCommand(expectedGameCsgo);
        AddGameCommand addValorantCommand = new AddGameCommand(expectedGameValorant);

        // same object -> returns true
        assertEquals(addCsgoCommand, addCsgoCommand);

        // same values -> returns true
        AddGameCommand addCsgoCommandCopy = new AddGameCommand(CSGO);
        assertEquals(addCsgoCommandCopy, addCsgoCommand);

        // different types -> returns false
        assertNotEquals(1, addCsgoCommand);

        // null -> returns false
        assertNotEquals(null, addCsgoCommand);

        // different game -> returns false
        assertNotEquals(addValorantCommand, addCsgoCommand);
    }

    /**
     * A Model stub that contains a single game.
     */
    private class ModelStubWithGame extends ModelStub {
        private final Game game;

        ModelStubWithGame(Game game) {
            requireNonNull(game);
            this.game = game;
        }

        @Override
        public boolean hasGame(Game game) {
            requireNonNull(game);
            return this.game.equals(game);
        }

        @Override
        public boolean hasGameWithId(GameId idToFind) {
            return this.game.getGameId().equals(idToFind);
        }
    }

    /**
     * A Model stub that always accept the game being added.
     */
    private class ModelStubAcceptingGameAdded extends ModelStub {
        final ArrayList<Game> gamesAdded = new ArrayList<>();

        @Override
        public boolean hasGame(Game game) {
            requireNonNull(game);
            return gamesAdded.stream().anyMatch(game::equals);
        }

        @Override
        public boolean hasGameWithId(GameId idToFind) {
            return gamesAdded.stream().anyMatch(game -> game.getGameId().equals(idToFind));
        }

        @Override
        public void addGame(Game game) {
            requireNonNull(game);
            gamesAdded.add(game);
        }

        @Override
        public ReadOnlyGamesList getGamesList() {
            return new GamesList();
        }
    }
}
