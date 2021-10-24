package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.storage.CsvAdaptedPerson;
import seedu.address.storage.CsvAdaptedTag;
import seedu.address.storage.CsvSerializableAddressBook;

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
        } catch (IOException e) {
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
    private static CsvSerializableAddressBook deserializeAddressBookFromCsvFile(Path filePath) throws IOException {
        // Assumes file exists
        assert FileUtil.isFileExists(filePath);

        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class)
                .with(csvSchema)
                .readValues(filePath.toFile());

        ArrayList<CsvAdaptedPerson> csvAdaptedPersons = new ArrayList<>();
        while (iterator.hasNext()) {
            Map<String, String> row = iterator.next();
            String name = row.get("Name");
            String github = row.get("GitHub");
            String telegram = row.get("Telegram");
            String email = row.get("Email");
            String address = row.get("Address");
            String phone = row.get("Phone Number");
            List<CsvAdaptedTag> tags = Arrays.stream(row.get("Tags").split(" "))
                    .map(CsvAdaptedTag::new)
                    .collect(Collectors.toList());
            CsvAdaptedPerson person = new CsvAdaptedPerson(name, telegram, github, phone,
                    email, address, tags);
            csvAdaptedPersons.add(person);
        }
        return new CsvSerializableAddressBook(csvAdaptedPersons);
    }
}
