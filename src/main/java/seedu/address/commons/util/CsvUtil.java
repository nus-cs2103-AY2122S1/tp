package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.storage.CsvAdaptedPerson;
import seedu.address.storage.CsvSerializableAddressBook;

/**
 * A class for reading from and writing between CSV Files and CsvSerializableAddressBook,
 * using the jackson-dataformat-csv library.
 * Credits: https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);

    /**
     * Jackson Class which helps to read {@code Csv} files.
     */
    private static final CsvMapper csvMapper = new CsvMapper();

    /**
     * Reads a {@code Csv} file and returns it as an Optional of {@link CsvSerializableAddressBook}.
     *
     * @param filePath Path to {@code Csv} file.
     * @return Optional of {@link CsvSerializableAddressBook}.
     * @throws DataConversionException If there is an error reading from {@code Csv} file.
     */
    public static Optional<CsvSerializableAddressBook> readCsvFile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        if (!FileUtil.isFileExists(filePath)) {
            logger.info("Json file " + filePath + " not found");
            return Optional.empty();
        }

        CsvSerializableAddressBook csvSerializableAddressBook;

        try {
            csvSerializableAddressBook = deserializeAddressBookFromCsvFile(filePath);
        } catch (IOException | IllegalValueException e) {
            logger.warning("Error reading from jsonFile file " + filePath + ": " + e);
            throw new DataConversionException(e);
        }
        return Optional.of(csvSerializableAddressBook);
    }

    /**
     * Converts a {@code Csv} file into a {@link CsvSerializableAddressBook}.
     * This method assumes that the file exists.
     *
     * @param filePath Path to the {@code Csv} file.
     * @return {@link CsvSerializableAddressBook}
     * @throws IOException If there is an error reading from {@code Csv} file.
     */
    private static CsvSerializableAddressBook deserializeAddressBookFromCsvFile(Path filePath)
            throws IOException, IllegalValueException {
        // Assumes file exists
        assert FileUtil.isFileExists(filePath);

        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(filePath.toFile());

        ArrayList<CsvAdaptedPerson> csvAdaptedPersons = new ArrayList<>();
        if (!iterator.hasNext()) {
            throw new IOException("Error reading from file");
        }
        while (iterator.hasNext()) {
            Map<String, String> row = iterator.next();
            String name = row.get("name");
            String github = row.get("github");
            String telegram = row.get("telegram");
            String email = row.get("email");
            String address = row.get("address");
            String phone = row.get("phone");
            String tagged = row.get("tagged");
            CsvAdaptedPerson person = new CsvAdaptedPerson(name, telegram, github, phone, email, address, tagged);
            csvAdaptedPersons.add(person);
        }
        return new CsvSerializableAddressBook(csvAdaptedPersons);
    }

    /**
     * Saves a CsvSerializableAddressBook into a specified Csv file.
     * Assumes that the file exists.
     *
     * @param csvSerializableAddressBook Address Book to be saved.
     * @param filePath Path of specified Csv file.
     * @throws IOException If there is an error while writing to the file.
     */
    public static void saveCsvFile(CsvSerializableAddressBook csvSerializableAddressBook, Path filePath)
            throws IOException {
        requireNonNull(csvSerializableAddressBook);
        requireNonNull(filePath);
        assert FileUtil.isFileExists(filePath);

        serializeObjectToCsvFile(csvSerializableAddressBook, filePath);
    }

    /**
     * This method serializes objects to Csv file.
     *
     * @param csvSerializableAddressBook is the Csv serializable address book.
     * @param csvFile is the Csv file.
     * @throws IOException if there is error in writing to the Csv file.
     */
    private static void serializeObjectToCsvFile(CsvSerializableAddressBook csvSerializableAddressBook, Path csvFile)
            throws IOException {
        FileUtil.writeToFile(csvFile, toCsvString(csvSerializableAddressBook));
    }

    /**
     * This method converts the Csv serializable address book to string form.
     *
     * @param csvSerializableAddressBook is the Csv serializable address book.
     * @return String representation of the csvSerializableAddressBook.
     * @throws IOException if there is error in convering the Csv file to String.
     */
    private static String toCsvString(CsvSerializableAddressBook csvSerializableAddressBook) throws IOException {
        csvMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        CsvSchema csvSchema = csvMapper.schemaFor(CsvAdaptedPerson.class).withHeader();
        StringWriter stringWriter = new StringWriter();
        SequenceWriter sequenceWriter = csvMapper
                .writer(csvSchema)
                .writeValues(stringWriter);
        for (CsvAdaptedPerson person : csvSerializableAddressBook.getPersons()) {
            sequenceWriter.write(person);
        }
        String csvString = stringWriter.toString();
        sequenceWriter.close();
        stringWriter.close();
        return csvString;
    }
}
