package socialite.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import socialite.commons.exceptions.DataConversionException;
import socialite.commons.util.FileUtil;
import socialite.commons.util.JsonUtil;
import socialite.model.ReadOnlyCommandHistory;

/**
 * A class to access CommandHistory data stored as a json file on the hard disk.
 */
public class JsonCommandHistoryStorage implements CommandHistoryStorage {

    private Path filePath;


    public JsonCommandHistoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getCommandHistoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCommandHistory> readCommandHistory() throws DataConversionException {
        return readCommandHistory(filePath);
    }

    @Override
    public Optional<ReadOnlyCommandHistory> readCommandHistory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCommandHistory> jsonCommandHistory = JsonUtil.readJsonFile(
                filePath, JsonSerializableCommandHistory.class);
        if (jsonCommandHistory.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(jsonCommandHistory.get().toModelType());
    }

    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException {
        saveCommandHistory(commandHistory, filePath);
    }

    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory, Path filePath) throws IOException {
        requireNonNull(commandHistory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCommandHistory(commandHistory), filePath);
    }

}
