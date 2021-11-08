package seedu.track2gather.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.track2gather.commons.exceptions.DataConversionException;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.ReadOnlyUserPrefs;
import seedu.track2gather.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends Track2GatherStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTrack2GatherFilePath();

    @Override
    Optional<ReadOnlyTrack2Gather> readTrack2Gather() throws DataConversionException, IOException;

    @Override
    void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather) throws IOException;

}
