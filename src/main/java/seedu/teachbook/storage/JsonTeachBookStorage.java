package seedu.teachbook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.teachbook.commons.core.LogsCenter;
import seedu.teachbook.commons.exceptions.DataConversionException;
import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.commons.util.FileUtil;
import seedu.teachbook.commons.util.JsonUtil;
import seedu.teachbook.model.ReadOnlyTeachBook;

/**
 * A class to access TeachBook data stored as a json file on the hard disk.
 */
public class JsonTeachBookStorage implements TeachBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTeachBookStorage.class);

    private Path filePath;

    public JsonTeachBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTeachBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTeachBook> readTeachBook() throws DataConversionException {
        return readTeachBook(filePath);
    }

    /**
     * Similar to {@link #readTeachBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTeachBook> readTeachBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTeachBook> jsonTeachBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTeachBook.class);
        if (jsonTeachBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTeachBook.get().toModelType());
        } catch (IllegalValueException exception) {
            logger.info("Illegal values found in " + filePath + ": " + exception.getMessage());
            throw new DataConversionException(exception);
        }
    }

    @Override
    public void saveTeachBook(ReadOnlyTeachBook teachBook) throws IOException {
        saveTeachBook(teachBook, filePath);
    }

    /**
     * Similar to {@link #saveTeachBook(ReadOnlyTeachBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTeachBook(ReadOnlyTeachBook teachBook, Path filePath) throws IOException {
        requireNonNull(teachBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTeachBook(teachBook), filePath);
    }

}
