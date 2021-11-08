package seedu.edrecord.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.model.ReadOnlyEdRecord;

/**
 * Represents a storage for {@link seedu.edrecord.model.EdRecord}.
 */
public interface EdRecordStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEdRecordFilePath();

    /**
     * Returns EdRecord data as a {@link ReadOnlyEdRecord}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEdRecord> readEdRecord() throws DataConversionException, IOException;

    /**
     * @see #getEdRecordFilePath()
     */
    Optional<ReadOnlyEdRecord> readEdRecord(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEdRecord} to the storage.
     * @param edRecord cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEdRecord(ReadOnlyEdRecord edRecord) throws IOException;

    /**
     * @see #saveEdRecord(ReadOnlyEdRecord)
     */
    void saveEdRecord(ReadOnlyEdRecord edRecord, Path filePath) throws IOException;

}
