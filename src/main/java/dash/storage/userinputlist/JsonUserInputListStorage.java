package dash.storage.userinputlist;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import dash.commons.exceptions.DataConversionException;
import dash.commons.exceptions.IllegalValueException;
import dash.commons.util.FileUtil;
import dash.commons.util.JsonUtil;
import dash.model.UserInputList;

public class JsonUserInputListStorage implements UserInputListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUserInputListStorage.class);

    private Path filePath;

    public JsonUserInputListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserInputListFilePath() {
        return filePath;
    }

    @Override
    public Optional<UserInputList> readUserInputList() throws DataConversionException {
        return readUserInputList(filePath);
    }

    /**
     * Similar to {@link #readUserInputList()}
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<UserInputList> readUserInputList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUserInputList> jsonUserInputList = JsonUtil.readJsonFile(
                filePath, JsonSerializableUserInputList.class);
        if (!jsonUserInputList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUserInputList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUserInputList(UserInputList userInputList) throws IOException {
        saveUserInputList(userInputList, filePath);
    }

    @Override
    public void saveUserInputList(UserInputList userInputList, Path filePath) throws IOException {
        requireNonNull(userInputList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUserInputList(userInputList), filePath);
    }


}
