package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachingAssistantBuddy;
import seedu.address.model.TeachingAssistantBuddy;

/**
 * Represents a storage for {@link TeachingAssistantBuddy}.
 */
public interface TeachingAssistantBuddyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTeachingAssistantBuddyFilePath();

    /**
     * Returns TeachingAssistantBuddy data as a {@link ReadOnlyTeachingAssistantBuddy}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy() throws DataConversionException, IOException;

    /**
     * @see #getTeachingAssistantBuddyFilePath()
     */
    Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTeachingAssistantBuddy} to the storage.
     * @param assistantBuddy cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy) throws IOException;

    /**
     * @see #saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy)
     */
    void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy, Path filePath) throws IOException;

}
