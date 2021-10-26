package seedu.tracker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalModules.CS2101;
import static seedu.tracker.testutil.TypicalModules.CS2103T;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.tracker.commons.core.GuiSettings;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;
import seedu.tracker.testutil.ModuleTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new UserInfo(), modelManager.getUserInfo());
        assertEquals(new AcademicCalendar(new AcademicYear(1), new Semester(1)), modelManager.getCurrentSemester());
        assertEquals(new Mc(160), modelManager.getMcGoal());
        assertEquals(new ModuleTracker(), new ModuleTracker(modelManager.getModuleTracker()));
    }

    @Test
    public void setUserInfo_nulUserInfo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserInfo(null));
    }

    @Test
    public void setUserInfo_validUserInfo_copiesUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setMcGoal(new Mc(320));
        userInfo.setCurrentSemester(new AcademicCalendar(new AcademicYear(5), new Semester(2)));
        modelManager.setUserInfo(userInfo);
        assertEquals(userInfo, modelManager.getUserInfo());

        // Modifying userInfo should not modify modelManager's userInfo
        UserInfo oldUserInfo = new UserInfo(userInfo);
        userInfo.setMcGoal(new Mc(1));
        assertEquals(oldUserInfo, modelManager.getUserInfo());
    }

    @Test
    public void setCurrentSemester_nullSemester_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCurrentSemester(null));
    }

    @Test
    public void setCurrentSemester_validSemester_success() {
        AcademicCalendar currentSemester = new AcademicCalendar(new AcademicYear(3), new Semester(3));
        modelManager.setCurrentSemester(currentSemester);
        assertEquals(currentSemester, modelManager.getCurrentSemester());
    }

    @Test
    public void setMcGoal_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMcGoal(null));
    }

    @Test
    public void setMcGoal_validMc_success() {
        Mc mcGoal = new Mc(160);
        modelManager.setMcGoal(mcGoal);
        assertEquals(mcGoal, modelManager.getMcGoal());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setModuleTrackerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setModuleTrackerFilePath(Paths.get("new/address/book/file/path"));
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
    public void setModuleTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setModuleTrackerFilePath(null));
    }

    @Test
    public void setModuleTrackerFilePath_validPath_setsModuleTrackerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setModuleTrackerFilePath(path);
        assertEquals(path, modelManager.getModuleTrackerFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInModuleTracker_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2103T));
    }

    @Test
    public void hasModule_moduleInModuleTracker_returnsTrue() {
        modelManager.addModule(CS2103T);
        assertTrue(modelManager.hasModule(CS2103T));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void equals() {
        ModuleTracker moduleTracker = new ModuleTrackerBuilder().withModule(CS2103T).withModule(CS2101).build();
        ModuleTracker differentModuleTracker = new ModuleTracker();
        UserPrefs userPrefs = new UserPrefs();
        UserInfo userInfo = new UserInfo();

        // same values -> returns true
        modelManager = new ModelManager(moduleTracker, userPrefs, userInfo);
        ModelManager modelManagerCopy = new ModelManager(moduleTracker, userPrefs, userInfo);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different moduleTrackers -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentModuleTracker, userPrefs, userInfo)));

        // different filteredList -> returns false
        /*String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredModuleList(new ModuleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(moduleTracker, userPrefs)));*/

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setModuleTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(moduleTracker, differentUserPrefs, userInfo)));
    }
}
