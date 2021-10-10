package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.storage.JsonAddressBookStorage;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Imports contacts from a specified JSON file.\n"
            + "Parameters: FILENAME\n"
            + "Example: " + COMMAND_WORD + " friends";

    public static final String MESSAGE_IMPORT_SUCCESS = "Imported from file: %s.json";

    public static final String MESSAGE_IMPORT_FILE_NOT_FOUND = "File with name &s.json could not be found!";

    public static final String MESSAGE_IMPORT_FILE_WRONG_TYPE = "%s is in the wrong format!";

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
        Path filePath = Path.of(testPath + fileName + ".json");
        JsonAddressBookStorage temporaryStorage = new JsonAddressBookStorage(filePath);
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook fileData;
        try {
            addressBookOptional = temporaryStorage.readAddressBook();
            fileData = addressBookOptional.orElseThrow();
        } catch (DataConversionException dce) {
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_WRONG_TYPE, fileName));
        } catch (NoSuchElementException nsee) {
            throw new CommandException(String.format(MESSAGE_IMPORT_FILE_NOT_FOUND, fileName));
        }
        ObservableList<Person> personList = fileData.getPersonList();
        for (Person p: personList) {
            try {
                model.addPerson(p);
            } catch (DuplicatePersonException dpe) {
                // skip current person if it already exists
                continue;
            }
        }
        return new CommandResult(String.format(MESSAGE_IMPORT_SUCCESS, fileName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && fileName.equals(((ImportCommand) other).fileName)); // state check
    }
}
