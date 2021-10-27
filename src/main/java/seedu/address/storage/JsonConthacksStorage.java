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
import seedu.address.model.ReadOnlyConthacks;

/**
 * A class to access Conthacks data stored as a json file on the hard disk.
 */
public class JsonConthacksStorage implements ConthacksStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonConthacksStorage.class);

    private Path filePath;

    public JsonConthacksStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getConthacksFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyConthacks> readConthacks() throws DataConversionException {
        return readConthacks(filePath);
    }

    /**
     * Similar to {@link #readConthacks()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyConthacks> readConthacks(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableConthacks> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableConthacks.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveConthacks(ReadOnlyConthacks addressBook) throws IOException {
        saveConthacks(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveConthacks(ReadOnlyConthacks)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveConthacks(ReadOnlyConthacks conthacks, Path filePath) throws IOException {
        requireNonNull(conthacks);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableConthacks(conthacks), filePath);
    }

}
