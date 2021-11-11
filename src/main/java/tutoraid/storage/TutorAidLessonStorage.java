package tutoraid.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.LessonBook;
import tutoraid.model.ReadOnlyLessonBook;

/**
 * Represents a storage for {@link LessonBook}.
 */
public interface TutorAidLessonStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLessonBookFilePath();

    /**
     * Returns LessonBook data as a {@link ReadOnlyLessonBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException;

    /**
     * @see #getLessonBookFilePath()
     */
    Optional<ReadOnlyLessonBook> readLessonBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyLessonBook} to the storage.
     * @param lessonBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException;

    /**
     * @see #saveLessonBook(ReadOnlyLessonBook)
     */
    void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException;
}
