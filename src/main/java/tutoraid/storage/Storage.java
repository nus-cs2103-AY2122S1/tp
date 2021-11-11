package tutoraid.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.ReadOnlyUserPrefs;
import tutoraid.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TutorAidStudentStorage, TutorAidLessonStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentBookFilePath();

    @Override
    Optional<ReadOnlyStudentBook> readStudentBook(ReadOnlyLessonBook lessonBook)
            throws DataConversionException, IOException;

    @Override
    void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException;

    @Override
    Path getLessonBookFilePath();

    @Override
    Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException;

    @Override
    void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException;

}
