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

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports contacts from a specified JSON file.\n"
            + "Parameters: FILENAME.json or FILENAME.csv\n"
            + "Example: " + COMMAND_WORD + " friends";

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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> personList;
        if (StringUtil.isJson(fileName)) {
            personList = getImportedPersonsFromJson();
        } else if (StringUtil.isCsv(fileName)) {
            personList = getImportedPersonsFromCsv();
        } else {
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_WRONG_TYPE, fileName));
        }

        assert StringUtil.isCsv(fileName) || StringUtil.isJson(fileName);

        for (Person p: personList) {
            try {
                model.addPerson(p);
            } catch (DuplicatePersonException dpe) {
                // do nothing; skip the current person if it already exists
            }
        }
        if (model.getPersonListControl() != null) {
            model.setTabIndex(0);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, fileName));
    }

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && fileName.equals(((ImportCommand) other).fileName)); // state check
    }
}
