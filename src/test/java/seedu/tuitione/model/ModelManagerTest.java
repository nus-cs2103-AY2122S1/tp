package seedu.tuitione.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.TypicalStudents.ALICE;
import static seedu.tuitione.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.GuiSettings;
import seedu.tuitione.model.student.NameContainsKeywordsPredicate;
import seedu.tuitione.testutil.TuitioneBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Tuitione(), new Tuitione(modelManager.getTuitione()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTuitioneFilePath(Paths.get("tuitione/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTuitioneFilePath(Paths.get("new/tuitione/book/file/path"));
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
    public void setTuitioneFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTuitioneFilePath(null));
    }

    @Test
    public void setTuitioneFilePath_validPath_setsTuitioneFilePath() {
        Path path = Paths.get("tuitione/book/file/path");
        modelManager.setTuitioneFilePath(path);
        assertEquals(path, modelManager.getTuitioneFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInTuitione_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInTuitione_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        Tuitione tuitione = new TuitioneBuilder().withStudent(ALICE).withStudent(BENSON).build();
        Tuitione differentTuitione = new Tuitione();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(tuitione, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(tuitione, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different tuitione -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTuitione, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(tuitione, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTuitioneFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(tuitione, differentUserPrefs)));
    }
}
