package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.storage.CsvAdaptedPerson;

/**
 * Converts a List of persons {@code Person} instance to CSV and vice versa
 */
public class CsvUtil {

    private static final Logger logger = LogsCenter.getLogger(CsvUtil.class);
    private static List<Integer> unsuccessfulRow = new ArrayList<>();
    private static String header = "";

    /**
     * Reads from file and returns a list of valid people to be imported in
     *
     * @param filePath path that contains the csv file to be imported
     * @return list of valid people to be imported
     * @throws IOException thrown when file util could not read the file
     */
    public static List<Person> deserializeObjectFromCsvFile(Path filePath)
            throws IOException, DataConversionException {
        requireNonNull(filePath);
        return fromCsvString(FileUtil.readFromFile(filePath));
    }

    /**
     * Takes in a list of people and writes the individual details in a Csv file
     *
     * @param filePath
     * @param currentState
     * @throws IOException
     */
    public static void serializeObjectToCsvFile(Path filePath, List<Person> currentState) throws IOException {
        FileUtil.writeToFile(filePath, toCsvString(currentState));
    }


    /**
     * Reads from Csv file and converts into an optional list of people
     *
     * @param filePath filepath of the csv
     * @return list optional, whether there is a list of people within the Csv file
     * @throws DataConversionException
     */
    public static Optional<List<Person>> readCsvFile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        if (!Files.exists(filePath)) {
            logger.info("CSV file " + filePath + " not found");
            return Optional.empty();
        }

        List<Person> persons;

        try {
            persons = deserializeObjectFromCsvFile(filePath);
        } catch (IOException e) {
            logger.warning("Error reading from CSV file " + filePath);
            throw new DataConversionException(e);
        }

        return Optional.of(persons);
    }


    /**
     * Gets the current state of the application databse and writes it into a CSV file
     *
     * @param currentState list of valid people currently in the database
     */
    public static void writeCsvFile(List<Person> currentState, Path exportPath) {
        requireNonNull(currentState);
        File dir = new File(exportPath.toString());
        dir.mkdirs();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date date = new Date();
        String exportUniqueName = "export[" + dateFormat.format(date) + "].csv";
        Path exportFilePath = Paths.get(exportPath.toString() , exportUniqueName);
        try {
            FileUtil.createIfMissing(exportFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            serializeObjectToCsvFile(exportFilePath, currentState);
        } catch (IOException e) {
            logger.warning("Error writing from CsvFile file " + exportFilePath);
        }
    }

    /**
     * Returns the list of valid person from the given file or {@code Optional.empty()} object if the file is not found.
     *   If any columns in the CSV file is not found, it will not be imported
     *     Will not read the header of the file as it does not contain a persondetails
     * @param csv
     * @return
     */
    public static List<Person> fromCsvString(String csv) throws DataConversionException {
        unsuccessfulRow = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        String[] personRows = csv.split("\n");

        header = personRows[0];
        checkValidHeader(header);
        for (int i = 1; i < personRows.length; i++) {
            Optional<Person> temp = createPerson(personRows[i].trim(), i + 1);
            if (temp.equals(Optional.empty())) {
                unsuccessfulRow.add(i + 1);
                continue;
            }
            persons.add(temp.get());
        }
        return persons;
    }

    /**
     * Creates a valid person based on the rowdetails found in the csv file
     *
     * @param rowStringPerson
     * @return
     */
    public static Optional<Person> createPerson(String rowStringPerson, int rowNumber) {
        try {
            return Optional.of(new CsvAdaptedPerson(rowStringPerson).toModelType());
        } catch (IllegalValueException e) {
            logger.warning("CSV File Import error : In row " + rowNumber + " : " + e);
            return Optional.empty();
        } catch (DataConversionException e) {
            logger.warning("CSV File Import error : In row " + rowNumber + " : " + e.toString());
            return Optional.empty();
        }
    }


    static String toCsvString(List<Person> personList) {
        String[] headingOrder = CsvAdaptedPerson.headerOrder();
        String headerString = headingOrder[0];
        for (int i = 1; i < headingOrder.length; i++) {
            headerString = headerString + ";" + headingOrder[i];
        }

        String toCsv = headerString;

        for (Person p : personList) {
            String csvString = p.toStringNoHeaders();
            toCsv = toCsv + "\n" + csvString;
        }

        return toCsv + "\n";
    }


    public static List<Integer> getUnsuccessfulRow() {
        return unsuccessfulRow;
    }

    /**
     * Check header of CSV
     *
     * @param header first line of CSV that represents the header
     * @throws DataConversionException thrown if header does not follow format
     */
    public static void checkValidHeader(String header)
            throws DataConversionException {
        String[] headerCheck = header.split(";", CsvAdaptedPerson.ATTRIBUTE_ORDERING.keySet().size());
        String[] headerValid = CsvAdaptedPerson.headerOrder();

        if (headerCheck.length == 1) {
            throw new DataConversionException("Wrong delimiter, Refer to user guide to use correct "
                    + "delimiter.\nEach row should have "
                    + (CsvAdaptedPerson.ATTRIBUTE_ORDERING.keySet().size() - 1) + " ';' ");
        }

        if (headerCheck.length != headerValid.length) {
            throw new DataConversionException("Missing/Extra Headers, Please check file");
        }

        for (int i = 0; i < headerValid.length; i++) {
            String upperHeader = headerCheck[i].toUpperCase(Locale.ROOT);
            String upperValidHeader = headerValid[i].toUpperCase(Locale.ROOT);

            if (!(upperHeader.contains(upperValidHeader))) {
                throw new DataConversionException("Wrong header detected,"
                        + "please double check file\nFirst row of csv must contain valid headers "
                        + Arrays.toString(headerValid) + " in that order.");
            }
        }
    }

}
