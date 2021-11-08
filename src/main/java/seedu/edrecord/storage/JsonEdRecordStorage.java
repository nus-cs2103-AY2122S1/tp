package seedu.edrecord.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.commons.util.FileUtil;
import seedu.edrecord.commons.util.JsonUtil;
import seedu.edrecord.model.ReadOnlyEdRecord;

/**
 * A class to access EdRecord data stored as a json file on the hard disk.
 */
public class JsonEdRecordStorage implements EdRecordStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEdRecordStorage.class);

    private Path filePath;

    public JsonEdRecordStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEdRecordFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEdRecord> readEdRecord() throws DataConversionException {
        return readEdRecord(filePath);
    }

    /**
     * Similar to {@link #readEdRecord()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEdRecord> readEdRecord(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEdRecord> jsonEdRecord = JsonUtil.readJsonFile(
                filePath, JsonSerializableEdRecord.class);
        if (!jsonEdRecord.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEdRecord.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveEdRecord(ReadOnlyEdRecord edRecord) throws IOException {
        saveEdRecord(edRecord, filePath);
    }

    /**
     * Similar to {@link #saveEdRecord(ReadOnlyEdRecord)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEdRecord(ReadOnlyEdRecord edRecord, Path filePath) throws IOException {
        requireNonNull(edRecord);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEdRecord(edRecord), filePath);
    }

}
