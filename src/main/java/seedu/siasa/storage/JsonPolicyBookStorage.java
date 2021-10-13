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

public class JsonPolicyBookStorage implements PolicyBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPolicyBookStorage.class);

    private Path filePath;

    public JsonPolicyBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getPolicyBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySiasa> readPolicyBook() throws DataConversionException {
        return readPolicyBook(filePath);
    }

    /**
     * Similar to {@link #readPolicyBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlySiasa> readPolicyBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePolicyBook> jsonPolicyBook = JsonUtil.readJsonFile(
            filePath, JsonSerializablePolicyBook.class);
        if (!jsonPolicyBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPolicyBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePolicyBook(ReadOnlySiasa policyBook) throws IOException {
        savePolicyBook(policyBook, filePath);
    }

    /**
     * Similar to {@link #savePolicyBook(ReadOnlySiasa)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void savePolicyBook(ReadOnlySiasa policyBook, Path filePath) throws IOException {
        requireNonNull(policyBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePolicyBook(policyBook), filePath);
    }
}
