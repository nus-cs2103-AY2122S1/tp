package seedu.plannermd.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.plannermd.commons.exceptions.DataConversionException;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;

/**
 * Represents a storage for {@link PlannerMd}.
 */
public interface PlannerMdStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPlannerMdFilePath();

    /**
     * Returns PlannerMd data as a {@link ReadOnlyPlannerMd}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPlannerMd> readPlannerMd() throws DataConversionException, IOException;

    /**
     * @see #getPlannerMdFilePath()
     */
    Optional<ReadOnlyPlannerMd> readPlannerMd(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPlannerMd} to the storage.
     * @param plannerMd cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePlannerMd(ReadOnlyPlannerMd plannerMd) throws IOException;

    /**
     * @see #savePlannerMd(ReadOnlyPlannerMd)
     */
    void savePlannerMd(ReadOnlyPlannerMd plannerMd, Path filePath) throws IOException;

}
