package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports the contacts into a named JSON file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports the current list of contacts to a specified filename.\n"
            + "Parameters: FILENAME\n"
            + "Example: " + COMMAND_WORD + " friends";

    public static final String MESSAGE_EXPORT_SUCCESS = "Exported to file: %s.json";

    public static final String MESSAGE_EXPORT_FAILURE = "File with name %s.json already exists!";

    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private final String fileName;

    /**
     * Creates an ExportCommand to export the AddressBook to a specified fileName.
     *
     * @param fileName Name of the JSON file.
     */
    public ExportCommand(String fileName) {
        requireNonNull(fileName);
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Path filePath = Path.of(fileName + ".json");
        JsonAddressBookStorage temporaryStorage = new JsonAddressBookStorage(filePath);
        try {
            temporaryStorage.exportToJson(model.getAddressBook());
        } catch (FileAlreadyExistsException faee) {
            throw new CommandException(String.format(MESSAGE_EXPORT_FAILURE, fileName));
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return new CommandResult(String.format(MESSAGE_EXPORT_SUCCESS, fileName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExportCommand // instanceof handles nulls
                && fileName.equals(((ExportCommand) other).fileName)); // state check
    }
}
