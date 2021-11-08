package seedu.gamebook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.gamebook.commons.core.LogsCenter;
import seedu.gamebook.commons.exceptions.DataConversionException;
import seedu.gamebook.commons.exceptions.IllegalValueException;
import seedu.gamebook.commons.util.FileUtil;
import seedu.gamebook.commons.util.JsonUtil;
import seedu.gamebook.model.ReadOnlyGameBook;

/**
 * A class to access GameBook data stored as a json file on the hard disk.
 */
public class JsonGameBookStorage implements GameBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGameBookStorage.class);

    private Path filePath;

    public JsonGameBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGameBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGameBook> readGameBook() throws DataConversionException {
        return readGameBook(filePath);
    }

    /**
     * Similar to {@link #readGameBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGameBook> readGameBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGameBook> jsonGameBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableGameBook.class);
        if (!jsonGameBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGameBook.get().toModelType());
        } catch (IllegalValueException | ParseException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGameBook(ReadOnlyGameBook gameBook) throws IOException {
        saveGameBook(gameBook, filePath);
    }

    /**
     * Similar to {@link #saveGameBook(ReadOnlyGameBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGameBook(ReadOnlyGameBook gameBook, Path filePath) throws IOException {
        requireNonNull(gameBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGameBook(gameBook), filePath);
    }

}
