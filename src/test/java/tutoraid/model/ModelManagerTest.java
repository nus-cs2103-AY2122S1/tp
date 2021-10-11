package tutoraid.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.GuiSettings;
import tutoraid.model.student.NameContainsKeywordsPredicate;
import tutoraid.testutil.Assert;
import tutoraid.testutil.StudentBookBuilder;
import tutoraid.testutil.TypicalStudents;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new StudentBook(), new StudentBook(modelManager.getStudentBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setStudentBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setStudentBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setStudentBookFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setStudentBookFilePath(null));
    }

    @Test
    public void setStudentBookFilePath_validPath_setsStudentBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setStudentBookFilePath(path);
        assertEquals(path, modelManager.getStudentBookFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInStudentBook_returnsFalse() {
        assertFalse(modelManager.hasStudent(TypicalStudents.ALICE));
    }

    @Test
    public void hasStudent_studentInStudentBook_returnsTrue() {
        modelManager.addStudent(TypicalStudents.ALICE);
        assertTrue(modelManager.hasStudent(TypicalStudents.ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList()
            .remove(0));
    }

    @Test
    public void equals() {
        StudentBook studentBook = new StudentBookBuilder().withStudent(TypicalStudents.ALICE)
            .withStudent(TypicalStudents.BENSON).build();
        StudentBook differentStudentBook = new StudentBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(studentBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(studentBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different studentBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentStudentBook, userPrefs)));

        //TODO: The only test that is failing
        // different filteredList -> returns false
        String[] keywords = TypicalStudents.ALICE.getStudentName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(studentBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setStudentBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(studentBook, differentUserPrefs)));
    }
}
