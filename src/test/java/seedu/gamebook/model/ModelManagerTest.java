package seedu.gamebook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.gamebook.model.Model.PREDICATE_SHOW_ALL_GAME_ENTRIES;
import static seedu.gamebook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.gamebook.commons.core.GuiSettings;
import seedu.gamebook.model.gameentry.DatePlayed;
import seedu.gamebook.model.gameentry.Duration;
import seedu.gamebook.model.gameentry.EndAmount;
import seedu.gamebook.model.gameentry.GameEntry;
import seedu.gamebook.model.gameentry.GameType;
import seedu.gamebook.model.gameentry.Location;
import seedu.gamebook.model.gameentry.StartAmount;

public class ModelManagerTest {

    private static final GameEntry POKER = new GameEntry(new GameType("Poker"), new StartAmount(100.),
            new EndAmount(80.), DatePlayed.empty(), new Duration(10), new Location("Marina bay sands"), new HashSet<>()
    );
    private static final GameEntry ROULETTE = new GameEntry(new GameType("Roulette"), new StartAmount(100.),
            new EndAmount(80.12), DatePlayed.empty(), new Duration(10), new Location("Marina Bay Sands"),
            new HashSet<>());
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new GameBook(), new GameBook(modelManager.getGameBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGameBookFilePath(Paths.get("gamebook/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setGameBookFilePath(Paths.get("new/gamebook/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setGameBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGameBookFilePath(null));
    }

    @Test
    public void setGameBookFilePath_validPath_setsGameBookFilePath() {
        Path path = Paths.get("gamebook/book/file/path");
        modelManager.setGameBookFilePath(path);
        assertEquals(path, modelManager.getGameBookFilePath());
    }

    @Test
    public void hasGameEntry_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasGameEntry(null));
    }

    @Test
    public void hasGameEntry_gameEntryNotInGameBook_returnsFalse() {
        assertFalse(modelManager.hasGameEntry(POKER));
    }

    @Test
    public void hasGameEntry_gameEntryInBook_returnsTrue() {
        modelManager.addGameEntry(POKER);
        assertTrue(modelManager.hasGameEntry(POKER));
    }

    @Test
    public void getFilteredGameEntryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGameEntryList().remove(0));
    }

    @Test
    public void equals() {
        GameBook gameBook = new GameBook();
        gameBook.addGameEntry(POKER);
        gameBook.addGameEntry(ROULETTE);
        GameBook differentGameBook = new GameBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(gameBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(gameBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentGameBook, userPrefs)));

        // different filteredList -> returns false
        modelManager.updateFilteredGameEntryList(gameEntry -> gameEntry.getGameType().toString().equals("Poker"));
        assertFalse(modelManager.equals(new ModelManager(gameBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredGameEntryList(PREDICATE_SHOW_ALL_GAME_ENTRIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setGameBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(gameBook, differentUserPrefs)));
    }
}
