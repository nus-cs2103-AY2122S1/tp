package seedu.tracker.storage;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.commons.util.JsonUtil;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class JsonUserInfoStorage implements UserInfoStorage {
    private Path filePath;

    public JsonUserInfoStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserInfoFilePath() {
        return filePath;
    }

    @Override
    public Optional<UserInfo> readUserInfo() throws DataConversionException {
        return readUserInfo(filePath);
    }


    /**
     * Similar to {@link #readUserInfo()}
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<UserInfo> readUserInfo(Path filePath) throws DataConversionException {
        return JsonUtil.readJsonFile(filePath, UserInfo.class);
    }


    @Override
    public void saveUserInfo(ReadOnlyUserInfo userInfo) throws IOException {
        JsonUtil.saveJsonFile(userInfo, filePath);
    }
}
