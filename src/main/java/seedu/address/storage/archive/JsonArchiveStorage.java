package seedu.address.storage.archive;

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
import seedu.address.storage.guest.JsonSerializableGuestBook;

public class JsonArchiveStorage implements ArchiveStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonArchiveStorage.class);

    private Path filePath;

    public JsonArchiveStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getArchiveFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGuestBook> readArchive() throws DataConversionException {
        return readArchive(filePath);
    }

    /**
     * Similar to {@link #readArchive()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGuestBook> readArchive(Path filePath) throws DataConversionException {
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
    public void saveArchive(ReadOnlyGuestBook guestManager) throws IOException {
        saveArchive(guestManager, filePath);
    }

    /**
     * Similar to {@link #saveArchive(ReadOnlyGuestBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveArchive(ReadOnlyGuestBook guestManager, Path filePath) throws IOException {
        requireNonNull(guestManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGuestBook(guestManager), filePath);
    }
}
