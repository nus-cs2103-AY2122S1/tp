package tutoraid.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tutoraid.commons.core.LogsCenter;
import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.commons.util.FileUtil;
import tutoraid.commons.util.JsonUtil;
import tutoraid.model.ReadOnlyLessonBook;

/**
 * A class to access LessonBook data stored as a json file on the hard disk.
 */
public class JsonTutorAidLessonStorage implements TutorAidLessonStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTutorAidLessonStorage.class);

    private Path filePath;

    public JsonTutorAidLessonStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLessonBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException {
        return readLessonBook(filePath);
    }

    /**
     * Similar to {@link #readLessonBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLessonBook> readLessonBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableLessonBook> jsonLessonBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableLessonBook.class);
        if (jsonLessonBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLessonBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException {
        saveLessonBook(lessonBook, filePath);
    }

    /**
     * Similar to {@link #saveLessonBook(ReadOnlyLessonBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException {
        requireNonNull(lessonBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLessonBook(lessonBook), filePath);
    }
}
