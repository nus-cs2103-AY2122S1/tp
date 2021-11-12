package seedu.academydirectory.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.commons.exceptions.DataConversionException;
import seedu.academydirectory.commons.exceptions.IllegalValueException;
import seedu.academydirectory.commons.util.FileUtil;
import seedu.academydirectory.commons.util.JsonUtil;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;

/**
 * A class to access AcademyDirectory data stored as a json file on the hard disk.
 */
public class JsonAcademyDirectoryStorage implements AcademyDirectoryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAcademyDirectoryStorage.class);

    private Path filePath;

    public JsonAcademyDirectoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAcademyDirectoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAcademyDirectory> readAcademyDirectory() throws DataConversionException {
        return readAcademyDirectory(filePath);
    }

    /**
     * Similar to {@link #readAcademyDirectory()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAcademyDirectory> readAcademyDirectory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAcademyDirectory> jsonAcademyDirectory = JsonUtil.readJsonFile(
                filePath, JsonSerializableAcademyDirectory.class);
        if (!jsonAcademyDirectory.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAcademyDirectory.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory) throws IOException {
        saveAcademyDirectory(academyDirectory, filePath);
    }

    /**
     * Similar to {@link #saveAcademyDirectory(ReadOnlyAcademyDirectory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory, Path filePath) throws IOException {
        requireNonNull(academyDirectory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAcademyDirectory(academyDirectory), filePath);
    }

}
