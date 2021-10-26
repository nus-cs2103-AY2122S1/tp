package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.BookKeeping;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class BookKeepingStorage {
    public void saveTransaction(BookKeeping bookKeeping, Path filePath) throws IOException {
        requireNonNull(bookKeeping);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookKeeping(bookKeeping), filePath);
    }

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
