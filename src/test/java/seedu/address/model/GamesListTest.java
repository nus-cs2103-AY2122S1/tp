package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.game.Game;
import seedu.address.model.game.exceptions.DuplicateGameException;
import seedu.address.testutil.GameBuilder;

public class GamesListTest {

    private final GamesList gamesList = new GamesList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), gamesList.getGamesList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gamesList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGamesList_replacesData() {
        GamesList newData = getTypicalGamesList();
        gamesList.resetData(newData);
        assertEquals(newData, gamesList);
    }

    @Test
    public void resetData_withDuplicateGames_throwsDuplicateGameException() {
        // Two games with the same identity fields
        Game editedAlice = new GameBuilder(MINECRAFT).build();
        List<Game> newGames = Arrays.asList(MINECRAFT, editedAlice);
        GamesListStub newData = new GamesListStub(newGames);

        assertThrows(DuplicateGameException.class, () -> gamesList.resetData(newData));
    }

    @Test
    public void hasGame_nullGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gamesList.hasGame(null));
    }

    @Test
    public void hasGame_gameNotInGamesList_returnsFalse() {
        assertFalse(gamesList.hasGame(MINECRAFT));
    }

    @Test
    public void hasGame_gameInGamesList_returnsTrue() {
        gamesList.addGame(MINECRAFT);
        assertTrue(gamesList.hasGame(MINECRAFT));
    }

    @Test
    public void hasGame_gameWithSameIdentityFieldsInGamesList_returnsTrue() {
        gamesList.addGame(MINECRAFT);
        Game editedAlice = new GameBuilder(MINECRAFT).build();
        assertTrue(gamesList.hasGame(editedAlice));
    }

    @Test
    public void hasGameId_nullGameId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gamesList.hasGameWithId(null));
    }

    @Test
    public void hasGameId_gameNotInGamesList_returnsFalse() {
        assertFalse(gamesList.hasGameWithId(MINECRAFT.getGameId()));
    }

    @Test
    public void hasGameId_gameInGamesList_returnsTrue() {
        gamesList.addGame(MINECRAFT);
        assertTrue(gamesList.hasGameWithId(MINECRAFT.getGameId()));
    }

    @Test
    public void getGameList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> gamesList.getGamesList().remove(0));
    }

    /**
     * A stub ReadOnlyGamesList whose games list can violate interface constraints.
     */
    private static class GamesListStub implements ReadOnlyGamesList {
        private final ObservableList<Game> games = FXCollections.observableArrayList();

        GamesListStub(Collection<Game> games) {
            this.games.setAll(games);
        }

        @Override
        public ObservableList<Game> getGamesList() {
            return games;
        }
    }

}
