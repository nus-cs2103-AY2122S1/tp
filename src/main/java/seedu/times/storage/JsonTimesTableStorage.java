package seedu.times.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.times.commons.core.LogsCenter;
import seedu.times.commons.exceptions.DataConversionException;
import seedu.times.commons.exceptions.IllegalValueException;
import seedu.times.commons.util.FileUtil;
import seedu.times.commons.util.JsonUtil;
import seedu.times.model.ReadOnlyTimesTable;

/**
 * A class to access TimesTable data stored as a json file on the hard disk.
 */
public class JsonTimesTableStorage implements TimesTableStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTimesTableStorage.class);

    private Path filePath;

    public JsonTimesTableStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTimesTableFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTimesTable> readTimesTable() throws DataConversionException {
        return readTimesTable(filePath);
    }

    /**
     * Similar to {@link #readTimesTable()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTimesTable> readTimesTable(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTimesTable> jsonTimesTable = JsonUtil.readJsonFile(
                filePath, JsonSerializableTimesTable.class);
        if (!jsonTimesTable.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTimesTable.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTimesTable(ReadOnlyTimesTable timesTable) throws IOException {
        saveTimesTable(timesTable, filePath);
    }

    /**
     * Similar to {@link #saveTimesTable(ReadOnlyTimesTable)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTimesTable(ReadOnlyTimesTable timesTable, Path filePath) throws IOException {
        requireNonNull(timesTable);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTimesTable(timesTable), filePath);
    }

}
