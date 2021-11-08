package seedu.docit.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.docit.commons.core.LogsCenter;
import seedu.docit.commons.exceptions.DataConversionException;
import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.commons.util.FileUtil;
import seedu.docit.commons.util.JsonUtil;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;

/**
 * A class to access AppointmentBook data stored as a json file on the hard disk.
 */
public class JsonAppointmentBookStorage implements AppointmentBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAppointmentBookStorage.class);

    private Path filePath;

    public JsonAppointmentBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAppointmentBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(ReadOnlyAddressBook addressBook)
        throws DataConversionException {
        return readAppointmentBook(addressBook, filePath);
    }

    /**
     * Similar to {@link AppointmentBookStorage#readAppointmentBook(ReadOnlyAddressBook)}.
     *
     * @param addressBook address book
     * @param filePath    location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(ReadOnlyAddressBook addressBook, Path filePath)
        throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAppointmentBook> jsonAppointmentBook =
            JsonUtil.readJsonFile(filePath, JsonSerializableAppointmentBook.class);
        if (!jsonAppointmentBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAppointmentBook.get().toModelType(addressBook));
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook)
        throws IOException {
        saveAppointmentBook(appointmentBook, addressBook, filePath);
    }

    /**
     * Similar to {@link AppointmentBookStorage#saveAppointmentBook(ReadOnlyAppointmentBook, ReadOnlyAddressBook)}.
     *
     * @param addressBook address book
     * @param filePath    location of the data. Cannot be null.
     */
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook,
        Path filePath) throws IOException {
        requireNonNull(appointmentBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAppointmentBook(appointmentBook, addressBook), filePath);
    }

}
