package seedu.unify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.unify.testutil.Assert.assertThrows;
import static seedu.unify.testutil.TypicalTasks.ALICE;
import static seedu.unify.testutil.TypicalTasks.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.unify.commons.core.GuiSettings;
import seedu.unify.model.task.NameContainsKeywordsPredicate;
import seedu.unify.testutil.UniFyBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new UniFy(), new UniFy(modelManager.getUniFy()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("unify/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/unify/book/file/path"));
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
    public void setUniFyFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUniFyFilePath(null));
    }

    @Test
    public void setUniFyFilePath_validPath_setsUniFyFilePath() {
        Path path = Paths.get("unify/book/file/path");
        modelManager.setUniFyFilePath(path);
        assertEquals(path, modelManager.getUniFyFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInUniFy_returnsFalse() {
        assertFalse(modelManager.hasTask(ALICE));
    }

    @Test
    public void hasTask_taskInUniFy_returnsTrue() {
        modelManager.addTask(ALICE);
        assertTrue(modelManager.hasTask(ALICE));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        UniFy uniFy = new UniFyBuilder().withTask(ALICE).withTask(BENSON).build();
        UniFy differentUniFy = new UniFy();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(uniFy, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(uniFy, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different uniFy -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentUniFy, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().taskName.split("\\s+");
        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(uniFy, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(uniFy, differentUserPrefs)));
    }
}
