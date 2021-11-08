package tutoraid.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tutoraid.commons.core.LogsCenter;
import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.ReadOnlyUserPrefs;
import tutoraid.model.UserPrefs;

/**
 * Manages storage of StudentBook and LessonBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TutorAidStudentStorage tutorAidStudentStorage;
    private TutorAidLessonStorage tutorAidLessonStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TutorAidStudentStorage},
     * {@code TutorAidLessonStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TutorAidStudentStorage tutorAidStudentStorage,
                          TutorAidLessonStorage tutorAidLessonStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.tutorAidStudentStorage = tutorAidStudentStorage;
        this.tutorAidLessonStorage = tutorAidLessonStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ StudentBook methods ==============================

    @Override
    public Path getStudentBookFilePath() {
        return tutorAidStudentStorage.getStudentBookFilePath();
    }

    @Override
    public Optional<ReadOnlyStudentBook> readStudentBook(ReadOnlyLessonBook lessonBook)
            throws DataConversionException, IOException {
        return readStudentBook(tutorAidStudentStorage.getStudentBookFilePath(), lessonBook);
    }

    @Override
    public Optional<ReadOnlyStudentBook> readStudentBook(Path filePath, ReadOnlyLessonBook lessonBook)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tutorAidStudentStorage.readStudentBook(filePath, lessonBook);
    }

    @Override
    public void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException {
        saveStudentBook(studentBook, tutorAidStudentStorage.getStudentBookFilePath());
    }

    @Override
    public void saveStudentBook(ReadOnlyStudentBook studentBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tutorAidStudentStorage.saveStudentBook(studentBook, filePath);
    }

    // ================ LessonBook methods ==============================

    @Override
    public Path getLessonBookFilePath() {
        return tutorAidLessonStorage.getLessonBookFilePath();
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException {
        return readLessonBook(tutorAidLessonStorage.getLessonBookFilePath());
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tutorAidLessonStorage.readLessonBook(filePath);
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException {
        saveLessonBook(lessonBook, tutorAidLessonStorage.getLessonBookFilePath());
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tutorAidLessonStorage.saveLessonBook(lessonBook, filePath);
    }

}
