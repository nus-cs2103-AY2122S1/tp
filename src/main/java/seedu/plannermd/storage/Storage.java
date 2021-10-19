package seedu.plannermd.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.plannermd.commons.exceptions.DataConversionException;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.ReadOnlyUserPrefs;
import seedu.plannermd.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PlannerMdStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPlannerMdFilePath();

    @Override
    Optional<ReadOnlyPlannerMd> readPlannerMd() throws DataConversionException, IOException;

    @Override
    void savePlannerMd(ReadOnlyPlannerMd plannerMd) throws IOException;

}
