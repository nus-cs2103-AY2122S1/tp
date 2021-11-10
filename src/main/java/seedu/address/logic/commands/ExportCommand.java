package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.CsvAddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports the contacts into a named Json or Csv file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports the current list of contacts to a specified JSON or CSV file.\n"
            + "Parameters: FILENAME.json or FILENAME.csv\n"
            + "Example: " + COMMAND_WORD + " friends.csv";

    public static final String MESSAGE_EXPORT_SUCCESS = "Exported to file: %s";

    public static final String MESSAGE_EXPORT_FAILURE = "File with name %s already exists!";

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    public static final String MESSAGE_EXPORT_FILE_WRONG_TYPE = "%s is not .csv or .json!";

    private final String testPath;
    private final String fileName;

    /**
     * Creates an ExportCommand to export the AddressBook to a specified fileName.
     *
     * @param fileName Name of the Json or Csv file.
     */
    public ExportCommand(String fileName) {
        requireNonNull(fileName);
        this.testPath = "";
        this.fileName = fileName;
    }

    /**
     * Creates an ExportCommand with a custom filePath for testing purposes.
     *
     * @param testPath Path where the Json or Csv file will be exported to.
     * @param fileName Name of the Json or Csv file.
     */
    public ExportCommand(String testPath, String fileName) {
        requireNonNull(fileName);
        this.testPath = testPath;
        this.fileName = fileName;
    }

    /**
     * This method attempts to export existing contacts.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any errors during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        ReadOnlyAddressBook currentAddressBook = model.getAddressBook();
        try {
            executeByCase(currentAddressBook);
        } catch (FileAlreadyExistsException faee) {
            throw new CommandException(String.format(MESSAGE_EXPORT_FAILURE, fileName));
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return new CommandResult(String.format(MESSAGE_EXPORT_SUCCESS, fileName));
    }

    private void executeByCase(ReadOnlyAddressBook currentAddressBook) throws CommandException, IOException {
        if (StringUtil.isJson(fileName)) {
            exportAddressBookToJson(currentAddressBook);
        } else if (StringUtil.isCsv(fileName)) {
            exportAddressBookToCsv(currentAddressBook);
        } else {
            throw new CommandException(String.format(MESSAGE_EXPORT_FILE_WRONG_TYPE, fileName));
        }
    }

    private void exportAddressBookToJson(ReadOnlyAddressBook currentAddressBook) throws IOException {
        Path filePath = Path.of(testPath + fileName);
        JsonAddressBookStorage temporaryStorage = new JsonAddressBookStorage(filePath);
        // The third argument true indicates that this method is triggered by an Export command to prevent overwriting.
        // Necessary because normal saving of address book also uses this method, but requires overwriting.
        temporaryStorage.saveAddressBook(currentAddressBook, filePath, true);
    }

    private void exportAddressBookToCsv(ReadOnlyAddressBook currentAddressBook) throws IOException {
        Path filePath = Path.of(testPath + fileName);
        CsvAddressBookStorage temporaryStorage = new CsvAddressBookStorage(filePath);
        temporaryStorage.saveAddressBook(currentAddressBook, filePath);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && fileName.equals(((ExportCommand) other).fileName)); // state check
    }
}
