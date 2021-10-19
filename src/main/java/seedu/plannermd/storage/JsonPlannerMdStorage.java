package seedu.plannermd.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.plannermd.commons.core.LogsCenter;
import seedu.plannermd.commons.exceptions.DataConversionException;
import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.commons.util.FileUtil;
import seedu.plannermd.commons.util.JsonUtil;
import seedu.plannermd.model.ReadOnlyPlannerMd;

/**
 * A class to access PlannerMd data stored as a json file on the hard disk.
 */
public class JsonPlannerMdStorage implements PlannerMdStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPlannerMdStorage.class);

    private Path filePath;

    public JsonPlannerMdStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPlannerMdFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPlannerMd> readPlannerMd() throws DataConversionException {
        return readPlannerMd(filePath);
    }

    /**
     * Similar to {@link #readPlannerMd()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPlannerMd> readPlannerMd(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePlannerMd> jsonPlannerMd = JsonUtil.readJsonFile(
                filePath, JsonSerializablePlannerMd.class);
        if (!jsonPlannerMd.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPlannerMd.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePlannerMd(ReadOnlyPlannerMd plannerMd) throws IOException {
        savePlannerMd(plannerMd, filePath);
    }

    /**
     * Similar to {@link #savePlannerMd(ReadOnlyPlannerMd)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePlannerMd(ReadOnlyPlannerMd plannerMd, Path filePath) throws IOException {
        requireNonNull(plannerMd);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePlannerMd(plannerMd), filePath);
    }

}
