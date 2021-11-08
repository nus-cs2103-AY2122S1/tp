package seedu.track2gather.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.track2gather.commons.core.LogsCenter;
import seedu.track2gather.commons.exceptions.DataConversionException;
import seedu.track2gather.commons.exceptions.IllegalValueException;
import seedu.track2gather.commons.util.FileUtil;
import seedu.track2gather.commons.util.JsonUtil;
import seedu.track2gather.model.ReadOnlyTrack2Gather;

/**
 * A class to access Track2Gather data stored as a json file on the hard disk.
 */
public class JsonTrack2GatherStorage implements Track2GatherStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrack2GatherStorage.class);

    private Path filePath;

    public JsonTrack2GatherStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrack2GatherFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTrack2Gather> readTrack2Gather() throws DataConversionException {
        return readTrack2Gather(filePath);
    }

    /**
     * Similar to {@link #readTrack2Gather()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTrack2Gather> readTrack2Gather(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTrack2Gather> jsonTrack2Gather = JsonUtil.readJsonFile(
                filePath, JsonSerializableTrack2Gather.class);
        if (!jsonTrack2Gather.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrack2Gather.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather) throws IOException {
        saveTrack2Gather(track2Gather, filePath);
    }

    /**
     * Similar to {@link #saveTrack2Gather(ReadOnlyTrack2Gather)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTrack2Gather(ReadOnlyTrack2Gather track2Gather, Path filePath) throws IOException {
        requireNonNull(track2Gather);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTrack2Gather(track2Gather), filePath);
    }

}
