package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tutoraid.commons.core.GuiSettings;
import tutoraid.model.LessonBook;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;
import tutoraid.model.UserPrefs;
import tutoraid.testutil.TypicalLessons;
import tutoraid.testutil.TypicalStudents;

public class StorageManagerTest {
    private static final ReadOnlyLessonBook lb = TypicalLessons.getTypicalLessonBook();

    @TempDir
    public Path testFolder;
    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {

        JsonTutorAidStudentStorage studentBookStorage =
                new JsonTutorAidStudentStorage(getTempFilePath("sb"), lb);
        JsonTutorAidLessonStorage lessonBookStorage = new JsonTutorAidLessonStorage(getTempFilePath("lb"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(studentBookStorage, lessonBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void studentBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTutorAidStudentStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonStudentBookStorageTest} class.
         */
        StudentBook original = TypicalStudents.getTypicalStudentBook();
        storageManager.saveStudentBook(original);
        ReadOnlyStudentBook retrieved = storageManager.readStudentBook(lb).get();
        assertEquals(original, new StudentBook(retrieved));
    }

    @Test
    public void getStudentBookFilePath() {
        assertNotNull(storageManager.getStudentBookFilePath());
    }

    @Test
    public void lessonBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTutorAidLessonStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonLessonBookStorageTest} class.
         */
        LessonBook original = TypicalLessons.getTypicalLessonBook();
        storageManager.saveLessonBook(original);
        ReadOnlyLessonBook retrieved = storageManager.readLessonBook().get();
        assertEquals(original, new LessonBook(retrieved));
    }

    @Test
    public void getLessonBookFilePath() {
        assertNotNull(storageManager.getLessonBookFilePath());
    }

}
