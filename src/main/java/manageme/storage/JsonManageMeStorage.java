package manageme.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import manageme.commons.core.LogsCenter;
import manageme.commons.exceptions.DataConversionException;
import manageme.commons.exceptions.IllegalValueException;
import manageme.commons.util.FileUtil;
import manageme.commons.util.JsonUtil;
import manageme.model.ReadOnlyManageMe;

/**
 * A class to access ManageMe data stored as a json file on the hard disk.
 */
public class JsonManageMeStorage implements ManageMeStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonManageMeStorage.class);

    private Path filePath;

    public JsonManageMeStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getManageMeFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyManageMe> readManageMe() throws DataConversionException {
        return readManageMe(filePath);
    }

    /**
     * Similar to {@link #readManageMe()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyManageMe> readManageMe(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableManageMe> jsonManageMe = JsonUtil.readJsonFile(
                filePath, JsonSerializableManageMe.class);
        if (!jsonManageMe.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonManageMe.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveManageMe(ReadOnlyManageMe manageMe) throws IOException {
        saveManageMe(manageMe, filePath);
    }

    /**
     * Similar to {@link #saveManageMe(ReadOnlyManageMe)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveManageMe(ReadOnlyManageMe manageMe, Path filePath) throws IOException {
        requireNonNull(manageMe);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableManageMe(manageMe), filePath);
    }

}
