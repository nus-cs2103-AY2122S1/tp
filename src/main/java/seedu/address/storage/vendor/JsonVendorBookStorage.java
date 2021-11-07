package seedu.address.storage.vendor;

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
import seedu.address.model.vendor.ReadOnlyVendorBook;

public class JsonVendorBookStorage implements VendorBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonVendorBookStorage.class);

    private Path filePath;

    public JsonVendorBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getVendorBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyVendorBook> readVendorBook() throws DataConversionException {
        return readVendorBook(filePath);
    }

    /**
     * Similar to {@link #readVendorBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyVendorBook> readVendorBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableVendorBook> jsonVendorManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableVendorBook.class);
        if (!jsonVendorManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonVendorManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveVendorBook(ReadOnlyVendorBook vendorManager) throws IOException {
        saveVendorBook(vendorManager, filePath);
    }

    /**
     * Similar to {@link #saveVendorBook(ReadOnlyVendorBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveVendorBook(ReadOnlyVendorBook vendorManager, Path filePath) throws IOException {
        requireNonNull(vendorManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableVendorBook(vendorManager), filePath);
    }


}

