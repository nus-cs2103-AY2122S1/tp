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
import seedu.address.model.ReadOnlySportsPa;

/**
 * A class to access SportsPa data stored as a json file on the hard disk.
 */
public class JsonSportsPaStorage implements SportsPaStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSportsPaStorage.class);

    private Path filePath;

    public JsonSportsPaStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSportsPaFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySportsPa> readSportsPa() throws DataConversionException {
        return readSportsPa(filePath);
    }

    /**
     * Similar to {@link #readSportsPa()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySportsPa> readSportsPa(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSportsPa> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableSportsPa.class);
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
    public void saveSportsPa(ReadOnlySportsPa addressBook) throws IOException {
        saveSportsPa(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveSportsPa(ReadOnlySportsPa)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSportsPa(ReadOnlySportsPa addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSportsPa(addressBook), filePath);
    }

}
