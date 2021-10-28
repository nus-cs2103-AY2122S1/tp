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
 * Exports last searched contact list into filepath
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file";
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
        return execute(model, new JsonAddressBookStorage(filePath));
    }

    CommandResult execute(Model model, JsonAddressBookStorage storage) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        AddressBook tempData;
        tempData = new AddressBook();
        tempData.setPersons(lastShownList);

        try {
            storage.saveAddressBook(tempData);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE);
        }

        return new CommandResult(
            String.format(MESSAGE_SUCCESS + " to " + filePath));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ExportCommand)) {
            return false;
        }
        ExportCommand e = (ExportCommand) other;
        return filePath.equals(e.filePath);
    }

}
