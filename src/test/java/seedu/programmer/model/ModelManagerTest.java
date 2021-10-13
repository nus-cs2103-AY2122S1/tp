package seedu.programmer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalStudents.ALICE;
import static seedu.programmer.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.model.student.QueryStudentDescriptor;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;
import seedu.programmer.testutil.ProgrammerErrorBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ProgrammerError(), new ProgrammerError(modelManager.getProgrammerError()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setProgrammerErrorFilePath(Paths.get("programmer/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setProgrammerErrorFilePath(Paths.get("new/programmer/book/file/path"));
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
    public void setProgrammerErrorFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setProgrammerErrorFilePath(null));
    }

    @Test
    public void setProgrammerErrorFilePath_validPath_setsProgrammerErrorFilePath() {
        Path path = Paths.get("programmer/book/file/path");
        modelManager.setProgrammerErrorFilePath(path);
        assertEquals(path, modelManager.getProgrammerErrorFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInProgrammerError_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInProgrammerError_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        ProgrammerError programmerError = new ProgrammerErrorBuilder().withStudent(ALICE).withStudent(BENSON).build();
        ProgrammerError differentProgrammerError = new ProgrammerError();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(programmerError, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(programmerError, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different ProgrammerError -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentProgrammerError, userPrefs)));

        // different filteredList -> returns false
        QueryStudentDescriptor descriptor = new QueryStudentDescriptor();
        descriptor.setName(ALICE.getName().fullName);
        modelManager.updateFilteredStudentList(new StudentDetailContainsQueryPredicate(descriptor));
        assertFalse(modelManager.equals(new ModelManager(programmerError, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setProgrammerErrorFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(programmerError, differentUserPrefs)));
    }
}
