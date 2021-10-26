package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.EncryptedJsonUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyCsBook;

/**
 * A class to access CsBook data stored as a json file on the hard disk.
 */
public class JsonCsBookStorage implements CsBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCsBookStorage.class);

    private static boolean isEncrypted;

    private Path filePath;

    public JsonCsBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public static void setIsEncrypted(boolean state) {
        isEncrypted = state;
    }

    public Path getCsBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCsBook> readCsBook() throws DataConversionException {
        return readCsBook(filePath);
    }

    /**
     * Similar to {@link #readCsBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyCsBook> readCsBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        Optional<JsonSerializableCsBook> jsonCsBook;
        try {
            jsonCsBook = JsonUtil.readJsonFile(
                    filePath, JsonSerializableCsBook.class);
            isEncrypted = false;
        } catch (DataConversionException e) {
            jsonCsBook = EncryptedJsonUtil.readEncryptedJsonFile(
                    filePath, JsonSerializableCsBook.class);
            isEncrypted = true;
        }

        if (!jsonCsBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCsBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveCsBook(ReadOnlyCsBook csBook) throws IOException {
        saveCsBook(csBook, filePath);
    }

    /**
     * Similar to {@link #saveCsBook(ReadOnlyCsBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCsBook(ReadOnlyCsBook csBook, Path filePath) throws IOException {
        requireNonNull(csBook);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        if (isEncrypted) {
            EncryptedJsonUtil.saveEncryptedJsonFile(new JsonSerializableCsBook(csBook), filePath);
        } else {
            JsonUtil.saveJsonFile(new JsonSerializableCsBook(csBook), filePath);
        }
    }

}
