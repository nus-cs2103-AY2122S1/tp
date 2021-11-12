package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.storage.CsvAddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * A command which imports a list of contacts from an external Json or Csv file.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports contacts from a specified JSON or CSV file.\n"
            + "Parameters: FILENAME.json or FILENAME.csv\n"
            + "Example: " + COMMAND_WORD + " friends.json";

    public static final String MESSAGE_IMPORT_SUCCESS = "Imported from file: %s";

    public static final String MESSAGE_IMPORT_FILE_NOT_FOUND = "File with name %s could not be found!";

    public static final String MESSAGE_IMPORT_FILE_WRONG_TYPE = "%s is not .csv or .json!";

    public static final String MESSAGE_IMPORT_FILE_WRONGLY_FORMATTED = "%s is wrongly formatted!";

    private final Logger logger = LogsCenter.getLogger(ImportCommand.class);

    private final String testPath;
    private final String fileName;

    /**
     * Creates an ImportCommand to import contacts from a specified file.
     *
     * @param fileName Name of the JSON file.
     */
    public ImportCommand(String fileName) {
        requireNonNull(fileName);
        this.testPath = "";
        this.fileName = fileName;
    }

    /**
     * Creates an ImportCommand with a custom filePath for testing purposes.
     *
     * @param testPath Path to the folder where the JSON file will be exported to.
     * @param fileName Name of the JSON file.
     */
    public ImportCommand(String testPath, String fileName) {
        requireNonNull(fileName);
        this.testPath = testPath;
        this.fileName = fileName;
    }

    /**
     * This method attempts to import contacts.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any errors during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Verify file extension. Should have been checked in ImportCommandParser.
        assert StringUtil.isCsv(fileName) || StringUtil.isJson(fileName);

        // Retrieve list of Persons.
        List<Person> personList;
        if (StringUtil.isJson(fileName)) {
            personList = getImportedPersonsFromJson();
        } else if (StringUtil.isCsv(fileName)) {
            personList = getImportedPersonsFromCsv();
        } else {
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_WRONG_TYPE, fileName));
        }

        addPersonsToModel(model, personList);
        switchToContactsTab(model);

        logger.info("Import success");
        return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, fileName));
    }

    /**
     * Retrieves a list of {@code Person} objects from the specified Json file.
     *
     * @return List of {@code Person}.
     * @throws CommandException if the Json file is wrongly formatted or does not exist.
     */
    private List<Person> getImportedPersonsFromJson() throws CommandException {
        Path filePath = Path.of(testPath + fileName);
        JsonAddressBookStorage temporaryStorage = new JsonAddressBookStorage(filePath);
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook fileData;
        try {
            addressBookOptional = temporaryStorage.readAddressBook();
            fileData = addressBookOptional.orElseThrow();
        } catch (DataConversionException dce) {
            logger.info("Import: Error while reading contacts from JSON file. File is likely to be wrongly formatted.");
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_WRONGLY_FORMATTED, fileName));
        } catch (NoSuchElementException nsee) {
            logger.info("Import: File does not exist");
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_NOT_FOUND, fileName));
        }
        return fileData.getPersonList();
    }

    /**
     * Retrieves a list of {@code Person} objects from the specified Csv file.
     *
     * @return List of {@code Person}.
     * @throws CommandException if the Csv file is wrongly formatted or does not exist.
     */
    private List<Person> getImportedPersonsFromCsv() throws CommandException {
        Path filePath = Path.of(testPath + fileName);
        CsvAddressBookStorage temporaryStorage = new CsvAddressBookStorage(filePath);
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook fileData;
        try {
            addressBookOptional = temporaryStorage.readAddressBook();
            fileData = addressBookOptional.orElseThrow();
        } catch (DataConversionException dce) {
            logger.info("Import: Error while reading contacts from CSV file. File is likely to be wrongly formatted.");
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_WRONGLY_FORMATTED, fileName));
        } catch (NoSuchElementException nsee) {
            logger.info("Import: File does not exist");
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_NOT_FOUND, fileName));
        }
        return fileData.getPersonList();
    }

    /**
     * This method attempts to add the list of Person object to the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @param personList to be added to Model.
     */
    private void addPersonsToModel(Model model, List<Person> personList) {
        // Add Persons to Model
        for (Person p: personList) {
            try {
                model.addPerson(p);
            } catch (DuplicatePersonException dpe) {
                // do nothing; skip the current person if it already exists
            }
        }
    }

    /**
     * This method switches the tabs to contacts tab.
     *
     * @param model {@code Model} which the command should operate on.
     */
    private void switchToContactsTab(Model model) {
        if (model.getPersonListControl() != null) {
            model.setTabIndex(0);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    /**
     * Method to compare two ImportCommand objects.
     *
     * @param other is the object that is going to be compared
     *              to the ImportCommand object that called this method.
     * @return boolean representation of whether the ImportCommand
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && fileName.equals(((ImportCommand) other).fileName)); // state check
    }
}
