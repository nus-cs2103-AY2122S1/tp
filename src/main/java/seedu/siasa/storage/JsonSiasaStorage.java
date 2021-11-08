package seedu.siasa.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.siasa.commons.core.LogsCenter;
import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.commons.exceptions.IllegalValueException;
import seedu.siasa.commons.util.FileUtil;
import seedu.siasa.commons.util.JsonUtil;
import seedu.siasa.model.ReadOnlySiasa;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonSiasaStorage implements SiasaStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSiasaStorage.class);

    private Path filePath;

    public JsonSiasaStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSiasaFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySiasa> readSiasa() throws DataConversionException {
        return readSiasa(filePath);
    }

    /**
     * Similar to {@link #readSiasa()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySiasa> readSiasa(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSiasa> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableSiasa.class);
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
    public void saveSiasa(ReadOnlySiasa siasa) throws IOException {
        saveSiasa(siasa, filePath);
    }

    /**
     * Similar to {@link #saveSiasa(ReadOnlySiasa)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSiasa(ReadOnlySiasa addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSiasa(addressBook), filePath);
    }

}
