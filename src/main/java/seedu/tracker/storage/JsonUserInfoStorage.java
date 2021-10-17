package seedu.tracker.storage;

import seedu.tracker.commons.core.LogsCenter;
import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.commons.util.JsonUtil;
import seedu.tracker.model.ReadOnlyUserInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class JsonUserInfoStorage implements UserInfoStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonUserInfoStorage.class);

    private Path filePath;

    public JsonUserInfoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUserInfoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUserInfo> readUserInfo() throws DataConversionException, IOException {
        return readUserInfo(filePath);
    }

    /**
     * Similar to {@link #readUserInfo()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUserInfo> readUserInfo(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUserInfo> jsonSerializableUserInfo = JsonUtil.readJsonFile(
                filePath, JsonSerializableUserInfo.class);
        if(!jsonSerializableUserInfo.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSerializableUserInfo.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUserInfo(ReadOnlyUserInfo userInfo) throws IOException {

    }

    @Override
    public void saveUserInfo(ReadOnlyUserInfo userInfo, Path filePath) throws IOException {

    }
}
