package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyInternship;

/**
 * A class to access Internship data stored as a json file on the hard disk.
 */
public class JsonInternshipStorage implements InternshipStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternshipStorage.class);

    private Path filePath;

    public JsonInternshipStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternshipFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternship> readInternship() throws DataConversionException {
        return readInternship(filePath);
    }

    /**
     * Similar to {@link #readInternship()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInternship> readInternship(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternship> jsonInternship = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternship.class);
        if (!jsonInternship.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInternship.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInternship(ReadOnlyInternship internship) throws IOException {
        saveInternship(internship, filePath);
    }

    /**
     * Similar to {@link #saveInternship(ReadOnlyInternship)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternship(ReadOnlyInternship internship, Path filePath) throws IOException {
        requireNonNull(internship);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternship(internship), filePath);
    }

}
