package seedu.placebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.placebook.commons.exceptions.DataConversionException;
import seedu.placebook.model.ReadOnlyContacts;
import seedu.placebook.model.ReadOnlySchedule;

/**
 * Represents a storage for {@link seedu.placebook.model.schedule.Schedule}.
 */
public interface ScheduleStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getScheduleFilePath();

    /**
     * Returns contact data as a {@link ReadOnlyContacts}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySchedule> readSchedule() throws DataConversionException, IOException;

    /**
     * @see #getScheduleFilePath()
     */
    Optional<ReadOnlySchedule> readSchedule(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyContacts} to the storage.
     * @param schedule cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSchedule(ReadOnlySchedule schedule) throws IOException;

    /**
     * @see #saveSchedule(ReadOnlySchedule)
     */
    void saveSchedule(ReadOnlySchedule schedule, Path filePath) throws IOException;

}
