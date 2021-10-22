package seedu.tracker.storage;

import seedu.tracker.commons.exceptions.DataConversionException;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.UserInfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface UserInfoStorage {
    Path getUserInfoFilePath();

    Optional<UserInfo> readUserInfo() throws DataConversionException, IOException;

    void saveUserInfo(ReadOnlyUserInfo userInfo) throws IOException;
}
