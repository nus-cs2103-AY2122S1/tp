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
import seedu.address.model.ReadOnlyAccount;

/**
 * A class to access Account stored as a json file on the hard disk.
 */
public class JsonAccountStorage implements AccountStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAccountStorage.class);

    private Path filePath;

    /**
     * Constructs {@code JsonAccountStorage} with {@code filePath}.
     */
    public JsonAccountStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets account file path.
     *
     * @return Path of account file.
     */
    public Path getAccountFilePath() {
        return filePath;
    }

    /**
     * Returns Account as a {@link ReadOnlyAccount}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlyAccount> readAccount() throws DataConversionException {
        return readAccount(filePath);
    }

    /**
     * Similar to {@link #readAccount()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAccount> readAccount(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonAdaptedAccount> jsonAccount = JsonUtil.readJsonFile(
                filePath, JsonAdaptedAccount.class);
        if (!jsonAccount.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAccount.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Saves the given {@link ReadOnlyAccount} to the storage.
     *
     * @param account cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAccount(ReadOnlyAccount account) throws IOException {
        saveAccount(account, filePath);
    }

    /**
     * Similar to {@link #saveAccount(ReadOnlyAccount)}.
     *
     * @param filePath location of the account. Cannot be null.
     */
    public void saveAccount(ReadOnlyAccount account, Path filePath) throws IOException {
        requireNonNull(account);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonAdaptedAccount(account), filePath);
    }

}
