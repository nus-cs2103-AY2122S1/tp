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
import seedu.address.model.ReadOnlyTeachingAssistantBuddy;

/**
 * A class to access TeachingAssistantBuddy data stored as a json file on the hard disk.
 */
public class JsonTeachingAssistantBuddyStorage implements TeachingAssistantBuddyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTeachingAssistantBuddyStorage.class);

    private Path filePath;

    public JsonTeachingAssistantBuddyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTeachingAssistantBuddyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy() throws DataConversionException {
        return readTeachingAssistantBuddy(filePath);
    }

    /**
     * Similar to {@link #readTeachingAssistantBuddy()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTeachingAssistantBuddy> readTeachingAssistantBuddy(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAssistantBuddy> jsonTeachingAssistantBuddy = JsonUtil.readJsonFile(
                filePath, JsonSerializableAssistantBuddy.class);
        if (!jsonTeachingAssistantBuddy.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTeachingAssistantBuddy.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy) throws IOException {
        saveTeachingAssistantBuddy(assistantBuddy, filePath);
    }

    /**
     * Similar to {@link #saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTeachingAssistantBuddy(ReadOnlyTeachingAssistantBuddy assistantBuddy, Path filePath)
            throws IOException {
        requireNonNull(assistantBuddy);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAssistantBuddy(assistantBuddy), filePath);
    }

}
