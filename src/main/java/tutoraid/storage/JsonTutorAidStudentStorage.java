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
import tutoraid.model.ReadOnlyStudentBook;

/**
 * A class to access StudentBook data stored as a json file on the hard disk.
 */
public class JsonTutorAidStudentStorage implements TutorAidStudentStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTutorAidStudentStorage.class);

    private Path filePath;
    private ReadOnlyLessonBook lessonBook;

    /**
     * Constructor for the student storage object.
     *
     * @param filePath Path to the JSON file
     * @param lessonBook Lesson book to create the Lesson-Student dependency
     */
    public JsonTutorAidStudentStorage(Path filePath, ReadOnlyLessonBook lessonBook) {
        this.filePath = filePath;
        this.lessonBook = lessonBook;
    }

    public Path getStudentBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStudentBook> readStudentBook(ReadOnlyLessonBook lessonBook) throws DataConversionException {
        return readStudentBook(filePath, lessonBook);
    }

    /**
     * Similar to {@link #readStudentBook(ReadOnlyLessonBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStudentBook> readStudentBook(Path filePath, ReadOnlyLessonBook lessonBook)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudentBook> jsonStudentBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudentBook.class);
        if (jsonStudentBook.isEmpty()) {
            try {
                return Optional.of(new JsonSerializableStudentBook(SampleDataUtil.getSampleStudentBook(lessonBook))
                        .toModelType(lessonBook));
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }
        }

        try {
            return Optional.of(jsonStudentBook.get().toModelType(lessonBook));
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException {
        saveStudentBook(studentBook, filePath);
    }

    /**
     * Similar to {@link #saveStudentBook(ReadOnlyStudentBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStudentBook(ReadOnlyStudentBook studentBook, Path filePath) throws IOException {
        requireNonNull(studentBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStudentBook(studentBook), filePath);
    }

}
