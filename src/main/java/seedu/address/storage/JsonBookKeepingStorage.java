package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyBookKeeping;

public class JsonBookKeepingStorage implements BookKeepingStorage {

    private Path filePath;

    public JsonBookKeepingStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getBookKeepingPath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBookKeeping> readBookKeeping() throws DataConversionException, IOException {
        return readBookKeeping(filePath);
    }

    @Override
    public Optional<ReadOnlyBookKeeping> readBookKeeping(Path filePath) throws DataConversionException, IOException {
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

    @Override
    public void saveBookKeeping(ReadOnlyBookKeeping bookKeeping) throws IOException {
        saveBookKeeping(bookKeeping, filePath);
    }

    @Override
    public void saveBookKeeping(ReadOnlyBookKeeping bookKeeping, Path filePath) throws IOException {
        requireNonNull(bookKeeping);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookKeeping(bookKeeping), filePath);
    }
}
