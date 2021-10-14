package seedu.programmer.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.commons.exceptions.DataConversionException;
import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.commons.util.FileUtil;
import seedu.programmer.commons.util.JsonUtil;
import seedu.programmer.model.ReadOnlyProgrammerError;

/**
 * A class to access ProgrammerError data stored as a json file on the hard disk.
 */
public class JsonProgrammerErrorStorage implements ProgrammerErrorStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonProgrammerErrorStorage.class);

    private Path filePath;

    public JsonProgrammerErrorStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getProgrammerErrorFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyProgrammerError> readProgrammerError() throws DataConversionException {
        return readProgrammerError(filePath);
    }

    /**
     * Similar to {@link #readProgrammerError()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyProgrammerError> readProgrammerError(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableProgrammerError> jsonProgrammerError = JsonUtil.readJsonFile(
                filePath, JsonSerializableProgrammerError.class);
        if (jsonProgrammerError.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonProgrammerError.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveProgrammerError(ReadOnlyProgrammerError programmerError) throws IOException {
        saveProgrammerError(programmerError, filePath);
    }

    /**
     * Similar to {@link #saveProgrammerError(ReadOnlyProgrammerError)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveProgrammerError(ReadOnlyProgrammerError programmerError, Path filePath) throws IOException {
        requireNonNull(programmerError);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProgrammerError(programmerError), filePath);
    }

}
