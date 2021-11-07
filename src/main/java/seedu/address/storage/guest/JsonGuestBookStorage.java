package seedu.address.storage.guest;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.guest.ReadOnlyGuestBook;

public class JsonGuestBookStorage implements GuestBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGuestBookStorage.class);

    private Path filePath;

    public JsonGuestBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGuestBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGuestBook> readGuestBook() throws DataConversionException {
        return readGuestBook(filePath);
    }

    /**
     * Similar to {@link #readGuestBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGuestBook> readGuestBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGuestBook> jsonGuestManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableGuestBook.class);
        if (!jsonGuestManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGuestManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGuestBook(ReadOnlyGuestBook guestManager) throws IOException {
        saveGuestBook(guestManager, filePath);
    }

    /**
     * Similar to {@link #saveGuestBook(ReadOnlyGuestBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGuestBook(ReadOnlyGuestBook guestManager, Path filePath) throws IOException {
        requireNonNull(guestManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGuestBook(guestManager), filePath);
    }


}
