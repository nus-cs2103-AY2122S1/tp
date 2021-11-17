package socialite.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import socialite.commons.exceptions.DataConversionException;
import socialite.model.ReadOnlyCommandHistory;

/**
 * Represents a storage for {@link ReadOnlyCommandHistory}.
 */
public interface CommandHistoryStorage {
    /**
     * Returns the file path of the CommandHistory data file.
     */
    Path getCommandHistoryFilePath();

    Optional<ReadOnlyCommandHistory> readCommandHistory() throws DataConversionException, IOException;

    Optional<ReadOnlyCommandHistory> readCommandHistory(Path filePath) throws DataConversionException, IOException;

    void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException;

    void saveCommandHistory(ReadOnlyCommandHistory commandHistory, Path filePath) throws IOException;
}
