package seedu.intern.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static seedu.intern.testutil.Assert.assertThrows;
import static seedu.intern.testutil.TypicalApplicants.ALICE;
import static seedu.intern.testutil.TypicalApplicants.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.core.GuiSettings;
import seedu.intern.model.applicant.NameContainsKeywordsPredicate;
import seedu.intern.testutil.InternWatcherBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new InternWatcher(), new InternWatcher(modelManager.getInternWatcher()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternWatcherFilePath(Paths.get("intern/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternWatcherFilePath(Paths.get("new/intern/book/file/path"));
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
    public void setInternWatcherFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternWatcherFilePath(null));
    }

    @Test
    public void setInternWatcherFilePath_validPath_setsInternWatcherFilePath() {
        Path path = Paths.get("intern/book/file/path");
        modelManager.setInternWatcherFilePath(path);
        assertEquals(path, modelManager.getInternWatcherFilePath());
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplicant(null));
    }

    @Test
    public void hasApplicant_applicantNotInInternWatcher_returnsFalse() {
        assertFalse(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void hasApplicant_applicantInInternWatcher_returnsTrue() {
        modelManager.addApplicant(ALICE);
        assertTrue(modelManager.hasApplicant(ALICE));
    }

    @Test
    public void getFilteredApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicantList().remove(0));
    }

    @Test
    public void equals() {
        InternWatcher internWatcher = new InternWatcherBuilder().withApplicant(ALICE).withApplicant(BENSON).build();
        InternWatcher differentInternWatcher = new InternWatcher();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internWatcher, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(internWatcher, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different internWatcher -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternWatcher, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(internWatcher, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternWatcherFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(internWatcher, differentUserPrefs)));
    }
}
