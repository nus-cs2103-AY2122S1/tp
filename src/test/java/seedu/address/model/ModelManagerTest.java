package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2103T;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.student.Name;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.testutil.CsBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new CsBook(), new CsBook(modelManager.getCsBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setCsBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setCsBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setCsBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setCsBookFilePath(null));
    }

    @Test
    public void setCsBookFilePath_validPath_setsCsBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setCsBookFilePath(path);
        assertEquals(path, modelManager.getCsBookFilePath());
    }

    @Test
    public void hasStudent_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent((Name) null));
    }

    @Test
    public void hasStudent_studentNotInCsBook_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInCsBook_returnsTrue() {
        modelManager.addGroup(TYPICAL_GROUP_CS2103T);
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    // Extension of the previous test case. After adding student, if we delete it, it should no longer be in the model
    @Test
    public void hasStudent_addThenDeleteStudent_returnsFalse() {
        modelManager.addGroup(TYPICAL_GROUP_CS2103T);
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));

        modelManager.deleteStudent(ALICE);
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getStudentByName_studentExists_returnsCorrectName() {
        modelManager.addGroup(TYPICAL_GROUP_CS2103T);
        modelManager.addStudent(ALICE);
        assertEquals(ALICE, modelManager.getStudentByName(ALICE.getName()));
    }

    @Test
    public void getStudentByName_studentDoesNotExist_returnsNull() {
        assertNull(modelManager.getStudentByName(ALICE.getName()));
    }

    @Test
    public void setStudent_studentInCsBook_setsStudent() {
        modelManager.addGroup(TYPICAL_GROUP_CS2103T);
        modelManager.addStudent(ALICE);
        modelManager.setStudent(ALICE, BOB);

        assertFalse(modelManager.hasStudent(ALICE));
        assertTrue(modelManager.hasStudent(BOB));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        CsBook csBook = new CsBookBuilder().withStudent(ALICE).withStudent(BENSON).build();
        CsBook differentCsBook = new CsBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(csBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(csBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different csBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentCsBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(csBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setCsBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(csBook, differentUserPrefs)));
    }
}
