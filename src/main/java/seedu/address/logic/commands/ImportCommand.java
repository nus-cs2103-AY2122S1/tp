package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

/**
 * Imports a JSON file to the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD
            + ": Imports the contacts list from a file identified by the given filename.\n\n"
            + "Parameters: FILENAME\n\n"
            + "Example: " + COMMAND_WORD + " t35.json";

    public static final String MESSAGE_SUCCESS = "File successfully imported.\n%s new contacts added";
    public static final String MESSAGE_INCORRECT_FORMAT = String.format("Data file not in the correct format. "
            + "Please make sure that all fields are valid and that the file is in JSON format\n%s", MESSAGE_USAGE);
    public static final String MESSAGE_IO_ERROR =
            "Problem while reading from the file";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found. Please try again";
    public static final String MESSAGE_DUPLICATE_PERSON = "Invalid file. %s";
    public static final String MESSAGE_WRONG_FORMAT = "Imported file must be in JSON format and "
            + "should not contain any special characters";

    private final String importedFileName; // fileName.json

    /**
     * Creates an ImportCommand to import the specified {@code importedFileName}
     */
    public ImportCommand(String importedFileName) {
        requireNonNull(importedFileName);
        this.importedFileName = importedFileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Path importedFilePath;
        int contactsAdded;

        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                importedFilePath = Paths.get("data", importedFileName);
            } else {
                importedFilePath = Paths.get(FileUtil.getCurrentPath(), "..", "data", importedFileName)
                        .toAbsolutePath().normalize();
            }

            UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(new Config().getUserPrefsFilePath());
            AddressBookStorage importedAddressBookStorage = new JsonAddressBookStorage(importedFilePath);
            Storage storage = new StorageManager(importedAddressBookStorage, userPrefsStorage);

            contactsAdded = model.importAddressBook(storage.readAddressBook().get());
        } catch (DataConversionException e) {
            throw new CommandException(MESSAGE_INCORRECT_FORMAT);
        } catch (IOException | URISyntaxException e) {
            throw new CommandException(MESSAGE_IO_ERROR);
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        } catch (DuplicatePersonException e) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, e.getMessage()));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, contactsAdded));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && importedFileName.equals(((ImportCommand) other).importedFileName));
    }
}
