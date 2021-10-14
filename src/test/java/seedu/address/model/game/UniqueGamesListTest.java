package seedu.address.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.VALORANT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.game.exceptions.DuplicateGameException;
import seedu.address.model.game.exceptions.GameNotFoundException;

public class UniqueGamesListTest {

    private final UniqueGamesList uniqueGamesList = new UniqueGamesList();

    @Test
    public void contains_nullGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGamesList.contains(null));
    }

    @Test
    public void contains_gameNotInList_returnsFalse() {
        assertFalse(uniqueGamesList.contains(MINECRAFT));
    }

    @Test
    public void contains_gameInList_returnsTrue() {
        uniqueGamesList.add(MINECRAFT);
        assertTrue(uniqueGamesList.contains(MINECRAFT));
    }

    @Test
    public void add_nullGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGamesList.add(null));
    }

    @Test
    public void add_duplicateGame_throwsDuplicateGameException() {
        uniqueGamesList.add(MINECRAFT);
        assertThrows(DuplicateGameException.class, () -> uniqueGamesList.add(MINECRAFT));
    }

    @Test
    public void setGame_nullTargetGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGamesList.setGame(null, MINECRAFT));
    }

    @Test
    public void setGame_nullEditedGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGamesList.setGame(MINECRAFT, null));
    }

    @Test
    public void setGame_targetGameNotInList_throwsGameNotFoundException() {
        assertThrows(GameNotFoundException.class, () -> uniqueGamesList.setGame(MINECRAFT, MINECRAFT));
    }

    @Test
    public void setGame_editedGameIsSameGame_success() {
        uniqueGamesList.add(MINECRAFT);
        uniqueGamesList.setGame(MINECRAFT, MINECRAFT);
        UniqueGamesList expectedUniqueGamesList = new UniqueGamesList();
        expectedUniqueGamesList.add(MINECRAFT);
        assertEquals(expectedUniqueGamesList, uniqueGamesList);
    }

    @Test
    public void setGame_editedGameHasDifferentIdentity_success() {
        uniqueGamesList.add(MINECRAFT);
        uniqueGamesList.setGame(MINECRAFT, VALORANT);
        UniqueGamesList expectedUniqueGamesList = new UniqueGamesList();
        expectedUniqueGamesList.add(VALORANT);
        assertEquals(expectedUniqueGamesList, uniqueGamesList);
    }

    @Test
    public void setGame_editedGameHasNonUniqueIdentity_throwsDuplicateGameException() {
        uniqueGamesList.add(MINECRAFT);
        uniqueGamesList.add(VALORANT);
        assertThrows(DuplicateGameException.class, () -> uniqueGamesList.setGame(MINECRAFT, VALORANT));
    }

    @Test
    public void remove_nullGame_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGamesList.remove(null));
    }

    @Test
    public void remove_gameDoesNotExist_throwsGameNotFoundException() {
        assertThrows(GameNotFoundException.class, () -> uniqueGamesList.remove(MINECRAFT));
    }

    @Test
    public void remove_existingGame_removesGame() {
        uniqueGamesList.add(MINECRAFT);
        uniqueGamesList.remove(MINECRAFT);
        UniqueGamesList expectedUniqueGamesList = new UniqueGamesList();
        assertEquals(expectedUniqueGamesList, uniqueGamesList);
    }

    @Test
    public void setGames_nullUniqueGameList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGamesList.setGames((UniqueGamesList) null));
    }

    @Test
    public void setGames_uniqueGameList_replacesOwnListWithProvidedUniqueGameList() {
        uniqueGamesList.add(MINECRAFT);
        UniqueGamesList expectedUniqueGamesList = new UniqueGamesList();
        expectedUniqueGamesList.add(VALORANT);
        uniqueGamesList.setGames(expectedUniqueGamesList);
        assertEquals(expectedUniqueGamesList, uniqueGamesList);
    }

    @Test
    public void setGames_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGamesList.setGames((List<Game>) null));
    }

    @Test
    public void setGames_list_replacesOwnListWithProvidedList() {
        uniqueGamesList.add(MINECRAFT);
        List<Game> gameList = Collections.singletonList(VALORANT);
        uniqueGamesList.setGames(gameList);
        UniqueGamesList expectedUniqueGamesList = new UniqueGamesList();
        expectedUniqueGamesList.add(VALORANT);
        assertEquals(expectedUniqueGamesList, uniqueGamesList);
    }

    @Test
    public void setGames_listWithDuplicateGames_throwsDuplicateGameException() {
        List<Game> listWithDuplicateGames = Arrays.asList(MINECRAFT, MINECRAFT);
        assertThrows(DuplicateGameException.class, () -> uniqueGamesList.setGames(listWithDuplicateGames));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueGamesList.asUnmodifiableObservableList().remove(0));
    }
}
