package seedu.tracker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.commons.util.JsonUtil;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;

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
    public Optional<UserInfo> readUserInfo() throws DataConversionException, IOException {
        return readUserInfo(filePath);
    }

    public Optional<UserInfo> readUserInfo(Path infoFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(infoFilePath, UserInfo.class);
    }

    @Override
    public void saveUserInfo(ReadOnlyUserInfo userInfo) throws IOException {
        JsonUtil.saveJsonFile(userInfo, filePath);
    }
}
