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
import tutoraid.model.ReadOnlyStudentBook;

/**
 * A class to access StudentBook data stored as a json file on the hard disk.
 */
public class JsonTutorAidStorage implements TutorAidStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTutorAidStorage.class);

    private Path filePath;

    public JsonTutorAidStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStudentBook> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStudentBook> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudentBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudentBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStudentBook(ReadOnlyStudentBook addressBook) throws IOException {
        saveStudentBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveStudentBook(ReadOnlyStudentBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStudentBook(ReadOnlyStudentBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStudentBook(addressBook), filePath);
    }

}
