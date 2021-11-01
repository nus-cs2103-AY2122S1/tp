package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_1;
import static seedu.address.testutil.TypicalModules.MODULE_2;
import static seedu.address.testutil.TypicalStudents.AMY;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.testutil.TeachingAssistantBuddyBuilder;

public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TeachingAssistantBuddy(), new TeachingAssistantBuddy(modelManager.getBuddy()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAssistantBuddyFilePath(Paths.get("assistant/buddy/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAssistantBuddyFilePath(Paths.get("new/assistant/buddy/file/path"));
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
    public void setAssistantBuddyFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAssistantBuddyFilePath(null));
    }

    @Test
    public void setAssistantBuddyFilePath_validPath_setsAssistantBuddyFilePath() {
        Path path = Paths.get("assistant/buddy/file/path");
        modelManager.setAssistantBuddyFilePath(path);
        assertEquals(path, modelManager.getAssistantBuddyFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAssistantBuddy_returnsFalse() {
        assertFalse(modelManager.hasModule(MODULE_1));
    }

    @Test
    public void hasModule_moduleInAssistantBuddy_returnsTrue() {
        modelManager.addModule(MODULE_1);
        assertTrue(modelManager.hasModule(MODULE_1));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAssistantBuddy_returnsFalse() {
        assertFalse(modelManager.hasStudent(AMY));
    }

    @Test
    public void hasStudent_studentInAssistantBuddy_returnsTrue() {
        modelManager.addStudent(AMY);
        assertTrue(modelManager.hasStudent(AMY));
    }

    @Test
    public void equals() {
        TeachingAssistantBuddy teachingAssistantBuddy = new TeachingAssistantBuddyBuilder()
                .withModule(MODULE_1).withModule(MODULE_2).build();
        TeachingAssistantBuddy differentAssistantBuddy = new TeachingAssistantBuddy();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(teachingAssistantBuddy, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(teachingAssistantBuddy, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different assistantBuddy -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAssistantBuddy, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = AMY.getName().fullName.split("\\s+");
        modelManager.updateFilteredModuleList(unused -> false);
        assertFalse(modelManager.equals(new ModelManager(teachingAssistantBuddy, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAssistantBuddyFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(teachingAssistantBuddy, differentUserPrefs)));
    }
}
