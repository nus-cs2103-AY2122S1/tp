package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Importing and exporting of csv files into the database
 */
public class CsvAddressBookImportExport implements ImportExport {

    public static final String MESSAGE_DUPLICATE_NOT_CALLED_PERSON = "Import contains duplicate person(s)."
            + " These duplicate person(s) are  list index:" + "\n";

    public static final String MESSAGE_DUPLICATE_CALLED_PERSON = "These duplicate person(s) with "
            + "these list index will be updated to 'called' : \n";

    private static final Logger logger = LogsCenter.getLogger(CsvAddressBookImportExport.class);

    private boolean fileFound = true;
    private List<Integer> duplicateRowImport = new ArrayList<>();
    private List<Integer> updateRowImport = new ArrayList<>();
    private int successfulNewImportCount = 0;
    private int notCalledDuplicateImportCount = 0;
    private int unsuccessfulImportCount = 0;
    private int calledDuplicateImportCount = 0;


    private final Path importPath;
    private final Path exportPath;


    /**
     * Constructor of the import export
     */
    public CsvAddressBookImportExport(Path importPath, Path exportPath) {
        requireNonNull(importPath);
        requireNonNull(exportPath);
        this.importPath = importPath;
        this.exportPath = exportPath;
    }

    /**
     * The filepath the program expects the csv file to be
     * @return
     */
    public Path getImportExportPath() {
        return importPath;
    }

    /**
     * Imports the csv file into the model
     *
     * @param model Model of the addressbook
     * @return Optional list of person
     * @throws DataConversionException if there are any errors found in the data
     */
    @Override
    public Optional<List<Person>>importIntoAddressBook(Model model) throws DataConversionException {
        return importAddressBook(this.importPath, model);
    }

    /**
     * Returns a list of person optional that are to be imported
     *   Returns {@code Optional.empty()} if the csvfile is not found
     * @param filePath expected path of csv file
     * @param model model to me inserted into
     * @return optional list of person to be added
     * @throws DataConversionException when there are any errors in the person creation
     */
    private Optional<List<Person>> importAddressBook(Path filePath, Model model) throws DataConversionException {
        requireNonNull(filePath);
        fileFound = true;

        Optional<List<Person>> csvImportAddressBook = CsvUtil.readCsvFile(filePath);
        if (csvImportAddressBook.isEmpty()) {
            fileFound = false;
            return Optional.empty();
        }

        addImportIntoAddressBook(csvImportAddressBook.get(), model);
        List<Integer> unsuccessful = CsvUtil.getUnsuccessfulRow();
        unsuccessfulImportCount = unsuccessful.size();
        return csvImportAddressBook;
    }


    @Override
    public void exportCurrentAddressBook(Model model) {
        exportAddressBook(model);
    }

    private void exportAddressBook(Model model) {
        CsvUtil.writeCsvFile(model.getFilteredPersonList(), this.exportPath);
    }

    public String getImportStatus() {
        if (fileFound) {
            return String.format("New Imports : " + String.format("%-19.30s" , successfulNewImportCount)
                    + "          Unsuccessful imports : " + unsuccessfulImportCount
                    + "\nTotal Duplicates : "
                    + String.format("%-10.30s" , (calledDuplicateImportCount + notCalledDuplicateImportCount))
                    + "          Called Duplicate(s): " + calledDuplicateImportCount
                    + "          Not Called Duplicate(s) : " + notCalledDuplicateImportCount)
                    + "\nCheck logs for detailed explanation.";
        }
        return String.format("CSV file not found in " + exportPath);
    }


    /**
     * Imports the list of valid people found in the CSV file
     *   Will not include duplicate people
     * @param people list of valid people
     * @param model model to import into
     */
    public void addImportIntoAddressBook(List<Person> people, Model model) {
        duplicateRowImport = new ArrayList<>(); // reset
        updateRowImport = new ArrayList<>(); // reset
        successfulNewImportCount = 0; // reset
        calledDuplicateImportCount = 0; //reset
        for (int pos = 0; pos < people.size(); pos++) {
            Person importPerson = people.get(pos);
            int listPos = model.duplicateIndex(importPerson);

            if (listPos != -1) {
                if (importPerson.getIsCalled().value) {
                    updatePerson(listPos, importPerson, model);
                    updateRowImport.add(listPos + 1);
                    duplicateRowImport.add(listPos + 1);
                    calledDuplicateImportCount++;
                    continue;
                }
                duplicateRowImport.add(listPos + 1);
                notCalledDuplicateImportCount++;
                continue;
            }
            model.addPerson(importPerson);
            successfulNewImportCount++;
        }
        Collections.sort(duplicateRowImport);
        logger.info(successfulNewImportCount + " new person(s) successfully imported");
        logger.info(MESSAGE_DUPLICATE_CALLED_PERSON + updateRowImport);
        logger.info(MESSAGE_DUPLICATE_NOT_CALLED_PERSON + duplicateRowImport);
    }

    private void updatePerson(int listPos, Person importPerson, Model model) {

        List<Person> lastShownList = model.getFilteredPersonList();
        assert(listPos >= 0 && listPos < lastShownList.size());
        Person personToEdit = lastShownList.get(listPos);
        model.setPerson(personToEdit, importPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

}
