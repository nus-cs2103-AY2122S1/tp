package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.BookKeeping;

public class BookKeepingStorage {
    /**
     * Save current BookKeeping to json.
     *
     * @param bookKeeping current bookKeeping.
     * @param filePath the path of bookKeeping.json.
     * @throws IOException if bookKeeping is not serializable.
     */
    public void saveBookKeeping(BookKeeping bookKeeping, Path filePath) throws IOException {
        requireNonNull(bookKeeping);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookKeeping(bookKeeping), filePath);
    }

    /**
     * Read BookKeeping from json file.
     *
     * @param filePath the path of the bookKeeping.json.
     * @return the BookKeeping enclosed in an optional.
     * @throws DataConversionException if bookKeeping.json cannot be converted to bookKeeping.
     */
    public Optional<BookKeeping> readBookKeeping(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBookKeeping> jsonBookKeeping = JsonUtil.readJsonFile(
                filePath, JsonSerializableBookKeeping.class);

        if (!jsonBookKeeping.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBookKeeping.get().toModelType());
        } catch (IllegalValueException e) {
            throw new DataConversionException(e);
        }
    }
}
