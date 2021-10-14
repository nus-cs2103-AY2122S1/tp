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
import seedu.address.model.ReadOnlyFriendsList;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonFriendsListStorage implements FriendsListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFriendsListStorage.class);

    private final Path filePath;

    public JsonFriendsListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFriendsListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFriendsList> readFriendsList() throws DataConversionException {
        return readFriendsList(filePath);
    }

    /**
     * Similar to {@link #readFriendsList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFriendsList> readFriendsList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFriendsList> jsonFriendsList = JsonUtil.readJsonFile(
                filePath, JsonSerializableFriendsList.class);
        if (jsonFriendsList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFriendsList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFriendsList(ReadOnlyFriendsList friendsList) throws IOException {
        saveFriendsList(friendsList, filePath);
    }

    /**
     * Similar to {@link #saveFriendsList(ReadOnlyFriendsList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFriendsList(ReadOnlyFriendsList friendsList, Path filePath) throws IOException {
        requireNonNull(friendsList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFriendsList(friendsList), filePath);
    }

}
