package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String MESSAGE_SUCCESS = "Exported last searched contacts list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports last searched contacts list into filepath\n"
            + "Parameters: FILEPATH\n"
            + "Example: " + COMMAND_WORD + " newexport.json";

    private final Path filePath;

    public ExportCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        AddressBook tempData;
        tempData = new AddressBook();
        tempData.setPersons(lastShownList);

        JsonAddressBookStorage tempStorage = new JsonAddressBookStorage(filePath);
        try {
            tempStorage.saveAddressBook(tempData);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS));
    }

}
