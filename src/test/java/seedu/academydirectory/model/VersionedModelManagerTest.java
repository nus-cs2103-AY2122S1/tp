package seedu.academydirectory.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.academydirectory.testutil.Assert.assertThrows;
import static seedu.academydirectory.testutil.TypicalStudents.ALICE;
import static seedu.academydirectory.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.GuiSettings;
import seedu.academydirectory.logic.AdditionalViewType;
import seedu.academydirectory.model.student.NameContainsKeywordsPredicate;
import seedu.academydirectory.testutil.AcademyDirectoryBuilder;

public class VersionedModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AcademyDirectory(), new AcademyDirectory(modelManager.getAcademyDirectory()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAcademyDirectoryFilePath(Paths.get("academy/directory/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAcademyDirectoryFilePath(Paths.get("new/academy/directory/file/path"));
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
    public void setAcademyDirectoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAcademyDirectoryFilePath(null));
    }

    @Test
    public void setAcademyDirectoryFilePath_validPath_setsAcademyDirectoryFilePath() {
        Path path = Paths.get("academy/directory/file/path");
        modelManager.setAcademyDirectoryFilePath(path);
        assertEquals(path, modelManager.getAcademyDirectoryFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAcademyDirectory_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAcademyDirectory_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void setAndGetAdditionalViewModelTest() {
        AdditionalViewModel additionalViewModel = modelManager.getAdditionalViewModel();
        assertDoesNotThrow(() -> modelManager.setAdditionalViewType(AdditionalViewType.VIEW));
        assertDoesNotThrow(() -> modelManager.setAdditionalInfo(AdditionalInfo.of("String")));

        modelManager.setAdditionalViewType(AdditionalViewType.VIEW);
        additionalViewModel.setAdditionalViewType(AdditionalViewType.VIEW);
        assertEquals(modelManager.getAdditionalViewModel(), additionalViewModel);
    }

    @Test
    public void equals() {
        AcademyDirectory academyDirectory = new AcademyDirectoryBuilder().withStudent(ALICE)
                                                    .withStudent(BENSON).build();
        AcademyDirectory differentAcademyDirectory = new AcademyDirectory();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(academyDirectory, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(academyDirectory, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different academyDirectory -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAcademyDirectory, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(academyDirectory, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAcademyDirectoryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(academyDirectory, differentUserPrefs)));
    }
}
