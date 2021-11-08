package seedu.track2gather.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.track2gather.commons.exceptions.DataConversionException;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.Track2Gather;

/**
 * Represents a storage for {@link Track2Gather}.
 */
public interface Track2GatherStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTrack2GatherFilePath();

    /**
     * Returns Track2Gather data as a {@link ReadOnlyTrack2Gather}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTrack2Gather> readTrack2Gather() throws DataConversionException, IOException;

    /**
     * @see #getTrack2GatherFilePath()
     */
    Optional<ReadOnlyTrack2Gather> readTrack2Gather(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTrack2Gather} to the storage.
     * @param track2Gather cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather) throws IOException;

    /**
     * @see #saveTrack2Gather(ReadOnlyTrack2Gather)
     */
    void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather, Path filePath) throws IOException;

}
