package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.InternshipBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Internship(), new Internship(modelManager.getInternship()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInternshipFilePath(Paths.get("internship/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInternshipFilePath(Paths.get("new/internship/file/path"));
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
    public void setInternshipFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInternshipFilePath(null));
    }

    @Test
    public void setInternshipFilePath_validPath_setsInternshipFilePath() {
        Path path = Paths.get("internship/file/path");
        modelManager.setInternshipFilePath(path);
        assertEquals(path, modelManager.getInternshipFilePath());
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplication(null));
    }

    @Test
    public void hasApplication_applicationNotInInternship_returnsFalse() {
        assertFalse(modelManager.hasApplication(AMAZON));
    }

    @Test
    public void hasApplication_applicationInInternship_returnsTrue() {
        modelManager.addApplication(AMAZON);
        assertTrue(modelManager.hasApplication(AMAZON));
    }

    @Test
    public void getFilteredApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicationList().remove(0));
    }

    @Test
    public void equals() {
        Internship internship = new InternshipBuilder().withApplication(AMAZON).withApplication(BYTEDANCE).build();
        Internship differentInternship = new Internship();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(internship, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(internship, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different Internship -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInternship, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = AMAZON.getCompany().fullCompanyName.split("\\s+");
        modelManager.updateFilteredApplicationList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(internship, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInternshipFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(internship, differentUserPrefs)));
    }
}
